package me.marcangeloh;

import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.API.Util.GeneralUtil.*;
import me.marcangeloh.Commands.PointCheckCommand;
import me.marcangeloh.Commands.PointsCoreCommands;
import me.marcangeloh.Events.ArmorEvent;
import me.marcangeloh.Events.JoinEvent;
import me.marcangeloh.Events.ToolEvents;
import me.marcangeloh.Events.WeaponEvent;
import me.marcangeloh.API.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.API.Util.ConfigurationUtil.Paths;
import me.marcangeloh.API.Util.SQLUtil.SQLManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.List;


public class PointsCore extends JavaPlugin implements Paths {
    private DataManager dataManager;
    private boolean isMySQLEnabled = false;
    private SQLManager sqlManager;
    public static Plugin plugin;
    public PlayerPoints playerPoints;
    public String server_version;
    public static boolean is1_16 = false;
    public static DebugIntensity serverDebugIntensity;
    public final static String pluginVersion ="1.1.92-SNAPSHOT";


    public void onDisable() {
        if(isMySQLEnabled) {
            sqlManager.saveData();
        } else{
            dataManager.saveAll();
        }
    }

    public void onEnable() {
        basicSetup();
        savingSetup();
        registerEvents();
        performPluginHooks(); //Hooking into other plugins
        getCommand("pointcheck").setExecutor(new PointCheckCommand());
        getCommand("points").setExecutor(new PointsCoreCommands());
        updateChecker();
    }

    public void removePoints(Tools tool, Player player, double amount) {
        playerPoints.removePointsToToolType(tool,player,amount);
    }

    public void addPoints(Tools tool, Player player, double amount) {
        playerPoints.addPointsToToolType(tool,player,amount);
    }

    private void updateChecker() {
        new UpdateChecker(this, 83263).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Message.errorMessage("PointsCore has a new update available, "+version, getServer().getConsoleSender());
            }
        });
    }

    private void performPluginHooks() {
        //PlaceholderAPI Hook
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPILink().register();
        }
        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            if(Bukkit.getServer().getPluginManager().getPlugin("PointsCore").isEnabled()) {
                Bukkit.getServicesManager().register(Economy.class, new EconomySetup(this), this, ServicePriority.Normal);
                Message.notifyMessage("Economy has been registered to vault.", getServer().getConsoleSender());
            }
        }

    }

    /**
     * Handles the basic setup for the server
     */
    private void basicSetup() {
        int pluginId = 8682;
        Metrics metrics = new Metrics(this, pluginId);
        plugin = this;
        playerPoints = new PlayerPoints();
        setupVersion();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        serverDebugIntensity = getDebugIntensity();
    }


    private void savingSetup() {
        //If SQL is not enabled Initiates the data from the config and loads it
        if(!getConfig().getBoolean(pathIsSQLEnabled)) {
            dataManager = new DataManager();
            dataManager.onEnable(plugin);
            List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
            for(Player player: players) {
                playerPoints = dataManager.loadPlayerFromSaveFile(playerPoints, player);
            }

        } else {
            isMySQLEnabled = true;
            //MySQL Initialization
            try {
                sqlManager = new SQLManager(getConfig().getString(pathSQLUsername),getConfig().getString(pathSQLPassword),"POINTS",  getConfig().getString(pathSQLHostName), getConfig().getString(pathSQLDatabase));
                playerPoints = sqlManager.loadData();
            } catch (SQLException e) {
                e.printStackTrace();
                Message.errorMessage("SQL Was not successfully enabled, the details input must be incorrect.", getServer().getConsoleSender());
                isMySQLEnabled = false;
                savingSetup();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Message.errorMessage("SQL Was not successfully enabled, the details input must be incorrect.", getServer().getConsoleSender());
                isMySQLEnabled = false;
                savingSetup();
            }

        }
    }

    /**
     * To avoid code duplication
     */

    /**
     * Registers the events to the server
     */
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new WeaponEvent(), this); //Registers the armor points events
        getServer().getPluginManager().registerEvents(new ToolEvents(), this); //Registers the tool points events
        getServer().getPluginManager().registerEvents(new ArmorEvent(), this); //Registers the tool points events
        getServer().getPluginManager().registerEvents(new JoinEvent(), this); // Registers the Join event
    }

    public void loadPlayerData(Player player) {
        if(isMySQLEnabled) {
            playerPoints = sqlManager.loadPlayerData(player) != null ? sqlManager.loadPlayerData(player) : playerPoints;
        } else {
            playerPoints = dataManager.loadPlayerFromSaveFile(playerPoints, player);
        }
    }

    public void savePlayerData(Player player) {
        if(isMySQLEnabled) {
            sqlManager.savePlayerData(player);
        } else {
            dataManager.addPlayerToSaveFile(player);
        }
    }


    private boolean setupVersion() {
        server_version = "N/A";

        try {
            server_version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        if(server_version.equalsIgnoreCase("v1_16_R1")) {
            is1_16 = true;
            return true;
        }
        return false;
    }

    /**
     * Gets the debug intensity for the messages of the server
     * @return the Debug intensity
     */
    public DebugIntensity getDebugIntensity() {
        if(getConfig().getString("Points.DebugMode").equalsIgnoreCase("INTENSE")) {

            return DebugIntensity.INTENSE;

        } else if(getConfig().getString("Points.DebugMode").equalsIgnoreCase("LIGHT")) {

            return DebugIntensity.LIGHT;

        } else {

            return DebugIntensity.NONE;

        }
    }
}

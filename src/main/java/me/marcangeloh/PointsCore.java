package me.marcangeloh;

import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.Commands.PointCheckCommand;
import me.marcangeloh.Events.ToolEvents;
import me.marcangeloh.Events.WeaponEvent;
import me.marcangeloh.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.Util.ConfigurationUtil.Paths;
import me.marcangeloh.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.Util.GeneralUtil.Message;
import me.marcangeloh.Util.SQLUtil.SQLManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public class PointsCore extends JavaPlugin implements Paths {
    private DataManager dataManager;
    private boolean isMySQLEnabled = false;
    private SQLManager sqlManager;
    public static Plugin plugin;
    public static PlayerPoints playerPoints;
    public String server_version;
    public static boolean is1_16 = false;
    public static DebugIntensity serverDebugIntensity;

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
        getCommand("pointcheck").setExecutor(new PointCheckCommand());
    }

    private void basicSetup() {
        int pluginId = 8682;
        Metrics metrics = new Metrics(this, pluginId);

        plugin = this;
        playerPoints = new PlayerPoints();
        setupVersion();
        serverDebugIntensity = getDebugIntensity();
    }

    private void savingSetup() {
        //If SQL is not enabled Initiates the data from the config and loads it
        if(!getConfig().getBoolean(pathIsSQLEnabled)) {
            dataManager = new DataManager();
            dataManager.onEnable(plugin);
        } else {
            isMySQLEnabled = true;
            //MySQL Initialization
            try {
                sqlManager = new SQLManager(getConfig().getString(pathSQLUsername),getConfig().getString(pathSQLPassword),"POINTS",  getConfig().getString(pathSQLHostName), getConfig().getString(pathSQLDatabase));
                sqlManager.loadData();
            } catch (SQLException e) {
                e.printStackTrace();
                errorMessage("SQL Was not successfully enabled, the details input must be incorrect.");
                isMySQLEnabled = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                errorMessage("SQL Was not successfully enabled, the details input must be incorrect.");
                isMySQLEnabled = false;
            }

        }
    }

    /**
     * To avoid code duplication
     * @param event the event to register
     */

    /**
     * Registers the events to the server
     */
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new WeaponEvent(), this); //Registers the armor points events
        getServer().getPluginManager().registerEvents(new ToolEvents(), this); //Registers the tool points events
    }

    /**
     * Prints out an error message
     * @param errorMessage the error message to print out
     */
    public void errorMessage(String errorMessage) {
        Message.errorMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + errorMessage, getServer().getConsoleSender());
    }


    public DataManager getDataManager() {
        return dataManager;
    }

    private void setupDefaultConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
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

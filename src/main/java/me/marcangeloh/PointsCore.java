package me.marcangeloh;

import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.Commands.PointCheckCommand;
import me.marcangeloh.Commands.PointsCoreCommands;
import me.marcangeloh.Events.ArmorEvent;
import me.marcangeloh.Events.ToolEvents;
import me.marcangeloh.Events.WeaponEvent;
import me.marcangeloh.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.Util.ConfigurationUtil.Paths;
import me.marcangeloh.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.Util.GeneralUtil.Message;
import me.marcangeloh.Util.GeneralUtil.PlaceholderAPILink;
import me.marcangeloh.Util.GeneralUtil.Tools;
import me.marcangeloh.Util.SQLUtil.SQLManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public final static String pluginVersion ="1.1.1-SNAPSHOT";


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
        try {
            HttpURLConnection c = (HttpURLConnection)new URL("http://www.spigotmc.org/api/general.php").openConnection();
            c.setDoOutput(true);
            c.setRequestMethod("POST");
            c.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=83263").getBytes("UTF-8"));
            String oldVersion = this.getDescription().getVersion();
            String newVersion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine().replaceAll("[a-zA-Z ]", "");
            if(!newVersion.equals(oldVersion)) {
                //there is a new version
                Message.notifyMessage("A new version is available for download on spigotmc, your version is "+oldVersion +" the newest version is "+newVersion+".", getServer().getConsoleSender());
            }
        }
        catch(Exception e) {
            //update failed, most likely to spigot being down or the server not having internet connection
            Message.errorMessage("Could not check for latest update! Most likely due to no internet connection or spigot servers being down.", getServer().getConsoleSender());
        }
    }

    private void performPluginHooks() {
        //PlaceholderAPI Hook
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPILink().register();
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
                Message.errorMessage("SQL Was not successfully enabled, the details input must be incorrect.", getServer().getConsoleSender());
                isMySQLEnabled = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Message.errorMessage("SQL Was not successfully enabled, the details input must be incorrect.", getServer().getConsoleSender());
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
        getServer().getPluginManager().registerEvents(new ArmorEvent(), this); //Registers the tool points events
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

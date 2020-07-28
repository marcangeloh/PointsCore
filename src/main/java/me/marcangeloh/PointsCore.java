package me.marcangeloh;

import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.Events.ArmorEvent;
import me.marcangeloh.Events.LoadDataEvent;
import me.marcangeloh.Events.ToolEvents;
import me.marcangeloh.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.Util.ConfigurationUtil.Paths;
import me.marcangeloh.Util.SQLUtil.SQLManager;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public class PointsCore extends JavaPlugin implements Paths {
    private DataManager dataManager;
    private boolean isMySQLEnabled = false;
    private SQLManager sqlManager;
    private Plugin plugin;
    private static PlayerPoints playerPoints;


    public void onDisable() {
        sqlManager.saveData(playerPoints.armorPoints.getArmorPoints(),
                playerPoints.meleeWeaponPoints.getMeleeWeaponPoints(),
                playerPoints.rangedWeaponPoints.getRangedWeaponPoints(),
                playerPoints.hoePoints.getHoePoints(),
                playerPoints.pickaxePoints.getPickaxePoints(),
                playerPoints.axePoints.getAxePoints(),
                playerPoints.fishingPoints.getFishingPoints(),
                playerPoints.shovelPoints.getShovelPoints() );
    }

    public void onEnable() {
        dataManager = new DataManager();
        plugin = this;

        //sets up the default configuration
        setupDefaultConfig();

        playerPoints = new PlayerPoints();

        //If SQL is not enabled Initiates the data from the config and loads it
        if(!getConfig().getBoolean(pathIsSQLEnabled)) {
            dataManager.onEnable(plugin);
        } else {
            isMySQLEnabled = true;
            //MySQL Initialization
            try {
                sqlManager = new SQLManager(getConfig().getString(pathSQLUsername),getConfig().getString(pathSQLPassword), "Points",getConfig().getString(pathSQLHostName), getConfig().getString(pathSQLDatabase), playerPoints);

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
        registerEvents();

    }

    /**
     * To avoid code duplication
     * @param event the event to register
     */
    private void eventRegistration(Listener event) {
        getServer().getPluginManager().registerEvents(event, plugin);
    }

    /**
     * Registers the events to the server
     */
    private void registerEvents() {
        eventRegistration(new LoadDataEvent(playerPoints, isMySQLEnabled(), dataManager, sqlManager));
        eventRegistration(new ArmorEvent(playerPoints.armorPoints, plugin)); //Registers the armor points events
        eventRegistration(new ToolEvents(plugin, playerPoints)); //Registers the tool points events
    }

    /**
     * Prints out an error message
     * @param errorMessage the error message to print out
     */
    public void errorMessage(String errorMessage) {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + errorMessage);
    }

    public boolean isMySQLEnabled() {
        return isMySQLEnabled;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    private void setupDefaultConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public static void updatePlayerPoints(PlayerPoints playerPoint) {
        playerPoints = playerPoint;
    }

}

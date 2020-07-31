package me.marcangeloh.Events;

import me.marcangeloh.API.PointsUtil.DetailedPoints.*;
import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.PointsCore;
import me.marcangeloh.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.Util.SQLUtil.SQLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class LoadDataEvent implements Listener {
    private boolean mySQL;
    private DataManager dataManager;
    private SQLManager sqlManager;

    public LoadDataEvent(boolean isMySQLEnabled, DataManager dataManager, SQLManager sqlManager) {
        mySQL = isMySQLEnabled;
        this.dataManager = dataManager;
        this.sqlManager = sqlManager;
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Player player= event.getPlayer();
        if(!allPointsContainPlayer(player)) {
            //If they are not already loaded or not properly loaded.

            if(mySQL) {

                //If My SQL is enabled load from mysql
                sqlManager.loadData(player.getName(), player.getUniqueId().toString());

            } else {

                //Otherwise load from config
                dataManager.loadPlayerFromSaveFile(PointsCore.playerPoints, player);

            }
        }
    }

    /**
     * Checks if the player exists in all points
     * @param player the player to check for
     * @return the value of the points contained
     */
    private boolean allPointsContainPlayer(Player player) {
        if(PointsCore.playerPoints.armorPoints.containsPlayer(player) && PointsCore.playerPoints.meleeWeaponPoints.containsPlayer(player) &&
                PointsCore.playerPoints.rangedWeaponPoints.containsPlayer(player) && PointsCore.playerPoints.hoePoints.containsPlayer(player) &&
                PointsCore.playerPoints.pickaxePoints.containsPlayer(player) && PointsCore.playerPoints.axePoints.containsPlayer(player) && PointsCore.playerPoints.fishingPoints.containsPlayer(player) &&
                PointsCore.playerPoints.shovelPoints.containsPlayer(player)) {
            return true;
        }
        return false;
    }
}

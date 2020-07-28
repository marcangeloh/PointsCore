package me.marcangeloh.API.PointsUtil.DetailedPoints;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class AxePoints implements Points {

    private final HashMap<String, Double> axePoints = new HashMap<String, Double>();
    public HashMap<String, Double> getAxePoints() {
        return axePoints;
    }

    @Override
    public double getPoints(Player player) {
        if(!axePoints.containsKey(player.getUniqueId().toString())) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The player "+player.getName()+" was not found. Adding to the hashmap.");
            axePoints.putIfAbsent(player.getUniqueId().toString(), 0.0);
        }
        return axePoints.get(player.getUniqueId().toString());
    }

    @Override
    public double getPoints(String player) {
        if(!axePoints.containsKey(player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The player with UUID: "+player+" was not found. Adding to the hashmap.");
            axePoints.putIfAbsent(player, 0.0);
        }
        return axePoints.get(player);  }
    /**
     * This method should be called on disable, it registers all of the players
     * In the axePoints HashMap
     * @return
     */
    public boolean onDisable() {
        if(axePoints.isEmpty()) {
            return true;
        } else {
            Set<String> keySet = axePoints.keySet();
            for (String key: keySet
            ) {
                if(axePoints.get(key) > 0.0) { //If they have any points
                    //Perform the save
                }
            }
        }

        return axePoints.isEmpty();
    }

    /**
     * Checks if the player is contained in the hashmap
     * @param player The player to check for
     * @return true if they are false if they aren't
     */
    public boolean containsPlayer(Player player) {
        if(axePoints.containsKey(player)){
            return true;
        }
        return false;
    }

    /**
     * Adds the player to the axePoints HashMap.
     * @param player The player in Player format to add
     * @param points Adds this amount of points to the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean addPointsToPlayer(Player player, Double points) {
        if(points == null)
            points = 0.0;
        String playerName = player.getName();

        return addPointsMethod(playerName, points);
    }

    /**
     * Adds the player to the axePoints HashMap.
     * @param player The player's name to add
     * @param points Adds this amount of points to the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean addPointsToPlayer(String player, Double points) {
        if(points == null)
            points = 0.0;
        return addPointsMethod(player, points);
    }

    /**
     * Adds points to the player in the hashmap
     * @param player The player in question
     * @param points The amount of points
     * @return True if already exists, False if they don't exist
     */
    private boolean addPointsMethod(String player, Double points) {
        if(axePoints.containsKey(player)) {
            double pointsToAdd = axePoints.get(player);
            axePoints.remove(player);
            axePoints.put(player, points + pointsToAdd);
            return true;
        } else {
            axePoints.put(player, points);
            return false;
        }
    }

    /**
     * Remove points from the player
     * @param player The player's name
     * @param points The amount of points to add
     * @return True if successful, False if player's point balance would go into the negative
     */
    private boolean removePointsMethod(String player, Double points) {
        if(axePoints.containsKey(player)) {
            double pointsToAdd = axePoints.get(player);

            if(pointsToAdd < points) { //If pointsToAdd-points < 0
                return false;//Return false
            }

            axePoints.remove(player);
            axePoints.put(player, pointsToAdd - points);
            return true;
        } else {
            axePoints.put(player, points);
            return false;
        }
    }

    /**
     * Removes points from the player in the axePoints HashMap.
     * @param player The player's name to remove points from
     * @param points Removes this amount of points from the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean removePointsFromPlayer(String player, Double points) {
        if(points == null)
            points = 0.0;
        return removePointsMethod(player, points);
    }

    /**
     * Removes points from the player in the axePoints HashMap.
     * @param playerInstance The player to remove points from
     * @param points Removes this amount of points from the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean removePointsFromPlayer(Player playerInstance, Double points) {
        if(points == null)
            points = 0.0;

        String player = playerInstance.getName();
        return removePointsMethod(player, points);
    }
    
}

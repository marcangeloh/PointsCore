package me.marcangeloh.API.PointsUtil.DetailedPoints;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface Points extends Listener {


    /**
     * Gets the points of a player
     * @param player the player whos points to return
     * @return the points of the player
     */
    double getPoints(Player player);

    /**
     * Gets the points of a player
     * @param player the player in string form
     * @return the points of the player
     */
    double getPoints(String player);


    /**
     * Checks if the hashmap contains the player
     * @param player The player to check for
     * @return true if the player exists in the hashmap and false if not or if it's 0.
     */
    boolean containsPlayer(Player player);

    /**
     * Adds the player to the armorPoints HashMap.
     * @param player The player in Player format to add
     * @param points Adds this amount of points to the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    boolean addPointsToPlayer(Player player, Double points);

    /**
     * Modifies the hashmap so that the player has the set amount of points
     * @param player The player to set points for
     * @param points The amount of points
     * @return true transaction is successful
     */
    boolean setPointsForPlayer(Player player, Double points);

    /**
     * Adds the player to the armorPoints HashMap.
     * @param player The player's name to add
     * @param points Adds this amount of points to the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    boolean addPointsToPlayer(String player, Double points);

    /**
     * Removes points from the player in the armorPoints HashMap.
     * @param player The player's name to remove points from
     * @param points Removes this amount of points from the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    boolean removePointsFromPlayer(String player, Double points);

    /**
     * Removes points from the player in the armorPoints HashMap.
     * @param playerInstance The player to remove points from
     * @param points Removes this amount of points from the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    boolean removePointsFromPlayer(Player playerInstance, Double points);

    String getPointName();

    void setMultiplier(Player player, Double multiplier);

    Double getMultiplier(Player player);

}

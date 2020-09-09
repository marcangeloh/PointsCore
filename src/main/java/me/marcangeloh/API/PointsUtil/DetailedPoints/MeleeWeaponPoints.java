package me.marcangeloh.API.PointsUtil.DetailedPoints;

import me.marcangeloh.API.Events.PointsAddedEvent;
import me.marcangeloh.API.Events.PointsRemovedEvent;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MeleeWeaponPoints implements Points {
    private final HashMap<String, Double> meleeWeaponPoints;
    private final HashMap<Player, Double> multiplier = new HashMap<>();

    public MeleeWeaponPoints() {
        meleeWeaponPoints = new HashMap<>();
    }

    public HashMap<String, Double> getMeleeWeaponPoints() {
        return meleeWeaponPoints;
    }

    @Override
    public double getPoints(Player player) {
        if(!meleeWeaponPoints.containsKey(player.getUniqueId().toString())) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The player "+player.getName()+" was not found. Adding to the hashmap.");
            meleeWeaponPoints.putIfAbsent(player.getUniqueId().toString(), 0.0);
        }
        return meleeWeaponPoints.get(player.getUniqueId().toString());
    }

    @Override
    public double getPoints(String player) {
        if(!meleeWeaponPoints.containsKey(player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The player with UUID: "+player+" was not found. Adding to the hashmap.");
            meleeWeaponPoints.putIfAbsent(player, 0.0);
        }
        return meleeWeaponPoints.get(player);  }


    /**
     * Checks if the player is contained in the hashmap
     * @param player The player to check for
     * @return true if they are false if they aren't
     */
    public boolean containsPlayer(Player player) {
        return meleeWeaponPoints.containsKey(player.getUniqueId().toString());
    }

    /**
     * Adds the player to the meleeWeaponPoints HashMap.
     * @param player The player in Player format to add
     * @param points Adds this amount of points to the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean addPointsToPlayer(Player player, Double points) {
        String uuid = player.getUniqueId().toString();

        return addPointsToPlayer(uuid, points);
    }

    /**
     * Adds the player to the meleeWeaponPoints HashMap.
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
        PointsAddedEvent pointsAddedEvent = new PointsAddedEvent(UUID.fromString(player), points);
        Bukkit.getPluginManager().callEvent(pointsAddedEvent);
        if(pointsAddedEvent.isCancelled()) {
            return false;
        }
        double multiplierAmount = 1.0;
        Player player1 = Bukkit.getPlayer(UUID.fromString(player));
        if(player1 != null) {
            multiplierAmount = getMultiplier(player1);

        }
        if(meleeWeaponPoints.containsKey(player)) {
            double pointsToAdd = meleeWeaponPoints.get(player) *multiplierAmount;
            meleeWeaponPoints.replace(player, points + pointsToAdd);
            return true;
        } else {
            meleeWeaponPoints.put(player, points*multiplierAmount);
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
        PointsRemovedEvent pointsRemovedEvent = new PointsRemovedEvent(UUID.fromString(player), points);
        Bukkit.getPluginManager().callEvent(pointsRemovedEvent);
        if(pointsRemovedEvent.isCancelled()) {
            return false;
        }
        if(meleeWeaponPoints.containsKey(player)) {
            double pointsToAdd = meleeWeaponPoints.get(player);

            if(pointsToAdd < points) { //If pointsToAdd-points < 0
                return false;//Return false
            }

            meleeWeaponPoints.replace(player, pointsToAdd - points);
            return true;
        } else {
            meleeWeaponPoints.put(player, 0.0);
            return false;
        }
    }

    /**
     * Removes points from the player in the meleeWeaponPoints HashMap.
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
     * Removes points from the player in the meleeWeaponPoints HashMap.
     * @param playerInstance The player to remove points from
     * @param points Removes this amount of points from the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean removePointsFromPlayer(Player playerInstance, Double points) {
        if(points == null)
            points = 0.0;

        String player = playerInstance.getUniqueId().toString();
        return removePointsMethod(player, points);
    }

    @Override
    public String getPointName() {
        return PointsCore.plugin.getConfig().getString( "Points.PointType.MeleeWeaponPoints.Name");
    }

    public void setMultiplier(Player player, Double multiplier) {
        if(this.multiplier.containsKey(player)) {
            this.multiplier.replace(player, multiplier);
        }
        this.multiplier.putIfAbsent(player, multiplier);
    }

    public Double getMultiplier(Player player) {
        if(multiplier.containsKey(player)) {
            return multiplier.get(player);
        }
        return 0.0;
    }
}

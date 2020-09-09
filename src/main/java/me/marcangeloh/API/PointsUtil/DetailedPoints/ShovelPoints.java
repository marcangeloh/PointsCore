package me.marcangeloh.API.PointsUtil.DetailedPoints;

import me.marcangeloh.API.Events.PointsAddedEvent;
import me.marcangeloh.API.Events.PointsRemovedEvent;
import me.marcangeloh.API.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ShovelPoints implements Points {
    private final HashMap<String, Double> shovelPoints;
    private final HashMap<Player, Double> multiplier = new HashMap<>();

    public ShovelPoints() {
        shovelPoints = new HashMap<>();
    }

    public HashMap<String, Double> getShovelPoints() {
        return shovelPoints;
    }

    @Override
    public double getPoints(Player player) {
        String uuid = player.getUniqueId().toString();
        if(!shovelPoints.containsKey(uuid)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The player "+player.getName()+" was not found. Adding to the hashmap.");
            shovelPoints.putIfAbsent(uuid, 0.0);
        }
        return shovelPoints.get(uuid);
    }

    /**
     * Checks if the player is contained in the hashmap
     * @param player The player to check for
     * @return true if they are false if they aren't
     */
    public boolean containsPlayer(Player player) {
        if(shovelPoints.containsKey(player.getUniqueId().toString()))
            return true;

        return false;
    }

    @Override
    public double getPoints(String player) {
        if(!shovelPoints.containsKey(player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The player with UUID: "+player+" was not found. Adding to the hashmap.");
            shovelPoints.putIfAbsent(player, 0.0);
        }
        return shovelPoints.get(player);  }



    /**
     * Adds the player to the shovelPoints HashMap.
     * @param player The player in Player format to add
     * @param points Adds this amount of points to the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean addPointsToPlayer(Player player, Double points) {
        String uuid = player.getUniqueId().toString();
        Message.debugMessage("addPointsToPlayer method executed, with uuid = "+uuid+"\npoints = "+points, DebugIntensity.INTENSE);
        return addPointsToPlayer(uuid, points);
    }



    /**
     * Adds the player to the shovelPoints HashMap.
     * @param player The player's name to add
     * @param points Adds this amount of points to the player (if null will be 0)
     * @return true if the player already is in the hashmap, false if they aren't.
     */
    public boolean addPointsToPlayer(String player, Double points) {
        if(points == null)
            points = 0.0;
        Message.debugMessage("addPointsToPlayer method executed, with uuid = "+player+"\npoints = "+points, DebugIntensity.LIGHT);
        return addPointsMethod(player, points);
    }

    /**
     * Adds points to the player in the hashmap
     * @param player The player in question
     * @param points The amount of points
     * @return True if already exists, False if they don't exist
     */
    private boolean addPointsMethod(String player, Double points) {
        //Debugging
        Bukkit.getServer().getConsoleSender().sendMessage("Giving " + player+ "\n "+points+"Points");
        //Fires the event
        PointsAddedEvent pointsAddedEvent = new PointsAddedEvent(UUID.fromString(player), points);
        Bukkit.getPluginManager().callEvent(pointsAddedEvent);
        //If the event was cancelled cancel adding points
        if(pointsAddedEvent.isCancelled()) {
            return false;
        }

        double multiplierAmount = 1.0;
        Player player1 = Bukkit.getPlayer(UUID.fromString(player));
        if(player1 != null) {
            multiplierAmount = getMultiplier(player1);

        }

        //Adds point to player
        if(shovelPoints.containsKey(player)) {
            double pointsToAdd = shovelPoints.get(player) * multiplierAmount;
            shovelPoints.replace(player, points + pointsToAdd);
            return true;
        } else {
            shovelPoints.put(player, points*multiplierAmount);
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
        //Fires the event
        PointsRemovedEvent pointsRemovedEvent = new PointsRemovedEvent(UUID.fromString(player), points);
        Bukkit.getPluginManager().callEvent(pointsRemovedEvent);
        //Cancel if event was canceled
        if(pointsRemovedEvent.isCancelled()) {
            return false;
        }
        //Adds the points to the player
        if(shovelPoints.containsKey(player)) {
            double pointsToRemove = shovelPoints.get(player);

            if(pointsToRemove < points) { //If pointsToAdd-points < 0
                return false;//Return false
            }

            shovelPoints.replace(player, pointsToRemove - points);
            return true;
        } else {
            shovelPoints.put(player, 0.0);
            return false;
        }
    }

    /**
     * Removes points from the player in the shovelPoints HashMap.
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
     * Removes points from the player in the shovelPoints HashMap.
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
        return PointsCore.plugin.getConfig().getString( "Points.PointType.ShovelPoints.Name");
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

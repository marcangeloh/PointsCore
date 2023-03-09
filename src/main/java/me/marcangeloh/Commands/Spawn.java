package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.TeleportUtil.TeleportUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Spawn implements CommandExecutor {

    Location spawn = null;

    private HashMap<Player, TeleportUtil> noMoveTime;

    private PointsCore pointsCore;

    public Spawn(PointsCore pointsCore, HashMap<Player, TeleportUtil> noMoveTime) {
        this.pointsCore = pointsCore;
        this.spawn = pointsCore.getConfig().getLocation("Spawn.Location");
        this.noMoveTime = noMoveTime;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            Message.errorMessage("You must be a player to use this command.", sender);
            return true;
        }
        if(!label.equalsIgnoreCase("spawn") && !label.equalsIgnoreCase("setspawn")) {
            return false;
        }

        Player player = (Player)  sender;

        if(!GeneralUtil.hasPermission(sender, "spawn") && label.equalsIgnoreCase("spawn")) {
            return true;
        }

        if(!GeneralUtil.hasPermission(sender, "setspawn") &&label.equalsIgnoreCase("setspawn")) {
            return true;
        }

        if(label.equalsIgnoreCase("spawn")) {
            if(spawn == null) {
                Message.errorMessage("Spawn has not yet been set, please contact a server admin.", player);
                return true;
            }

            Integer noMove = pointsCore.getConfig().getInt("Spawn.NoMoveTime", 0);
            if(noMove == 0 || GeneralUtil.hasPermission(player, "spawn.nocooldown")) {
                player.teleport(spawn);
                Message.notifyMessage("You have been teleported to spawn.", player);
            } else {
                noMoveTime.put(player, new TeleportUtil(noMove, spawn));
            }
            return true;
        }

        if(label.equalsIgnoreCase("setspawn")) {
            pointsCore.getConfig().set("Spawn.Location", player.getLocation());
            pointsCore.saveConfig();
            Message.notifyMessage("Spawn position has been set to your location.", player);
            return true;
        }

        return false;
    }
}

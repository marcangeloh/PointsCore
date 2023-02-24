package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class TPA implements CommandExecutor {
    public static HashMap<UUID, Integer> cooldowns = new HashMap<>();
    public static HashMap<UUID, Location> requestedLocation = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("tpa")) {
            return false;
        }

        if (!(commandSender instanceof Player)) {
            Message.errorMessage("You must be a player to use this command", commandSender);
            return true;
        }

        if (!commandSender.hasPermission("pointscore.tpa") || commandSender.hasPermission("pointscore.*") || commandSender.hasPermission("*")) {
            Message.errorMessage("You do not have permission to use this command", commandSender);
            return true;
        }

        if (args.length == 0) {
            Message.errorMessage("Correct command usage is /tpa <playerName>", commandSender);
            return true;
        }


        Player player = (Player) commandSender;
        Player player2 = Bukkit.getPlayer(args[0]);

        if (player2 == null) {
            Message.errorMessage(args[0] + " is not a valid player.", player);
            return true;
        }

        Location location = player.getLocation();
        cooldowns.put(player2.getUniqueId(), PointsCore.plugin.getConfig().getInt("TPA.Cooldown",3));
        Message.notifyMessage(player.getDisplayName()+" has requested to teleport to you. " +
                "\nPlease type /tpaccept to accept, or /tpdeny to deny the request.",player2);
        requestedLocation.put(player.getUniqueId(), location);
        return true;

    }
}

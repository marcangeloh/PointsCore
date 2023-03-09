package me.marcangeloh.Commands.TeleportCommands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.TeleportUtil.TPARequest;
import me.marcangeloh.API.Util.TeleportUtil.HashMapUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class TPA implements CommandExecutor {

    private HashMapUtil hashMapUtil;
    private PointsCore pointsCore;
    public TPA(PointsCore pointsCore, HashMapUtil hashMapUtil) {
        this.pointsCore = pointsCore;
        this.hashMapUtil = hashMapUtil;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("tpa") && !command.getName().equalsIgnoreCase("tpahere")) {
            return false;
        }

        if (!(commandSender instanceof Player)) {
            Message.errorMessage("You must be a player to use this command", commandSender);
            return true;
        }

        if (!GeneralUtil.hasPermission(commandSender,"tpa")) {
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

        hashMapUtil.teleportMap.put(player2,
                new TPARequest(player, player2, pointsCore.getConfig().getInt("TPA.Cooldown", 3),
                pointsCore.getConfig().getInt("TPA.NoMoveTime"),
                false));

        if(s.equalsIgnoreCase("tpahere")) {
            Message.notifyMessage("You have requested "+player2.getDisplayName()+" to teleport to you. Type /tpcancel to cancel the request.", player);
            Message.notifyMessage(player.getDisplayName() + " has requested that you teleport to them. " +
                    "\nPlease type /tpaccept to accept, or /tpdeny to deny the request.", player2);
            hashMapUtil.teleportMap.get(player2).isInverted = true;
        } else if(command.getName().equalsIgnoreCase("tpa")) {
            Message.notifyMessage("You have requested to teleport to "+player2.getDisplayName()+". Type /tpcancel to cancel the request.", player);
            Message.notifyMessage(player.getDisplayName() + " has requested to teleport to you. " +
                    "\nPlease type /tpaccept to accept, or /tpdeny to deny the request.", player2);
        }
        return true;

    }
}

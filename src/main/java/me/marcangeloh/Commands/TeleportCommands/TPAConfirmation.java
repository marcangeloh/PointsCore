package me.marcangeloh.Commands.TeleportCommands;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.HashMapUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TPAConfirmation implements CommandExecutor {
    private HashMapUtil hashMapUtil;
    public TPAConfirmation(HashMapUtil hashMapUtil) {
        this.hashMapUtil = hashMapUtil;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            Message.errorMessage("This command can only be used by a player.", commandSender);
            return true;
        }

        Player player = (Player) commandSender;

        if(label.equalsIgnoreCase("tpdeny")) {
            Message.notifyMessage("You have denied the tp request.", player);
            if(containsKey(player)) {
                hashMapUtil.teleportMap.remove(player);
            }
            return true;
        }

        if(label.equalsIgnoreCase("tpca")) {
            Message.notifyMessage("You have denied the tp request.", player);
            for(Player player1: hashMapUtil.teleportMap.keySet()) {
                if(!hashMapUtil.teleportMap.get(player1).player.equals(player)) {
                    continue;
                }

                hashMapUtil.teleportMap.remove(player1);
                return true;
            }
            return true;
        }

        if(!containsKey(player)) {
            Message.errorMessage("You do not have any pending tpa request.", player);
            return true;
        }
        Player player1 = hashMapUtil.teleportMap.get(player).player;

        if(PointsCore.plugin.getConfig().getInt("TPA.NoMoveTime",3) == 0) {

            if(hashMapUtil.teleportMap.get(player).isInverted) {
                Message.notifyMessage("You have successfully been teleported.", player1);
                player1.teleport(player.getLocation());
                hashMapUtil.teleportMap.remove(player);
            } else {
                Message.notifyMessage("You have successfully been teleported.", player);
                player.teleport(player1.getLocation());
                hashMapUtil.teleportMap.remove(player);
            }
            return true;
        }

        hashMapUtil.teleportMap.get(player).isConfirmed = true;
        return true;

    }

    private boolean containsKey(Player player) {
        for(Player player1: hashMapUtil.teleportMap.keySet()) {
            if(!player1.equals(player)) {
                continue;
            }
            return true;
        }
        return false;
    }
}

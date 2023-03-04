package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player) && args.length == 0) {
            Message.errorMessage("You must specify the player to enable flight.", commandSender);
            return true;
        }


        Player player;
        if(args.length == 0) {

            if(!GeneralUtil.hasPermission(commandSender, "fly")) {
                return true;
            }

            player = (Player) commandSender;

        } else {
            if(!GeneralUtil.hasPermission(commandSender, "fly.others")) {
                return true;
            }
            player = Bukkit.getPlayer(args[0]);
        }


        if(player == null) {
            Message.notifyMessage("Player is invalid, correct command usage is: \n/fly <player>",commandSender);
            return true;
        }

        player.setAllowFlight(!player.getAllowFlight());

        String message = "Flight has been " + (player.getAllowFlight() ? ChatColor.GREEN+"enabled." : ChatColor.RED+"disabled.");
        Message.notifyMessage(message, player);
        return true;
    }
}

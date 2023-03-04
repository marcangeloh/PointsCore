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

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!command.getName().equalsIgnoreCase("heal")) {
            return false;
        }

        Player player;
        if(strings.length == 0) {
            if(!GeneralUtil.hasPermission(commandSender, "heal")) {
                return true;
            }

            if(!(commandSender instanceof Player)) {
                Message.errorMessage("You forgot to specify the player.", commandSender);
                return true;
            }
            player = (Player) commandSender;
        } else {
            if(!GeneralUtil.hasPermission(commandSender, "heal.others")) {
                return true;
            }
            player = Bukkit.getPlayer(strings[0]);
            if(player == null) {
                Message.errorMessage("The player is invalid, please make sure they are online.", commandSender);
                return true;
            }
        }

        player.setHealth(player.getHealthScale());
        Message.notifyMessage("You have been "+ ChatColor.GREEN+"healed.", player);
        return true;
    }
}

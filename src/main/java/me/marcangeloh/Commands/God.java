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

public class God implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!command.getName().equalsIgnoreCase("godmode") && !command.getName().equalsIgnoreCase("god")) {
            return false;
        }


        Player player;
        if(strings.length == 0) {
            if(!GeneralUtil.hasPermission(commandSender, "god")) {
                return true;
            }

            if(!(commandSender instanceof Player)) {
                Message.errorMessage("You must specify the player.", commandSender);
                return true;
            }

            player = (Player) commandSender;

        } else {
            if(!GeneralUtil.hasPermission(commandSender, "god.others")) {
                return true;
            }
            player = Bukkit.getPlayer(strings[0]);
            if(player == null) {
                Message.errorMessage("This player is invalid", commandSender);
                return true;
            }
        }

        player.setInvulnerable(!player.isInvulnerable());
        Message.notifyMessage("God Mode is now "+(player.isInvulnerable() ? ChatColor.GREEN + "enabled." : ChatColor.RED + "disabled."), player);
        return true;
    }
}

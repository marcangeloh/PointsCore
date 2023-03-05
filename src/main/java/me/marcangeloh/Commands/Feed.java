package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!label.equalsIgnoreCase("feed")) {
            return false;
        }

        if(args.length == 0 && !(sender instanceof Player)) {
            Message.errorMessage("You must select a player to use this command.", sender);
            return true;
        }



        Player player;

        if(args.length > 0) {
            if(!GeneralUtil.hasPermission(sender, "feed.others")) {
                return true;
            }

            for (String arg: args
                 ) {
                player = Bukkit.getPlayer(arg);

                if(player == null) {
                    Message.errorMessage(arg + " is not a valid player.", sender);
                    continue;
                }
                player.setFoodLevel(20);
                Message.notifyMessage("Your hunger bar has been filled up.", player);
                Message.notifyMessage("You have sated "+player.getDisplayName(), sender);
            }
        } else {
            if(!GeneralUtil.hasPermission(sender, "feed")) {
                return true;
            }

            player = (Player) sender;
            player.setFoodLevel(20);
            Message.notifyMessage("Your hunger bar has been filled up.", player);
            Message.notifyMessage("You have sated "+player.getDisplayName(), sender);
        }
        return true;
    }
}

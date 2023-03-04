package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!command.getName().equalsIgnoreCase("broadcast") && !command.getName().equalsIgnoreCase("bc")) {
            return false;
        }

        //Checks for permisssion
        if(!GeneralUtil.hasPermission(sender,"broacast") ) {
            return true;
        }

        //Add all arguments into string
        String str = "";
        for(String arg: args) {
            str += " "+ arg;
        }

        //Handles the Brocast prefix
        str = PointsCore.plugin.getConfig().getString("Broadcast.Prefix") + str.replaceFirst(" ", "");

        //Gets All players from the server
        for (Player player: Bukkit.getServer().getOnlinePlayers()
             ) {
            //Sends message to them
            player.sendMessage(Message.format(str));
        }
        return true;
    }
}

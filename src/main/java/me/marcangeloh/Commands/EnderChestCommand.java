package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnderChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!label.equalsIgnoreCase("enderchest") && !label.equalsIgnoreCase("echest")) {
            return false;
        }

        if(!(sender instanceof Player)) {
            Message.errorMessage("You must be a player to use this command.", sender);
            return true;
        }

        if(!GeneralUtil.hasPermission(sender,"enderchest")) {
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(player.getEnderChest());
        return true;
    }
}

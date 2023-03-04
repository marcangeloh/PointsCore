package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Vanish implements CommandExecutor {
    HashMap<Player, ItemStack[]> armorAndHandMap = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!label.equalsIgnoreCase("vanish") &&
            !label.equalsIgnoreCase("v")) {
            return false;
        }

        if(!GeneralUtil.hasPermission(sender, "vanish")) {
            return true;
        }

        if(!(sender instanceof Player )&&args.length==0) {
            Message.errorMessage("You need to specify a player", sender);
            return true;
        }
        Player player;
        if(args.length == 0) {
            player = (Player) sender;
        } else {
            player = Bukkit.getPlayer(args[0]);
            if(player == null) {
                Message.errorMessage("Invalid player provided", sender);
            }
        }

        int length = player.getInventory().getArmorContents().length+2;
        ItemStack[] armor = new ItemStack[length];

        if(armorAndHandMap.containsKey(player)) {
            armor = armorAndHandMap.get(player);
            ItemStack[] temp = new ItemStack[length-2];
            for (int i = 0; i < length-2; i ++){
                temp[i] = armor[i];
            }
            player.getInventory().setArmorContents(temp);
            player.getInventory().setItemInMainHand(armor[length-2]);
            player.getInventory().setItemInOffHand(armor[length-1]);
            armorAndHandMap.remove(player);
            Message.notifyMessage("You are now visible.", player);
            player.setInvisible(false);
        } else {
            for (int i = 0; i < length-2; i ++){
                armor[i] = player.getInventory().getArmorContents()[i];
            }
            armor[length-2] = player.getInventory().getItemInMainHand();
            armor[length-1] = player.getInventory().getItemInOffHand();
            player.getInventory().setArmorContents(new ItemStack[length-2]);
            player.getInventory().setItemInMainHand(null);
            player.getInventory().setItemInOffHand(null);
            armorAndHandMap.put(player, armor);
            Message.notifyMessage("You are invisible.", player);
            player.setInvisible(true);
        }
        return true;
    }
}

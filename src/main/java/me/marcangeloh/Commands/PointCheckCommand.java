package me.marcangeloh.Commands;

import me.marcangeloh.PointsCore;
import me.marcangeloh.Util.GeneralUtil.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointCheckCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if(args.length == 0 && !(sender instanceof  Player)) {
            //Error occurred
            Message.errorMessage("You must include a player when executing this from console. i.e /pointcheck <name>", sender);
            return false;
        }

        if(!cmd.getName().equalsIgnoreCase("pointcheck")||cmd.getName().equalsIgnoreCase("pc")) {
            return false;
        }

        if((sender instanceof Player) && args.length == 0) {
            Player player = (Player) sender;
            Message.notifyMessage(player.getDisplayName()+" has: "+pointsToString(player), player);
            return true;
        }

        if(args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if(player == null) {
                Message.errorMessage("Invalid player name please try again.", sender);
                return false;
            }
            Message.notifyMessage(player.getDisplayName()+" has:"+pointsToString(player), sender);
            return true;
        } else {
            Message.errorMessage("Invalid number of arguments, proper command usage is /pointcheck <playername>", sender);
            return false;
        }

    }

    private String pointsToString(Player player) {
        return "\n" + ChatColor.DARK_GREEN + PointsCore.playerPoints.shovelPoints.getPoints(player) + ChatColor.GOLD+ " Shovel Points." +
                "\n" +ChatColor.DARK_GREEN + PointsCore.playerPoints.axePoints.getPoints(player) + ChatColor.GOLD+ " Axe Points."+
                "\n"+ChatColor.DARK_GREEN + PointsCore.playerPoints.pickaxePoints.getPoints(player) + ChatColor.GOLD+ " Pickaxe Points."+
                "\n"+ChatColor.DARK_GREEN + PointsCore.playerPoints.hoePoints.getPoints(player) + ChatColor.GOLD+ " Hoe Points."+
                "\n"+ChatColor.DARK_GREEN + PointsCore.playerPoints.fishingPoints.getPoints(player) + ChatColor.GOLD+ " Fishing Points."+
                "\n"+ChatColor.DARK_GREEN + PointsCore.playerPoints.armorPoints.getPoints(player) + ChatColor.GOLD+ " Armor Points."+
                "\n"+ChatColor.DARK_GREEN + PointsCore.playerPoints.meleeWeaponPoints.getPoints(player) + ChatColor.GOLD+ " Melee Weapon Points."+
                "\n"+ChatColor.DARK_GREEN + PointsCore.playerPoints.rangedWeaponPoints.getPoints(player) + ChatColor.GOLD+ " Ranged Weapon Points.";
    }
}

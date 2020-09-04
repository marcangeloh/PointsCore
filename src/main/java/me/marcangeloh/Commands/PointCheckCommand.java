package me.marcangeloh.Commands;

import me.marcangeloh.PointsCore;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointCheckCommand implements CommandExecutor {
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;
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

        if(args.length <= 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if(player == null) {
                Message.errorMessage("Invalid player name please try again.", sender);
                return false;
            }
            Message.notifyMessage(player.getDisplayName()+" has:"+pointsToString(player), sender);
            return true;
        }
        return false;

    }

    private String pointsToString(Player player) {
        int decimalPlaces = PointsCore.plugin.getConfig().getInt("Points.PointsDecimalPlaces");
        String symbol = " "+PointsCore.plugin.getConfig().getString("Points.PointsSymbol");

        return "\n" + ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.shovelPoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ " "+pointsCore.playerPoints.getPointNameFromToolType(Tools.SHOVEL)+
                "\n" +ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.axePoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ " "+pointsCore.playerPoints.getPointNameFromToolType(Tools.AXE)+
                "\n"+ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.pickaxePoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ " "+pointsCore.playerPoints.getPointNameFromToolType(Tools.PICKAXE)+
                "\n"+ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.hoePoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ "  "+pointsCore.playerPoints.getPointNameFromToolType(Tools.HOE)+
                "\n"+ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.fishingPoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ " "+pointsCore.playerPoints.getPointNameFromToolType(Tools.FISH_ROD)+
                "\n"+ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.armorPoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ " "+pointsCore.playerPoints.getPointNameFromToolType(Tools.ARMOR)+
                "\n"+ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.meleeWeaponPoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ " "+pointsCore.playerPoints.getPointNameFromToolType(Tools.MELEE_WEAPON)+
                "\n"+ChatColor.DARK_GREEN + roundAvoid(pointsCore.playerPoints.rangedWeaponPoints.getPoints(player),decimalPlaces) +symbol+ ChatColor.GOLD+ " "+pointsCore.playerPoints.getPointNameFromToolType(Tools.RANGED_WEAPON);
    }



    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}

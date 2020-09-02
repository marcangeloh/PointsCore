package me.marcangeloh.Commands;

import me.marcangeloh.API.PointsUtil.DetailedPoints.Points;
import me.marcangeloh.PointsCore;
import me.marcangeloh.Util.GeneralUtil.CooldownUtil;
import me.marcangeloh.Util.GeneralUtil.Message;
import me.marcangeloh.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;

public class PointsCoreCommands implements CommandExecutor {
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {
        if(!command.getName().equalsIgnoreCase("points")) {
            return false;
        }

        if(args.length == 0) {
            sendHelpMessage(sender);
            return true;
        }

        if(!sender.hasPermission("Points.admin")) {
            Message.errorMessage("You do not have permission to execute this command.", sender);
            return true;
        }

        if(args[0].equalsIgnoreCase("help")) {
            sendHelpMessage(sender);
            return true;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            PointsCore.plugin.reloadConfig();
            return true;
        }

        if(args.length != 4) {
            sendHelpMessage(sender);
            return true;
        }

        Player player = Bukkit.getPlayer(args[1]);
        double amount;

        try {
            amount= Double.parseDouble(args[3]);
        }catch (NumberFormatException exception) {
            Message.errorMessage("Invalid amount entered.", sender);
            return true;
        }

        if(player == null) {
            Message.errorMessage("That player is invalid.", sender);
            return true;
        }

        if(args[0].equalsIgnoreCase("add") ||args[0].equalsIgnoreCase("increase")||args[0].equalsIgnoreCase("increment")) {

            if(args[2].equalsIgnoreCase("shovel") ||args[2].equalsIgnoreCase("spade")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.SHOVEL, player, amount);
            } else if(args[2].equalsIgnoreCase("ranged") ||args[2].equalsIgnoreCase("rangedweapons")||args[2].equalsIgnoreCase("rw")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.RANGED_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("pick") ||args[2].equalsIgnoreCase("pickaxe")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.PICKAXE, player, amount);
            }else if(args[2].equalsIgnoreCase("melee") ||args[2].equalsIgnoreCase("meleeweapons")||args[2].equalsIgnoreCase("mw")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.MELEE_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("hoe")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.HOE, player, amount);
            }else if(args[2].equalsIgnoreCase("fishing")||args[2].equalsIgnoreCase("fish")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.FISH_ROD, player, amount);
            }else if(args[2].equalsIgnoreCase("axe")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.AXE, player, amount);
            }else if(args[2].equalsIgnoreCase("armor")
                    ||args[2].equalsIgnoreCase("chestplate")
                    ||args[2].equalsIgnoreCase("helmet")
                    ||args[2].equalsIgnoreCase("leggings")
                    ||args[2].equalsIgnoreCase("protection")
                    ||args[2].equalsIgnoreCase("boots")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.ARMOR, player, amount);
            }

            return true;
        }
        if(args[0].equalsIgnoreCase("remove") ||args[0].equalsIgnoreCase("subtract")||args[0].equalsIgnoreCase("sub")) {

            if(args[2].equalsIgnoreCase("shovel") ||args[2].equalsIgnoreCase("spade")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.SHOVEL, player, amount);
            } else if(args[2].equalsIgnoreCase("ranged") ||args[2].equalsIgnoreCase("rangedweapons")||args[2].equalsIgnoreCase("rw")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.RANGED_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("pick") ||args[2].equalsIgnoreCase("pickaxe")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.PICKAXE, player, amount);
            }else if(args[2].equalsIgnoreCase("melee") ||args[2].equalsIgnoreCase("meleeweapons")||args[2].equalsIgnoreCase("mw")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.MELEE_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("hoe")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.HOE, player, amount);
            }else if(args[2].equalsIgnoreCase("fishing")||args[2].equalsIgnoreCase("fish")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.FISH_ROD, player, amount);
            }else if(args[2].equalsIgnoreCase("axe")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.AXE, player, amount);
            }else if(args[2].equalsIgnoreCase("armor")
                    ||args[2].equalsIgnoreCase("chestplate")
                    ||args[2].equalsIgnoreCase("helmet")
                    ||args[2].equalsIgnoreCase("leggings")
                    ||args[2].equalsIgnoreCase("protection")
                    ||args[2].equalsIgnoreCase("boots")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.ARMOR, player, amount);
            }


            Message.notifyMessage("Successfully removed " + amount+ args[2]+" from "+ args[1], sender);
            Message.notifyMessage("You have lost " + amount+ args[2]+" from "+sender.getName(), player);
            return true;
        }

        if(args[0].equalsIgnoreCase("multiplier")||args[0].equalsIgnoreCase("multi")) {
            if(pointsCore.playerPoints.multiplierMap.containsKey(player.getUniqueId())) {
                pointsCore.playerPoints.multiplierMap.get(player.getUniqueId()).setEndTime(args[2]);
                pointsCore.playerPoints.multiplierMap.get(player.getUniqueId()).setMultiplier(amount);
            } else{
                pointsCore.playerPoints.multiplierMap.put(player.getUniqueId(), new CooldownUtil(args[2], amount));
            }
            return true;
        }
        return false;
    }

    private void sendHelpMessage(CommandSender sender) {
        Message.notifyMessage("To use points core you can use the following commands", sender);
        Message.notifyMessage("/points add <player> <type> <amount>", sender);
        Message.notifyMessage("/points multiplier <player> <duration> <amount>", sender);
        Message.notifyMessage("/points remove <player> <type> <amount>", sender);
        Message.notifyMessage("/points reload", sender);
        Message.notifyMessage("/points help", sender);
    }
}

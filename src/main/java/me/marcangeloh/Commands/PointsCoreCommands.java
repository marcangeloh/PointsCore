package me.marcangeloh.Commands;

import me.marcangeloh.API.PointsUtil.DetailedPoints.Points;
import me.marcangeloh.PointsCore;
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
                PointsCore.playerPoints.addPointsToToolType(Tools.SHOVEL, player, amount);
            } else if(args[2].equalsIgnoreCase("ranged") ||args[2].equalsIgnoreCase("rangedweapons")||args[2].equalsIgnoreCase("rw")) {
                PointsCore.playerPoints.addPointsToToolType(Tools.RANGED_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("pick") ||args[2].equalsIgnoreCase("pickaxe")) {
                PointsCore.playerPoints.addPointsToToolType(Tools.PICKAXE, player, amount);
            }else if(args[2].equalsIgnoreCase("melee") ||args[2].equalsIgnoreCase("meleeweapons")||args[2].equalsIgnoreCase("mw")) {
                PointsCore.playerPoints.addPointsToToolType(Tools.MELEE_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("hoe")) {
                PointsCore.playerPoints.addPointsToToolType(Tools.HOE, player, amount);
            }else if(args[2].equalsIgnoreCase("fishing")||args[2].equalsIgnoreCase("fish")) {
                PointsCore.playerPoints.addPointsToToolType(Tools.FISH_ROD, player, amount);
            }else if(args[2].equalsIgnoreCase("axe")) {
                PointsCore.playerPoints.addPointsToToolType(Tools.AXE, player, amount);
            }else if(args[2].equalsIgnoreCase("armor")
                    ||args[2].equalsIgnoreCase("chestplate")
                    ||args[2].equalsIgnoreCase("helmet")
                    ||args[2].equalsIgnoreCase("leggings")
                    ||args[2].equalsIgnoreCase("protection")
                    ||args[2].equalsIgnoreCase("boots")) {
                PointsCore.playerPoints.addPointsToToolType(Tools.ARMOR, player, amount);
            }

            return true;
        }
        if(args[0].equalsIgnoreCase("remove") ||args[0].equalsIgnoreCase("subtract")||args[0].equalsIgnoreCase("sub")) {

            if(args[2].equalsIgnoreCase("shovel") ||args[2].equalsIgnoreCase("spade")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.SHOVEL, player, amount);
            } else if(args[2].equalsIgnoreCase("ranged") ||args[2].equalsIgnoreCase("rangedweapons")||args[2].equalsIgnoreCase("rw")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.RANGED_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("pick") ||args[2].equalsIgnoreCase("pickaxe")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.PICKAXE, player, amount);
            }else if(args[2].equalsIgnoreCase("melee") ||args[2].equalsIgnoreCase("meleeweapons")||args[2].equalsIgnoreCase("mw")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.MELEE_WEAPON, player, amount);
            }else if(args[2].equalsIgnoreCase("hoe")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.HOE, player, amount);
            }else if(args[2].equalsIgnoreCase("fishing")||args[2].equalsIgnoreCase("fish")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.FISH_ROD, player, amount);
            }else if(args[2].equalsIgnoreCase("axe")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.AXE, player, amount);
            }else if(args[2].equalsIgnoreCase("armor")
                    ||args[2].equalsIgnoreCase("chestplate")
                    ||args[2].equalsIgnoreCase("helmet")
                    ||args[2].equalsIgnoreCase("leggings")
                    ||args[2].equalsIgnoreCase("protection")
                    ||args[2].equalsIgnoreCase("boots")) {
                PointsCore.playerPoints.removePointsToToolType(Tools.ARMOR, player, amount);
            }

            Message.notifyMessage("Successfully added " + amount+ args[2]+" to "+ args[1], sender);
            Message.notifyMessage("You have received " + amount+ args[2]+" from "+sender.getName(), player);
            return true;
        }
        return false;
    }

    private void sendHelpMessage(CommandSender sender) {
        Message.notifyMessage("To use points core you can use the following commands", sender);
        Message.notifyMessage("/points add <player> <type> <amount>", sender);
        Message.notifyMessage("/points remove <player> <type> <amount>", sender);
        Message.notifyMessage("/points reload", sender);
        Message.notifyMessage("/points help", sender);
    }
}

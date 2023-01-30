package me.marcangeloh.Commands;

import me.marcangeloh.PointsCore;
import me.marcangeloh.API.Util.GeneralUtil.CooldownUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PointsCoreCommands implements CommandExecutor {
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String lbl, String[] args) {
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
            Message.notifyMessage("Config Reloaded", sender);
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
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" shovel points", sender);
                Message.notifyMessage("You've received "+amount+" shovel points", sender);
            } else if(args[2].equalsIgnoreCase("ranged") ||
                    args[2].equalsIgnoreCase("rangedweapons")||
                    args[2].equalsIgnoreCase("rw") ||
                    args[2].equalsIgnoreCase("bow") ||
                    args[2].equalsIgnoreCase("crossbow") ||
                    args[2].equalsIgnoreCase("trident")
            ) {
                pointsCore.playerPoints.addPointsToToolType(Tools.RANGED_WEAPON, player, amount);
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" ranged points", sender);
                Message.notifyMessage("You've received "+amount+" ranged points", sender);
            }else if(args[2].equalsIgnoreCase("pick") ||args[2].equalsIgnoreCase("pickaxe")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.PICKAXE, player, amount);
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" pickaxe points", sender);
                Message.notifyMessage("You've received "+amount+" pickaxe points", sender);
            }else if(args[2].equalsIgnoreCase("melee") ||args[2].equalsIgnoreCase("meleeweapons")||
                    args[2].equalsIgnoreCase("mw") || args[2].contains("sword")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.MELEE_WEAPON, player, amount);
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" melee points", sender);
                Message.notifyMessage("You've received "+amount+" melee points", sender);
            }else if(args[2].equalsIgnoreCase("hoe")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.HOE, player, amount);
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" hoe points", sender);
                Message.notifyMessage("You've received "+amount+" hoe points", sender);
            }else if(args[2].equalsIgnoreCase("fishing")||args[2].equalsIgnoreCase("fish")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.FISH_ROD, player, amount);
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" fishing points", sender);
                Message.notifyMessage("You've received "+amount+" fishing points", sender);
            }else if(args[2].equalsIgnoreCase("axe")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.AXE, player, amount);
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" axe points", sender);
                Message.notifyMessage("You've received "+amount+" axe points", sender);
            }else if(args[2].contains("armor")
                    ||args[2].contains("chest")
                    ||args[2].contains("helm")
                    ||args[2].contains("leggin")
                    ||args[2].contains("protect")
                    ||args[2].contains("boot")) {
                pointsCore.playerPoints.addPointsToToolType(Tools.ARMOR, player, amount);
                Message.notifyMessage("You've given "+player.getDisplayName()+" "+amount+" armor points", sender);
                Message.notifyMessage("You've received "+amount+" armor points", sender);
            }

            return true;
        }
        if(args[0].equalsIgnoreCase("remove") ||args[0].equalsIgnoreCase("subtract")||args[0].equalsIgnoreCase("sub")) {

            if(args[2].equalsIgnoreCase("shovel") ||args[2].equalsIgnoreCase("spade")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.SHOVEL, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" shovel points", sender);
                Message.notifyMessage("You've lost "+amount+" shovel points", sender);
            } else if(args[2].equalsIgnoreCase("ranged") ||args[2].equalsIgnoreCase("rangedweapons")||args[2].equalsIgnoreCase("rw")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.RANGED_WEAPON, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" ranged points", sender);
                Message.notifyMessage("You've lost "+amount+" ranged points", sender);
            }else if(args[2].equalsIgnoreCase("pick") ||args[2].equalsIgnoreCase("pickaxe")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.PICKAXE, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" pickaxe points", sender);
                Message.notifyMessage("You've lost "+amount+" pickaxe points", sender);
            }else if(args[2].equalsIgnoreCase("melee") ||args[2].equalsIgnoreCase("meleeweapons")||args[2].equalsIgnoreCase("mw")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.MELEE_WEAPON, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" melee points", sender);
                Message.notifyMessage("You've lost "+amount+" melee points", sender);
            }else if(args[2].equalsIgnoreCase("hoe")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.HOE, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" hoe points", sender);
                Message.notifyMessage("You've lost "+amount+" hoe points", sender);
            }else if(args[2].equalsIgnoreCase("fishing")||args[2].equalsIgnoreCase("fish")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.FISH_ROD, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" fishing points", sender);
                Message.notifyMessage("You've lost "+amount+" fishing points", sender);
            }else if(args[2].equalsIgnoreCase("axe")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.AXE, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" axe points", sender);
                Message.notifyMessage("You've lost "+amount+" axe points", sender);
            }else if(args[2].equalsIgnoreCase("armor")
                    ||args[2].equalsIgnoreCase("chestplate")
                    ||args[2].equalsIgnoreCase("helmet")
                    ||args[2].equalsIgnoreCase("leggings")
                    ||args[2].equalsIgnoreCase("protection")
                    ||args[2].equalsIgnoreCase("boots")) {
                pointsCore.playerPoints.removePointsToToolType(Tools.ARMOR, player, amount);
                Message.notifyMessage("You've removed "+amount+" from "+player.getDisplayName()+" armor points", sender);
                Message.notifyMessage("You've lost "+amount+" armor points", sender);
            }


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

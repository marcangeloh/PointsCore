package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.TeleportUtil.HomeUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.TeleportUtil.TeleportUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Home implements CommandExecutor {

    HashMap<Player, TeleportUtil> noMoveTime;
    private PointsCore pointsCore;
    public Home(PointsCore pointsCore, HashMap<Player, TeleportUtil> noMoveTime) {
        this.pointsCore = pointsCore;
        this.noMoveTime = noMoveTime;
    }

    HashMap<Player, HomeUtil> homeUtils = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            Message.errorMessage("This command can only be executed by a player.", sender);
            return true;
        }

        if(!label.equalsIgnoreCase("home") && !label.equalsIgnoreCase("sethome")) {
            return false;
        }


        Player player = (Player) sender;


        if(!homeUtils.containsKey(player)) {
            homeUtils.putIfAbsent(player, new HomeUtil(pointsCore, player));
        }

        String homeName;
        if(args.length == 0) {
            homeName = "home";
        } else {
            homeName = args[0];
        }

        if(label.equalsIgnoreCase("home")) {


            if(homeUtils.get(player).getHomeLocation(homeName) == null) {
                Message.notifyMessage("That house was not found. Was it set?", player);
                return true;
            }

            Integer noMove = pointsCore.getConfig().getInt("Home.NoMoveTime", 0);
            if(noMove == 0) {
                player.teleport(homeUtils.get(player).getHomeLocation(homeName));
                Message.notifyMessage("Successfully teleported you home!", player);
            } else {
                noMoveTime.put(player, new TeleportUtil(noMove, homeUtils.get(player).getHomeLocation(homeName)));
            }

            return true;
        }

        if(label.equalsIgnoreCase("sethome")) {
            int maxAllowed = getNumberOfHomesAllowed(player);
            if( maxAllowed< homeUtils.get(player).getPlayerHomes()) {
                Message.errorMessage("You do not have permission to set more than "+maxAllowed+" houses.",player);
                return true;
            }

            //Sets the home of the player
            homeUtils.get(player).addPlayerHome(homeName, player.getLocation());
            homeUtils.get(player).savePlayerHomes();
            Message.notifyMessage("Your houses' location has successfully been set.", player);
            return true;
        }

        if(label.equalsIgnoreCase("delhome")) {
            if(homeUtils.get(player).containsHome(homeName)) {
                Message.notifyMessage("Successfully deleted home "+ homeName, player);
                homeUtils.get(player).removePlayerHome(homeName);
                homeUtils.get(player).savePlayerHomes();
                return true;
            }
        }

        return false;
    }


    public int getNumberOfHomesAllowed(Player player) {
        PermissionAttachment attachment = player.addAttachment(pointsCore);
        int maxHomes = -1;

        if(GeneralUtil.hasPermission(player, ""))
            return 200;

        for (int i = 1; ; i++) {
            attachment.setPermission("pointscore.home." + i, true);
            if (!player.hasPermission("pointscore.home." + i)) {
                break;
            }
            maxHomes = i;
        }

        player.removeAttachment(attachment);
        return maxHomes == -1 ? Integer.MAX_VALUE : maxHomes;
    }
}

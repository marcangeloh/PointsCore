package me.marcangeloh.Commands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.HomeUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.TeleportUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Home implements CommandExecutor {

    HashMap<Player, TeleportUtil> noMoveTime;

    public Home(HashMap<Player, TeleportUtil> noMoveTime) {
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


        if(!GeneralUtil.hasPermission(sender, "home")) {
            return true;
        }

        Player player = (Player) sender;


        if(!homeUtils.containsKey(player)) {
            homeUtils.putIfAbsent(player, new HomeUtil(player));
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

            Integer noMove = PointsCore.plugin.getConfig().getInt("Home.NoMoveTime", 0);
            if(noMove == 0) {
                player.teleport(homeUtils.get(player).getHomeLocation(homeName));
                Message.notifyMessage("Successfully teleported you home!", player);
            } else {
                noMoveTime.put(player, new TeleportUtil(noMove, homeUtils.get(player).getHomeLocation(homeName)));
            }

            return true;
        }

        if(label.equalsIgnoreCase("sethome")) {
            //Sets the home of the player
            homeUtils.get(player).addPlayerHome(homeName, player.getLocation());
            homeUtils.get(player).savePlayerHomes();
            Message.notifyMessage("You're houses' location has successfully been set.", player);
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

}

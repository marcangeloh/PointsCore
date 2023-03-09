package me.marcangeloh.Commands.TeleportCommands;

import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class RandomTP implements CommandExecutor {
    private PointsCore pointsCore;

    public RandomTP(PointsCore pointsCore) {
        this.pointsCore = pointsCore;
    }

    public static HashMap<UUID, Integer> cooldown = new HashMap<UUID, Integer>();
    public boolean onCommand( CommandSender sender, Command command, String s, String[] args) {
        if(!command.getName().equalsIgnoreCase("rtp") && !command.getName().equalsIgnoreCase("randomtp") && !command.getName().equalsIgnoreCase("wild"))
            return false;

        if(!(sender instanceof Player) && args.length == 0) {
            Message.notifyMessage("You must type in the name of the players you wish to tp", sender);
            return true;
        }

        if(args.length > 0) {
            if (sender.hasPermission("pointscore.*") || sender.hasPermission("*") || sender.hasPermission("pointscore.rtp.others"))

            for (String arg: args
                 ) {
                Player player = Bukkit.getPlayer(arg);
                randomTp(player);
            }

            Message.notifyMessage("You've successfully teleported the players.", sender);
            return true;
        }

        randomTp((Player) sender);
        return true;
    }

    private void randomTp(Player player) {
        Location location = new Location(player.getWorld(), getMax("X"), 180, getMax("Z"));
        location.setY(player.getWorld().getHighestBlockYAt(location));

        if(!GeneralUtil.hasPermission(player,"rtp")) {
            return;
        }

        if(!isLocationSafe(location)) {
            randomTp(player);
            return;
        }

        if(!GeneralUtil.hasPermission(player,"rtp.nocooldown")) {
            if(cooldown.containsKey(player.getUniqueId())) {
                Message.errorMessage("rtp is still on cooldown for " +cooldown.get(player.getUniqueId()) + " seconds", player);
                return;
            }
            cooldown.put(player.getUniqueId(), pointsCore.getConfig().getInt("RandomTP.Cooldown", 10));
        }
        player.teleport(location);
        Message.notifyMessage("You were successfuly teleported to the wild.", player);
    }

    private boolean isLocationSafe(Location location) {
        ArrayList<String> badBlocks = (ArrayList<String>) pointsCore.getConfig().getStringList("RandomTP.DangerousBlocks");

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getBlock();
        Block below = location.getWorld().getBlockAt(x,y-1,z);
        Block above = location.getWorld().getBlockAt(x,y+1,z);


        for(String e: badBlocks){
            if(Material.getMaterial(e) == below.getType())
                return false;
        }

        if(above.getType().isSolid() || block.getType().isSolid())
            return false;

        return true;
    }

    private int getMax(String type) {
        Random random = new Random();
        int max = pointsCore.getConfig().getInt("RandomTP.Max"+type);
        int min = pointsCore.getConfig().getInt("RandomTP.Min"+type);
        return random.nextInt(max - min) + min;
    }
}

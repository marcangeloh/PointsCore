package me.marcangeloh.API.Util.TeleportUtil;

import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class HomeUtil {

    Player player;
    HashMap<String, Location> playerHomesMap = new HashMap<>();
    private File file;
    private FileConfiguration fileConfig;

    public HomeUtil(PointsCore pointsCore, Player player) {
        this.player = player;
        file = new File(pointsCore.getDataFolder(), "homes.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't create homes.yml file!");
            }
        }


        fileConfig = YamlConfiguration.loadConfiguration(file);
        loadPlayerHomes();
    }


    public void addPlayerHome(String homeName, Location homeLocation) {
        if(playerHomesMap.containsKey(homeName)) {
            playerHomesMap.replace(homeName, homeLocation);
        }
        playerHomesMap.putIfAbsent(homeName, homeLocation);
    }

    public int getPlayerHomes() {
        return playerHomesMap.keySet().size();
    }

    public Location getHomeLocation(String homeName) {
        if(!playerHomesMap.containsKey(homeName)) {
            return null;
        }

        return playerHomesMap.get(homeName);
    }

    public void removePlayerHome(String homeName) {
        if(!playerHomesMap.containsKey(homeName)) {
            return;
        }

        playerHomesMap.remove(homeName);
    }

    public void savePlayerHomes() {
        for (String name: playerHomesMap.keySet()
             ) {

            fileConfig.set(player.getUniqueId() + "."+name, playerHomesMap.get(name));
        }

        try {
            fileConfig.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void loadPlayerHomes() {

        if(fileConfig.getConfigurationSection(player.getUniqueId().toString() ) == null)
            return;

        for(String name: fileConfig.getConfigurationSection(player.getUniqueId().toString() ).getKeys(false)) {
            playerHomesMap.putIfAbsent(name, fileConfig.getLocation(player.getUniqueId() + "."+name));
        }

    }

    public boolean containsHome(String homeName) {
        if(playerHomesMap.containsKey(homeName)) return true;
        return false;
    }
}

package me.marcangeloh.Util.ConfigurationUtil;

import me.marcangeloh.API.PointsUtil.PlayerPoints;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private File playerFiles;
    private FileConfiguration fileConfig;

    public void onEnable(Plugin plugin) {
        playerFiles = new File(plugin.getDataFolder(), "data.yml");
        if (!playerFiles.exists()) {
            try {
                playerFiles.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't create data.yml file!");
            }
        }


        fileConfig = YamlConfiguration.loadConfiguration(playerFiles);

    }

    public FileConfiguration getFileConfig() {
        return fileConfig;
    }

    public void addPlayerToSaveFile(PlayerPoints playerPoints, Player player) throws IOException {
        String uuid = player.getUniqueId().toString();
        fileConfig.set(uuid+Paths.pathRangedWeaponPoints,
                playerPoints.rangedWeaponPoints.getPoints(player));
        fileConfig.set(uuid+Paths.pathMeleeWeaponPoints,
                playerPoints.meleeWeaponPoints.getPoints(player));
        fileConfig.set(uuid+Paths.pathArmorPoints,
                playerPoints.armorPoints.getPoints(player));
        fileConfig.set(uuid+Paths.pathAxePoints,
                playerPoints.axePoints.getPoints(player));
        fileConfig.set(uuid+Paths.pathFishingRodPoints,
                playerPoints.fishingPoints.getPoints(player));
        fileConfig.set(uuid+Paths.pathHoePoints,
                playerPoints.hoePoints.getPoints(player));
        fileConfig.set(uuid+Paths.pathPickaxePoints,
                playerPoints.pickaxePoints.getPoints(player));
        fileConfig.set(uuid+Paths.pathShovelPoints,
                playerPoints.shovelPoints.getPoints(player));
        fileConfig.save(playerFiles);
    }

    public void loadPlayerFromSaveFile(PlayerPoints playerPoints, Player player) {
        String uuid = player.getUniqueId().toString();
        if(!playerPoints.shovelPoints.containsPlayer(player)) {
            playerPoints.shovelPoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+Paths.pathShovelPoints));
        }
        if(!playerPoints.rangedWeaponPoints.containsPlayer(player)) {
            playerPoints.rangedWeaponPoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+ Paths.pathRangedWeaponPoints));
        }
        if(!playerPoints.pickaxePoints.containsPlayer(player)) {
            playerPoints.pickaxePoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+ Paths.pathPickaxePoints));
        }
        if(!playerPoints.meleeWeaponPoints.containsPlayer(player)) {
            playerPoints.meleeWeaponPoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+ Paths.pathMeleeWeaponPoints));
        }
        if(!playerPoints.hoePoints.containsPlayer(player)) {
            playerPoints.hoePoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+ Paths.pathHoePoints));
        }
        if(!playerPoints.fishingPoints.containsPlayer(player)) {
            playerPoints.fishingPoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+ Paths.pathFishingRodPoints));
        }
        if(!playerPoints.axePoints.containsPlayer(player)) {
            playerPoints.axePoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+ Paths.pathAxePoints));
        }
        if(!playerPoints.armorPoints.containsPlayer(player)) {
            playerPoints.armorPoints.addPointsToPlayer(player, fileConfig.getDouble(uuid+ Paths.pathArmorPoints));
        }
        PointsCore.updatePlayerPoints(playerPoints);
    }
}

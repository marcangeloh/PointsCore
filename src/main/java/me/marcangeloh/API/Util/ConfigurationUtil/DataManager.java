package me.marcangeloh.API.Util.ConfigurationUtil;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DataManager {

    private File playerFiles;
    private FileConfiguration fileConfig;
    PointsCore pointsCore;

    public void onEnable(PointsCore pointsCore) {
        this.pointsCore = pointsCore;
        playerFiles = new File(pointsCore.getDataFolder(), "data.yml");
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

    public void saveAll() {
        ArrayList<String> uuids = new ArrayList<>();
        if(pointsCore.playerPoints.armorPoints.getArmorPoints() != null) {
            for (String uuid : pointsCore.playerPoints.armorPoints.getArmorPoints().keySet()
            ) {
                uuids.add(uuid);
                addUUIDToSaveFile(uuid);
            }
        }

        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.axePoints.getAxePoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.fishingPoints.getFishingPoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.hoePoints.getHoePoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.meleeWeaponPoints.getMeleeWeaponPoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.pickaxePoints.getPickaxePoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.rangedWeaponPoints.getRangedWeaponPoints()));
        uuids.addAll(nullCheck(uuids, pointsCore.playerPoints.shovelPoints.getShovelPoints()));

    }

    private ArrayList<String> nullCheck(ArrayList<String> uuids, HashMap<String, Double> map) {
        if(map == null) {
            return new ArrayList<String>();
        } else {
            return checkUUID(uuids, map.keySet());
        }
    }

    private ArrayList<String> checkUUID(ArrayList<String> uuids, Set<String> keySet) {
        ArrayList<String> uuidsToAdd = new ArrayList<>();
        for (String uuid: keySet) {
            if(!uuids.contains(uuid)) {
                uuidsToAdd.add(uuid);
                addUUIDToSaveFile(uuid);
            }
        }
        return uuidsToAdd;
    }

    public void addPlayerToSaveFile(Player player) {
        String uuid = player.getUniqueId().toString();
        addUUIDToSaveFile(uuid);
    }

    public void addUUIDToSaveFile(String uuid) {
        fileConfig.set(uuid+Paths.pathRangedWeaponPoints,
                pointsCore.playerPoints.rangedWeaponPoints.getPoints(uuid));
        fileConfig.set(uuid+Paths.pathMeleeWeaponPoints,
                pointsCore.playerPoints.meleeWeaponPoints.getPoints(uuid));
        fileConfig.set(uuid+Paths.pathArmorPoints,
                pointsCore.playerPoints.armorPoints.getPoints(uuid));
        fileConfig.set(uuid+Paths.pathAxePoints,
                pointsCore.playerPoints.axePoints.getPoints(uuid));
        fileConfig.set(uuid+Paths.pathFishingRodPoints,
                pointsCore.playerPoints.fishingPoints.getPoints(uuid));
        fileConfig.set(uuid+Paths.pathHoePoints,
                pointsCore.playerPoints.hoePoints.getPoints(uuid));
        fileConfig.set(uuid+Paths.pathPickaxePoints,
                pointsCore.playerPoints.pickaxePoints.getPoints(uuid));
        fileConfig.set(uuid+Paths.pathShovelPoints,
                pointsCore.playerPoints.shovelPoints.getPoints(uuid));
        try {
            fileConfig.save(playerFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PlayerPoints loadPlayerFromSaveFile(PlayerPoints playerPoints, Player player) {
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
        return playerPoints;
    }
}

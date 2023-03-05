package me.marcangeloh.API.Util.ConfigurationUtil;

import me.marcangeloh.PointsCore;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WarpsUtil {

    private final File warpsFile;
    private final YamlConfiguration warpsConfig;
    private final Map<String, Location> warps;

    public WarpsUtil() {
        this.warpsFile = new File(PointsCore.plugin.getDataFolder(), "warps.yml");
        this.warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);
        this.warps = new HashMap<>();

        loadWarps();
    }

    private void loadWarps() {
        if (!warpsFile.exists()) {
            return;
        }

        ConfigurationSection section = warpsConfig.getConfigurationSection("warps");
        if (section == null) {
            return;
        }

        for (String name : section.getKeys(false)) {
            Location location = section.getLocation(name);
            if (location != null) {
                warps.put(name, location);
            }
        }
    }

    public void saveWarps() {
        ConfigurationSection section = warpsConfig.createSection("warps");
        for (Map.Entry<String, Location> entry : warps.entrySet()) {
            section.set(entry.getKey(), entry.getValue());
        }

        try {
            warpsConfig.save(warpsFile);
        } catch (IOException e) {
            PointsCore.plugin.getLogger().severe("Failed to save warps.yml");
            e.printStackTrace();
        }
    }

    public void setWarp(String name, Location location) {
        warps.put(name, location);
    }

    public Location getWarp(String name) {
        return warps.get(name);
    }

    public void removeWarp(String name) {
        warps.remove(name);
    }

    public Map<String, Location> getWarps() {
        return warps;
    }

}

package me.marcangeloh.API.Util.GeneralUtil;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPILink extends PlaceholderExpansion {
    PointsCore pointsCore;
    private final String pluginVersion;

    public PlaceholderAPILink(PointsCore pointsCore, String pluginVersion) {
        this.pointsCore = pointsCore;
        this.pluginVersion = pluginVersion;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pc";
    }

    @Override
    public String getRequiredPlugin() {
        return "PointsCore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "marcangeloh";
    }

    @Override
    public @NotNull String getVersion() {
        return pluginVersion;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String s) {

        if(player == null){
            return "";
        }
        int decimalPlaces = pointsCore.getConfig().getInt("Points.PointsDecimalPlaces");
        String symbol = " "+pointsCore.getConfig().getString("Points.PointsSymbol");
        // %armor_points%
        switch (s) {
            case "armor_points":
                return roundAvoid(pointsCore.playerPoints.armorPoints.getPoints(player), decimalPlaces) + symbol;


            // %axe_points%
            case "axe_points":
                return roundAvoid(pointsCore.playerPoints.axePoints.getPoints(player), decimalPlaces) + symbol;


            // %fishing_points%
            case "fishing_points":
                return roundAvoid(pointsCore.playerPoints.fishingPoints.getPoints(player), decimalPlaces) + symbol;


            // %hoe_points%
            case "hoe_points":
                return roundAvoid(pointsCore.playerPoints.hoePoints.getPoints(player), decimalPlaces) + symbol;


            // %melee_weapon_points%
            case "melee_weapon_points":
                return roundAvoid(pointsCore.playerPoints.meleeWeaponPoints.getPoints(player), decimalPlaces) + symbol;


            // %pc_pickaxe_points%
            case "pickaxe_points":
                return roundAvoid(pointsCore.playerPoints.pickaxePoints.getPoints(player), decimalPlaces) + symbol;


            // %ranged_weapon_points%
            case "ranged_weapon_points":
                return roundAvoid(pointsCore.playerPoints.rangedWeaponPoints.getPoints(player), decimalPlaces) + symbol;


            // %shovel_points%
            case "shovel_points":
                return roundAvoid(pointsCore.playerPoints.shovelPoints.getPoints(player), decimalPlaces) + symbol;
        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;
    }

    private static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

}

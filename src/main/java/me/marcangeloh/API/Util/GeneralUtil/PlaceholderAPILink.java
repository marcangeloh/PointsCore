package me.marcangeloh.API.Util.GeneralUtil;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;

public class PlaceholderAPILink extends PlaceholderExpansion {
    PointsCore pointsCore;
    private final String pluginVersion;

    public PlaceholderAPILink(PointsCore pointsCore, String pluginVersion) {
        this.pointsCore = pointsCore;
        this.pluginVersion = pluginVersion;
    }

    @Override
    public String getIdentifier() {
        return "pc";
    }

    @Override
    public String getPlugin() {
        return "PointsCore";
    }

    @Override
    public String getAuthor() {
        return "marcangeloh";
    }

    @Override
    public String getVersion() {
        return pluginVersion;
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {

        if(player == null){
            return "";
        }
        int decimalPlaces = pointsCore.getConfig().getInt("Points.PointsDecimalPlaces");
        String symbol = " "+pointsCore.getConfig().getString("Points.PointsSymbol");
        // %armor_points%
        if(s.equals("armor_points")){
            return roundAvoid(pointsCore.playerPoints.armorPoints.getPoints(player),decimalPlaces) +symbol;
        }

        // %axe_points%
        if(s.equals("axe_points")){
            return  roundAvoid(pointsCore.playerPoints.axePoints.getPoints(player),decimalPlaces) +symbol;
        }

        // %fishing_points%
        if(s.equals("fishing_points")){
            return  roundAvoid(pointsCore.playerPoints.fishingPoints.getPoints(player),decimalPlaces) +symbol;
        }

        // %hoe_points%
        if(s.equals("hoe_points")){
            return  roundAvoid(pointsCore.playerPoints.hoePoints.getPoints(player),decimalPlaces) +symbol;
        }

        // %melee_weapon_points%
        if(s.equals("melee_weapon_points")){
            return  roundAvoid(pointsCore.playerPoints.meleeWeaponPoints.getPoints(player),decimalPlaces) +symbol;
        }

        // %pc_pickaxe_points%
        if(s.equals("pickaxe_points")){
            return  roundAvoid(pointsCore.playerPoints.pickaxePoints.getPoints(player),decimalPlaces) +symbol;
        }

        // %ranged_weapon_points%
        if(s.equals("ranged_weapon_points")){
            return  roundAvoid(pointsCore.playerPoints.rangedWeaponPoints.getPoints(player),decimalPlaces) +symbol;
        }

        // %shovel_points%
        if(s.equals("shovel_points")){
            return  roundAvoid(pointsCore.playerPoints.shovelPoints.getPoints(player),decimalPlaces) +symbol;
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

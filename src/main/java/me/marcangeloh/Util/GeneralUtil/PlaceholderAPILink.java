package me.marcangeloh.Util.GeneralUtil;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;

public class PlaceholderAPILink extends PlaceholderExpansion {

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
        return PointsCore.pluginVersion;
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {

        // %armor_points%
        if(s.equals("armor_points")){
            return PointsCore.playerPoints.armorPoints.getPoints(player) +"";
        }

        // %axe_points%
        if(s.equals("axe_points")){
            return  PointsCore.playerPoints.axePoints.getPoints(player) +"";
        }

        // %fishing_points%
        if(s.equals("fishing_points")){
            return  PointsCore.playerPoints.fishingPoints.getPoints(player) +"";
        }

        // %hoe_points%
        if(s.equals("hoe_points")){
            return  PointsCore.playerPoints.hoePoints.getPoints(player) +"";
        }

        // %melee_weapon_points%
        if(s.equals("melee_weapon_points")){
            return  PointsCore.playerPoints.meleeWeaponPoints.getPoints(player) +"";
        }

        // %pickaxe_points%
        if(s.equals("pickaxe_points")){
            return  PointsCore.playerPoints.pickaxePoints.getPoints(player) +"";
        }

        // %ranged_weapon_points%
        if(s.equals("ranged_weapon_points")){
            return  PointsCore.playerPoints.rangedWeaponPoints.getPoints(player) +"";
        }

        // %shovel_points%
        if(s.equals("shovel_points")){
            return  PointsCore.playerPoints.shovelPoints.getPoints(player) +"";
        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;
    }
}

package me.marcangeloh.API.PointsUtil;

import me.marcangeloh.API.PointsUtil.DetailedPoints.*;
import me.marcangeloh.Util.GeneralUtil.Tools;
import org.bukkit.entity.Player;

import java.io.*;

public final class PlayerPoints implements Serializable {
    public ArmorPoints armorPoints = new ArmorPoints();
    public AxePoints axePoints = new AxePoints();
    public FishingPoints fishingPoints = new FishingPoints();
    public HoePoints hoePoints = new HoePoints();
    public MeleeWeaponPoints meleeWeaponPoints = new MeleeWeaponPoints();
    public PickaxePoints pickaxePoints = new PickaxePoints();
    public RangedWeaponPoints rangedWeaponPoints = new RangedWeaponPoints();
    public ShovelPoints shovelPoints = new ShovelPoints();

    public boolean addPointsToToolType(Tools tool, Player player, double amount) {
        switch (tool) {
            case PICKAXE:
                pickaxePoints.addPointsToPlayer(player,amount);
                return true;
            case SHOVEL:
                shovelPoints.addPointsToPlayer(player,amount);
                return true;
            case AXE:
                axePoints.addPointsToPlayer(player,amount);
                return true;
            case HOE:
                hoePoints.addPointsToPlayer(player,amount);
                return true;
            case ARMOR:
                armorPoints.addPointsToPlayer(player,amount);
                return true;
            case RANGED_WEAPON:
                rangedWeaponPoints.addPointsToPlayer(player,amount);
                return true;
            case FISH_ROD:
                fishingPoints.addPointsToPlayer(player,amount);
                return true;
            case MELEE_WEAPON:
                meleeWeaponPoints.addPointsToPlayer(player,amount);
                return true;
            default:
                return false;
        }
    }

    public boolean removePointsToToolType(Tools tool, Player player, double amount) {
        switch (tool) {
            case PICKAXE:
                pickaxePoints.removePointsFromPlayer(player,amount);
                return true;
            case SHOVEL:
                shovelPoints.removePointsFromPlayer(player,amount);
                return true;
            case AXE:
                axePoints.removePointsFromPlayer(player,amount);
                return true;
            case HOE:
                hoePoints.removePointsFromPlayer(player,amount);
                return true;
            case ARMOR:
                armorPoints.removePointsFromPlayer(player,amount);
                return true;
            case RANGED_WEAPON:
                rangedWeaponPoints.removePointsFromPlayer(player,amount);
                return true;
            case FISH_ROD:
                fishingPoints.removePointsFromPlayer(player,amount);
                return true;
            case MELEE_WEAPON:
                meleeWeaponPoints.removePointsFromPlayer(player,amount);
                return true;
            default:
                return false;
        }
    }
}

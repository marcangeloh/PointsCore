package me.marcangeloh.API.PointsUtil;

import me.marcangeloh.API.PointsUtil.DetailedPoints.*;
import me.marcangeloh.API.Util.GeneralUtil.CooldownUtil;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public final class PlayerPoints implements Serializable {
    public ArmorPoints armorPoints = new ArmorPoints();
    public AxePoints axePoints = new AxePoints();
    public FishingPoints fishingPoints = new FishingPoints();
    public HoePoints hoePoints = new HoePoints();
    public MeleeWeaponPoints meleeWeaponPoints = new MeleeWeaponPoints();
    public PickaxePoints pickaxePoints = new PickaxePoints();
    public RangedWeaponPoints rangedWeaponPoints = new RangedWeaponPoints();
    public ShovelPoints shovelPoints = new ShovelPoints();
    public HashMap<UUID, CooldownUtil> multiplierMap = new HashMap<>();

    public boolean addPointsToToolType(Tools tool, Player player, double amountNoMultiplier) {
        double amount = amountNoMultiplier;
        UUID uuid = player.getUniqueId();
        if(multiplierMap.containsKey(uuid)) {
            amount = amount * multiplierMap.get(uuid).getMultiplierAmount();
        }

        switch (tool) {
            case PICKAXE:
                return pickaxePoints.addPointsToPlayer(player, amount);
            case SHOVEL:
                return shovelPoints.addPointsToPlayer(player,amount);
            case AXE:
                return axePoints.addPointsToPlayer(player,amount);
            case HOE:
                return hoePoints.addPointsToPlayer(player,amount);
            case ARMOR:
                return armorPoints.addPointsToPlayer(player,amount);
            case RANGED_WEAPON:
                return rangedWeaponPoints.addPointsToPlayer(player,amount);
            case FISH_ROD:
                return fishingPoints.addPointsToPlayer(player,amount);
            case MELEE_WEAPON:
                return meleeWeaponPoints.addPointsToPlayer(player,amount);
            default:
                return false;
        }
    }

    public boolean removePointsToToolType(Tools tool, Player player, double amount) {
        switch (tool) {
            case PICKAXE:
                return pickaxePoints.removePointsFromPlayer(player,amount);
            case SHOVEL:
                return shovelPoints.removePointsFromPlayer(player,amount);
            case AXE:
                return axePoints.removePointsFromPlayer(player,amount);
            case HOE:
                return hoePoints.removePointsFromPlayer(player,amount);
            case ARMOR:
                return armorPoints.removePointsFromPlayer(player,amount);
            case RANGED_WEAPON:
                return rangedWeaponPoints.removePointsFromPlayer(player,amount);
            case FISH_ROD:
                return fishingPoints.removePointsFromPlayer(player,amount);
            case MELEE_WEAPON:
                return meleeWeaponPoints.removePointsFromPlayer(player,amount);
            default:
                return false;
        }
    }

    public double getPointsFromToolType(Tools tool, Player player) {
        switch (tool) {
            case PICKAXE:
                return pickaxePoints.getPoints(player);
            case SHOVEL:
                return shovelPoints.getPoints(player);
            case AXE:
                return axePoints.getPoints(player);
            case HOE:
                return hoePoints.getPoints(player);
            case ARMOR:
                return armorPoints.getPoints(player);
            case RANGED_WEAPON:
                return rangedWeaponPoints.getPoints(player);
            case FISH_ROD:
                return fishingPoints.getPoints(player);
            case MELEE_WEAPON:
                return meleeWeaponPoints.getPoints(player);
            default:
                return 0.0;
        }
    }

    public String getPointNameFromToolType(Tools tool) {
        switch (tool) {
            case PICKAXE:
                return pickaxePoints.getPointName();
            case SHOVEL:
                return shovelPoints.getPointName();
            case AXE:
                return axePoints.getPointName();
            case HOE:
                return hoePoints.getPointName();
            case ARMOR:
                return armorPoints.getPointName();
            case RANGED_WEAPON:
                return rangedWeaponPoints.getPointName();
            case FISH_ROD:
                return fishingPoints.getPointName();
            case MELEE_WEAPON:
                return meleeWeaponPoints.getPointName();
            default:
                return "N/A";
        }
    }
}

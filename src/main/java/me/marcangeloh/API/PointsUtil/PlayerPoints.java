package me.marcangeloh.API.PointsUtil;

import me.marcangeloh.API.PointsUtil.DetailedPoints.*;
import me.marcangeloh.API.Util.GeneralUtil.CooldownUtil;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public final class PlayerPoints implements Serializable {
    public PlayerPoints(PointsCore pointsCore) {
        this.pointsCore = pointsCore;
        armorPoints = new ArmorPoints(pointsCore);
        axePoints = new AxePoints(pointsCore);
        fishingPoints = new FishingPoints(pointsCore);
        hoePoints = new HoePoints(pointsCore);
        meleeWeaponPoints = new MeleeWeaponPoints(pointsCore);
        pickaxePoints = new PickaxePoints(pointsCore);
        rangedWeaponPoints = new RangedWeaponPoints(pointsCore);
        shovelPoints = new ShovelPoints(pointsCore);
    }

    private PointsCore pointsCore;
    public ArmorPoints armorPoints;
    public AxePoints axePoints;
    public FishingPoints fishingPoints;
    public HoePoints hoePoints;
    public MeleeWeaponPoints meleeWeaponPoints;
    public PickaxePoints pickaxePoints;
    public RangedWeaponPoints rangedWeaponPoints;
    public ShovelPoints shovelPoints;
    public HashMap<UUID, CooldownUtil> multiplierMap = new HashMap<>();


    public Double getMultiplierForTool(Player player, Tools tool) {
        switch (tool) {
            case PICKAXE:
                return pickaxePoints.getMultiplier(player);
            case HOE:
                return hoePoints.getMultiplier(player);
            case RANGED_WEAPON:
                return rangedWeaponPoints.getMultiplier(player);
            case MELEE_WEAPON:
                return meleeWeaponPoints.getMultiplier(player);
            case FISH_ROD:
                return fishingPoints.getMultiplier(player);
            case ARMOR:
                return armorPoints.getMultiplier(player);
            case AXE:
                return axePoints.getMultiplier(player);
            case SHOVEL:
                return shovelPoints.getMultiplier(player);
        }
        return 0.0;
    }

    public boolean setPointsForToolType(Tools tool, Player player, Double amount) {
        switch (tool) {
            case AXE:
                return axePoints.setPointsForPlayer(player, amount);
            case HOE:
                return hoePoints.setPointsForPlayer(player, amount);
            case ARMOR:
                return armorPoints.setPointsForPlayer(player,amount);
            case SHOVEL:
                return shovelPoints.setPointsForPlayer(player,amount);
            case PICKAXE:
                return pickaxePoints.setPointsForPlayer(player,amount);
            case FISH_ROD:
                return fishingPoints.setPointsForPlayer(player,amount);
            case MELEE_WEAPON:
                return meleeWeaponPoints.setPointsForPlayer(player,amount);
            case RANGED_WEAPON:
                return rangedWeaponPoints.setPointsForPlayer(player,amount);
            default:
                return false;
        }
    }
    public void setMultiplierForTool(Player player, Double amount, Tools tool) {
        switch (tool) {
            case PICKAXE:
                pickaxePoints.setMultiplier(player, amount);
                return;
            case HOE:
                hoePoints.setMultiplier(player, amount);
                return;
            case RANGED_WEAPON:
                rangedWeaponPoints.setMultiplier(player,amount);
                return;
            case MELEE_WEAPON:
                meleeWeaponPoints.setMultiplier(player, amount);
                return;
            case FISH_ROD:
                fishingPoints.setMultiplier(player, amount);
                return;
            case ARMOR:
                armorPoints.setMultiplier(player, amount);
                return;
            case AXE:
                axePoints.setMultiplier(player, amount);
                return;
            case SHOVEL:
                shovelPoints.setMultiplier(player, amount);
                return;
        }
    }

    public String getGeneralPointsSymbol() {
        return pointsCore.getConfig().getString("Points.PointsSymbol");
    }
    public String getGeneralPointsName() {
        return pointsCore.getConfig().getString("Points.PointsName");
    }

    public String getPointNameFromTool(Tools tool) {
        switch(tool) {
            case SHOVEL:
                return shovelPoints.getPointName();
            case AXE:
                return axePoints.getPointName();
            case ARMOR:
                return armorPoints.getPointName();
            case FISH_ROD:
                return fishingPoints.getPointName();
            case MELEE_WEAPON:
                return meleeWeaponPoints.getPointName();
            case RANGED_WEAPON:
                return rangedWeaponPoints.getPointName();
            case HOE:
                return hoePoints.getPointName();
            case PICKAXE:
                return pickaxePoints.getPointName();
        }
        return "";
    }

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

    public double getGeneralPoints(Player player) {
        return pickaxePoints.getPoints(player)+shovelPoints.getPoints(player)+axePoints.getPoints(player)
                +hoePoints.getPoints(player)+armorPoints.getPoints(player)+fishingPoints.getPoints(player)
                +meleeWeaponPoints.getPoints(player);
    }

    public boolean removeFromGeneralPoints(Player player, double oldAmount) {
        double amount = oldAmount/8;
        if(amount > getPointsFromToolType(Tools.SHOVEL,player) ||
                amount > getPointsFromToolType(Tools.RANGED_WEAPON,player) ||
                amount > getPointsFromToolType(Tools.PICKAXE,player) ||
                amount > getPointsFromToolType(Tools.MELEE_WEAPON,player) ||
                amount > getPointsFromToolType(Tools.HOE,player) ||
                amount > getPointsFromToolType(Tools.FISH_ROD,player) ||
                amount > getPointsFromToolType(Tools.AXE,player) ||
                amount > getPointsFromToolType(Tools.ARMOR,player)) {
            return false;
        }
        removePointsToToolType(Tools.SHOVEL,player,amount);
        removePointsToToolType(Tools.RANGED_WEAPON,player,amount);
        removePointsToToolType(Tools.PICKAXE,player,amount);
        removePointsToToolType(Tools.MELEE_WEAPON,player,amount);
        removePointsToToolType(Tools.HOE,player,amount);
        removePointsToToolType(Tools.FISH_ROD,player,amount);
        removePointsToToolType(Tools.AXE,player,amount);
        removePointsToToolType(Tools.ARMOR,player,amount);
        return true;
    }

    public void addToGeneralPoints(Player player, double oldAmount) {
        double amount = oldAmount/8;
        addPointsToToolType(Tools.SHOVEL,player,amount);
        addPointsToToolType(Tools.RANGED_WEAPON,player,amount);
        addPointsToToolType(Tools.PICKAXE,player,amount);
        addPointsToToolType(Tools.MELEE_WEAPON,player,amount);
        addPointsToToolType(Tools.HOE,player,amount);
        addPointsToToolType(Tools.FISH_ROD,player,amount);
        addPointsToToolType(Tools.AXE,player,amount);
        addPointsToToolType(Tools.ARMOR,player,amount);
    }
}

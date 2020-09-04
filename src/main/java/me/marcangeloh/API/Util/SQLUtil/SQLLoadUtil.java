package me.marcangeloh.API.Util.SQLUtil;


public class SQLLoadUtil {
    private final String playerName, uuid;
    private final Double armorPoints, meleeWeaponPoints, rangedWeaponPoints, toolPoints, hoePoints, pickaxePoints, axePoints, fishingPoints, shovelPoints;

    public SQLLoadUtil(String uuid, String playerName, Double armorPoints, Double meleeWeaponPoints,  Double rangedWeaponPoints, Double toolPoints, Double hoePoints, Double pickaxePoints, Double axePoints, Double fishingPoints, Double shovelPoints) {
        this.playerName=playerName;
        this.uuid = uuid;
        this.armorPoints = armorPoints;
        this.meleeWeaponPoints = meleeWeaponPoints;
        this.rangedWeaponPoints = rangedWeaponPoints;
        this.toolPoints = toolPoints;
        this.hoePoints = hoePoints;
        this.pickaxePoints = pickaxePoints;
        this.axePoints = axePoints;
        this.fishingPoints = fishingPoints;
        this.shovelPoints = shovelPoints;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getUuid() {
        return uuid;
    }

    public Double getShovelPoints() {
        return shovelPoints;
    }

    public Double getArmorPoints() {
        return armorPoints;
    }

    public Double getMeleeWeaponPoints() {
        return meleeWeaponPoints;
    }

    public Double getRangedWeaponPoints() {
        return rangedWeaponPoints;
    }

    public Double getToolPoints() {
        return toolPoints;
    }

    public Double getHoePoints() {
        return hoePoints;
    }

    public Double getPickaxePoints() {
        return pickaxePoints;
    }

    public Double getAxePoints() {
        return axePoints;
    }

    public Double getFishingPoints() {
        return fishingPoints;
    }
}

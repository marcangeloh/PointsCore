package me.marcangeloh.Util.ConfigurationUtil;

public interface Paths {

    String mainPath = "Points.";
    /*
     * Points Section
     */
    String enabledFor = mainPath+"EnabledFor.";
    String pathIsRangedWeaponEnabled = enabledFor+"RangedWeapons";
    String pathIsMeleeWeaponEnabled = enabledFor+"MeleeWeapons";
    String pathIsArmorEnabled = enabledFor+"Armor";
    String pathIsPickaxeEnabled = enabledFor+"Pickaxe";
    String pathAreToolsEnabled = enabledFor+"Tools"; //Should either be tools or Individual points for tools
    String pathIsShovelEnabled = enabledFor+"Shovel";
    String pathIsAxeEnabled = enabledFor+"Axe";
    String pathIsFishingRodEnabled = enabledFor+"FishingRod";
    String pathIsHoeEnabled = enabledFor+"Hoe";
    String pathCurrencySymbol = enabledFor+"CurrencySymbol";

    /*
     * Player Save File Config Format
     */
    String pathToSaveFile = "";
    String pathRangedWeaponPoints = ".RangedWeaponPoints";
    String pathMeleeWeaponPoints = ".MeleeWeaponPoints";
    String pathArmorPoints = ".ArmorPoints";
    String pathShovelPoints = ".ToolPoints.Shovel";
    String pathPickaxePoints = ".ToolPoints.Pickaxe";
    String pathAxePoints = ".ToolPoints.Axe";
    String pathFishingRodPoints = ".ToolPoints.FishingRod";
    String pathHoePoints = ".ToolPoints.HoePoints";

    /**
     * Path to Point amounts
     */
    String pathIncrement = "Points.Increment.";
    String pathArmorPointsIncrement = pathIncrement +"ArmorPoints";
    String pathToolsIncrement = pathIncrement + "ToolPoints.";
    String pathPickaxeValues = pathToolsIncrement + "PickaxeValues.";
    String pathStoneValue = pathPickaxeValues + "Stone";
    String pathDioriteValue = pathPickaxeValues + "Diorite";
    String pathGraniteValue = pathPickaxeValues + "Granite";
    String pathAndesiteValue = pathPickaxeValues + "Andesite";
    String pathSandstoneValue = pathPickaxeValues + "Sandstone";
    String pathChiseledSandstoneValue = pathPickaxeValues + "ChiseledSandstone";
    String pathChiseledRedSandstoneValue = pathPickaxeValues + "ChiseledRedSandstone";
    String pathCutSandstoneValue = pathPickaxeValues + "CutSandstone";
    String pathCutRedSandstoneValue = pathPickaxeValues + "CutRedSandstone";
    String pathSmoothSandstoneValue = pathPickaxeValues + "SmoothSandstone";
    String pathSmoothRedSandstoneValue = pathPickaxeValues + "SmoothRedSandstone";
    String pathNetherrackValue = pathPickaxeValues + "Netherrack";
    String pathNetherBrickValue = pathPickaxeValues + "NetherBrick";
    String pathLapisValue = pathPickaxeValues + "Lapis";
    String pathCoalValue = pathPickaxeValues + "Coal";
    String pathEndStoneValue = pathPickaxeValues + "EndStone";
    String pathObsidianValue = pathPickaxeValues + "Obsidian";
    String pathTerracottaValue = pathPickaxeValues + "Terracotta";
    String pathDiamondValue = pathPickaxeValues + "Diamond";
    String pathEmeraldValue = pathPickaxeValues + "Emerald";
    String pathIronValue = pathPickaxeValues + "Iron";
    String pathGoldValue = pathPickaxeValues + "Gold";
    String pathPrismarineValue = pathPickaxeValues + "Prismarine";
    String pathPrismarineBrickValue = pathPickaxeValues + "PrismarineBricks";
    String pathIceValue = pathPickaxeValues + "Ice";
    String pathBrickValue = pathPickaxeValues + "Brick";
    String pathQuartzValue = pathPickaxeValues + "NetherQuartz";
    String pathPOtherValue = pathPickaxeValues + "Other";

    //Shovel Paths
    String pathShovelValues = pathToolsIncrement + "ShovelValues.";
    String pathDirt =  pathShovelValues + "Dirt";
    String pathSand = pathShovelValues + "Sand";
    String pathSoulSand = pathShovelValues + "SoulSand";
    String pathMycelium = pathShovelValues + "Mycelium";
    String pathGravel = pathShovelValues + "Gravel";
    String pathSOtherValue = pathShovelValues + "Other";

    //Axe Paths
    String pathAxeValues = pathToolsIncrement + "AxeValues.";
    String pathAcaciaValues = pathAxeValues + "Acacia";
    String pathOakValues = pathAxeValues + "Oak";
    String pathDarkOakValues = pathAxeValues + "DarkOak";
    String pathJungleValues =pathAxeValues + "Jungle";
    String pathSpruceValues = pathAxeValues + "Spruce";
    String pathBirchValues = pathAxeValues + "Birch";
    String pathAOtherValue = pathAxeValues + "Other";
    //Hoe Paths
    String pathHoeValues = pathToolsIncrement + "HoeValues.";
    String pathCoarse = pathHoeValues + "ConvertCoarse", pathHoe = "Hoe";
    //Fishing Paths
    String pathFishingValues = pathToolsIncrement + "FishingValues.";
    String pathCodValues = pathFishingValues + "Cod";
    String pathPufferfishValues = pathFishingValues + "Pufferfish";
    String pathTropicalFishValues = pathFishingValues +"TropicalFish";
    String pathSalmonValues = pathFishingValues + "Salmon";
    String pathFOtherValues = pathFishingValues +"Other";

    /**
     * SQL Section
     */

    /*
     SQL Init from config
     */
    String pathIsSQLEnabled = "Points.SQL.Enabled";
    String pathSQLHostName = "Points.SQL.ServerAndPort";
    String pathSQLDatabase = "Points.SQL.Database";
    String pathSQLUsername = "Points.SQL.Username";
    String pathSQLPassword = "Points.SQL.Password";

    /*
     SQL Table and Column Names
     */




}

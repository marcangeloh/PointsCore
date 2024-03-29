package me.marcangeloh.API.Util.ConfigurationUtil;

import me.marcangeloh.PointsCore;
import me.marcangeloh.API.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.Tools;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class ValueUtil implements Paths {
    private PointsCore pointsCore;

    public ValueUtil(PointsCore pointsCore) {
        this.pointsCore = pointsCore;
    }

    public Tools getToolType(Material tool) {
        return getToolTypeByVersion(tool);
    }

    private Tools getToolTypeByVersion(Material tool) {
        if(tool.equals(Material.DIAMOND_AXE) ||
                tool.equals(Material.GOLDEN_AXE) ||
                tool.equals(Material.IRON_AXE) ||
                tool.equals(Material.STONE_AXE) ||
                tool.equals(Material.NETHERITE_AXE) ||
                tool.equals(Material.WOODEN_AXE)) {
            return Tools.AXE;
        } else if(tool.equals(Material.NETHERITE_PICKAXE) ||
                tool.equals(Material.DIAMOND_PICKAXE) ||
                tool.equals(Material.GOLDEN_PICKAXE) ||
                tool.equals(Material.IRON_PICKAXE) ||
                tool.equals(Material.STONE_PICKAXE) ||
                tool.equals(Material.WOODEN_PICKAXE)) {
            return Tools.PICKAXE;
        } else if(tool.equals(Material.DIAMOND_SHOVEL) ||
                tool.equals(Material.GOLDEN_SHOVEL) ||
                tool.equals(Material.IRON_SHOVEL) ||
                tool.equals(Material.STONE_SHOVEL) ||
                tool.equals(Material.NETHERITE_SHOVEL) ||
                tool.equals(Material.WOODEN_SHOVEL)) {
            return Tools.SHOVEL;
        }else if(tool.equals(Material.DIAMOND_HOE) ||
                tool.equals(Material.GOLDEN_HOE) ||
                tool.equals(Material.IRON_HOE) ||
                tool.equals(Material.STONE_HOE) ||
                tool.equals(Material.NETHERITE_HOE) ||
                tool.equals(Material.WOODEN_HOE)) {
            return Tools.HOE;
        } else if(tool.equals(Material.FISHING_ROD)) {
            return Tools.FISH_ROD;
        } else if(tool.equals(Material.DIAMOND_SWORD) ||
                tool.equals(Material.GOLDEN_SWORD) ||
                tool.equals(Material.IRON_SWORD) ||
                tool.equals(Material.STONE_SWORD) ||
                tool.equals(Material.NETHERITE_SWORD) ||
                tool.equals(Material.WOODEN_SWORD)) {
            return Tools.MELEE_WEAPON;
        } else if(tool.equals(Material.BOW)
                || tool.equals(Material.TRIDENT)
                || tool.equals(Material.CROSSBOW)){
            return Tools.RANGED_WEAPON;
        } else if(tool.equals(Material.CHAINMAIL_BOOTS) ||
                tool.equals(Material.IRON_BOOTS)||
                tool.equals(Material.LEATHER_BOOTS)||
                tool.equals(Material.NETHERITE_BOOTS)||
                tool.equals(Material.DIAMOND_BOOTS)
                || tool.equals(Material.GOLDEN_BOOTS)||
                tool.equals(Material.CHAINMAIL_LEGGINGS) ||
                tool.equals(Material.IRON_LEGGINGS)||
                tool.equals(Material.NETHERITE_LEGGINGS)||
                tool.equals(Material.LEATHER_LEGGINGS)||
                tool.equals(Material.DIAMOND_LEGGINGS)
                || tool.equals(Material.GOLDEN_LEGGINGS)||
                tool.equals(Material.CHAINMAIL_CHESTPLATE) ||
                tool.equals(Material.IRON_CHESTPLATE)||
                tool.equals(Material.NETHERITE_CHESTPLATE)||
                tool.equals(Material.LEATHER_CHESTPLATE)||
                tool.equals(Material.DIAMOND_CHESTPLATE)
                || tool.equals(Material.GOLDEN_CHESTPLATE)||
                tool.equals(Material.CHAINMAIL_HELMET) ||
                tool.equals(Material.IRON_HELMET)||
                tool.equals(Material.LEATHER_HELMET)||
                tool.equals(Material.NETHERITE_HELMET)||
                tool.equals(Material.DIAMOND_HELMET)
                || tool.equals(Material.GOLDEN_HELMET)
                || tool.equals(Material.TURTLE_HELMET)){
            return Tools.ARMOR;
        } else {
            return Tools.NONE;
        }

    }

    public double getMaterialValue(Tools tool, Material material) {
        if(tool.equals(Tools.PICKAXE)) {
            switch (material) {
                case ICE:
                case PACKED_ICE:
                case BLUE_ICE:
                case FROSTED_ICE:
                    return pointsCore.getConfig().getDouble(pathIceValue);
                case BRICK:
                case BRICKS:
                case BRICK_SLAB:
                case BRICK_STAIRS:
                    return pointsCore.getConfig().getDouble(pathBrickValue);
                case QUARTZ:
                case QUARTZ_BLOCK:
                case QUARTZ_BRICKS:
                case QUARTZ_PILLAR:
                case QUARTZ_SLAB:
                case QUARTZ_STAIRS:
                case CHISELED_QUARTZ_BLOCK:
                case NETHER_QUARTZ_ORE:
                case SMOOTH_QUARTZ:
                case SMOOTH_QUARTZ_SLAB:
                case SMOOTH_QUARTZ_STAIRS:
                    return pointsCore.getConfig().getDouble(pathQuartzValue);
                case STONE:
                case STONE_BRICK_SLAB:
                case STONE_BRICK_STAIRS:
                case STONE_BRICK_WALL:
                case STONE_BRICKS:
                case STONE_SLAB:
                case STONE_STAIRS:
                case STONE_BUTTON:
                    return pointsCore.getConfig().getDouble(pathStoneValue);
                case DIORITE:
                case DIORITE_SLAB:
                case DIORITE_STAIRS:
                case DIORITE_WALL:
                case POLISHED_DIORITE:
                case POLISHED_DIORITE_SLAB:
                case POLISHED_DIORITE_STAIRS:
                    return pointsCore.getConfig().getDouble(pathDioriteValue);
                case GRANITE:
                case GRANITE_SLAB:
                case GRANITE_STAIRS:
                case GRANITE_WALL:
                case POLISHED_GRANITE:
                case POLISHED_GRANITE_SLAB:
                case POLISHED_GRANITE_STAIRS:
                    return pointsCore.getConfig().getDouble(pathGraniteValue);
                case ANDESITE:
                case ANDESITE_SLAB:
                case ANDESITE_STAIRS:
                case ANDESITE_WALL:
                case POLISHED_ANDESITE:
                case POLISHED_ANDESITE_SLAB:
                case POLISHED_ANDESITE_STAIRS:
                    return pointsCore.getConfig().getDouble(pathAndesiteValue);
                case SANDSTONE:
                case SANDSTONE_SLAB:
                case SANDSTONE_STAIRS:
                case SANDSTONE_WALL:
                    return pointsCore.getConfig().getDouble(pathSandstoneValue);
                case CHISELED_SANDSTONE:
                    return pointsCore.getConfig().getDouble(pathChiseledSandstoneValue);
                case CHISELED_RED_SANDSTONE:
                    return pointsCore.getConfig().getDouble(pathChiseledRedSandstoneValue);
                case CUT_SANDSTONE:
                case CUT_SANDSTONE_SLAB:
                    return pointsCore.getConfig().getDouble(pathCutSandstoneValue);
                case CUT_RED_SANDSTONE:
                case CUT_RED_SANDSTONE_SLAB:
                    return pointsCore.getConfig().getDouble(pathCutRedSandstoneValue);
                case SMOOTH_SANDSTONE:
                case SMOOTH_SANDSTONE_SLAB:
                case SMOOTH_SANDSTONE_STAIRS:
                    return pointsCore.getConfig().getDouble(pathSmoothSandstoneValue);
                case RED_SANDSTONE:
                case RED_SANDSTONE_SLAB:
                case RED_SANDSTONE_STAIRS:
                case RED_SANDSTONE_WALL:
                    return pointsCore.getConfig().getDouble(pathRedSandstone);
                case SMOOTH_RED_SANDSTONE:
                case SMOOTH_RED_SANDSTONE_SLAB:
                case SMOOTH_RED_SANDSTONE_STAIRS:
                    return pointsCore.getConfig().getDouble(pathSmoothRedSandstoneValue);
                case NETHERRACK:
                    return pointsCore.getConfig().getDouble(pathNetherrackValue);
                case NETHER_BRICK:
                case NETHER_BRICK_FENCE:
                case NETHER_BRICK_SLAB:
                case NETHER_BRICK_STAIRS:
                case NETHER_BRICKS:
                case CHISELED_NETHER_BRICKS:
                case CRACKED_NETHER_BRICKS:
                case NETHER_BRICK_WALL:
                    return pointsCore.getConfig().getDouble(pathNetherBrickValue);
                case LAPIS_BLOCK:
                case LAPIS_LAZULI:
                case LAPIS_ORE:
                case DEEPSLATE_LAPIS_ORE:
                    return pointsCore.getConfig().getDouble(pathLapisValue);
                case COAL:
                case COAL_BLOCK:
                case COAL_ORE:
                case DEEPSLATE_COAL_ORE:
                    return pointsCore.getConfig().getDouble(pathCoalValue);
                case COPPER_ORE:
                case COPPER_BLOCK:
                case RAW_COPPER:
                case RAW_COPPER_BLOCK:
                case DEEPSLATE_COPPER_ORE:
                case EXPOSED_COPPER:
                case WEATHERED_COPPER:
                    return pointsCore.getConfig().getDouble(pathCopperValue);
                case CUT_COPPER:
                case CUT_COPPER_SLAB:
                case CUT_COPPER_STAIRS:
                case EXPOSED_CUT_COPPER:
                case EXPOSED_CUT_COPPER_SLAB:
                case OXIDIZED_CUT_COPPER:
                case WEATHERED_CUT_COPPER_STAIRS:
                case WEATHERED_CUT_COPPER:
                case OXIDIZED_CUT_COPPER_SLAB:
                case EXPOSED_CUT_COPPER_STAIRS:
                case WEATHERED_CUT_COPPER_SLAB:
                case OXIDIZED_CUT_COPPER_STAIRS:
                    return pointsCore.getConfig().getDouble(pathCutCopper);
                case CRYING_OBSIDIAN:
                    return pointsCore.getConfig().getDouble(pathCryingObsidian);
                case END_STONE:
                case END_STONE_BRICK_SLAB:
                case END_STONE_BRICK_STAIRS:
                case END_STONE_BRICK_WALL:
                case END_STONE_BRICKS:
                    return pointsCore.getConfig().getDouble(pathEndStoneValue);
                case OBSIDIAN:
                    return pointsCore.getConfig().getDouble(pathObsidianValue);
                case BLACKSTONE:
                case BLACKSTONE_SLAB:
                case BLACKSTONE_STAIRS:
                case BLACKSTONE_WALL:
                case GILDED_BLACKSTONE:
                case POLISHED_BLACKSTONE:
                case CHISELED_POLISHED_BLACKSTONE:
                case CRACKED_POLISHED_BLACKSTONE_BRICKS:
                case POLISHED_BLACKSTONE_BRICK_SLAB:
                    return pointsCore.getConfig().getDouble(pathBlackstone);
                case TERRACOTTA:
                case BLACK_GLAZED_TERRACOTTA:
                case BLACK_TERRACOTTA:
                case BLUE_GLAZED_TERRACOTTA:
                case BLUE_TERRACOTTA:
                case BROWN_GLAZED_TERRACOTTA:
                case BROWN_TERRACOTTA:
                case CYAN_GLAZED_TERRACOTTA:
                case CYAN_TERRACOTTA:
                case GRAY_GLAZED_TERRACOTTA:
                case GRAY_TERRACOTTA:
                case GREEN_GLAZED_TERRACOTTA:
                case GREEN_TERRACOTTA:
                case LIGHT_BLUE_GLAZED_TERRACOTTA:
                case LIGHT_BLUE_TERRACOTTA:
                case LIGHT_GRAY_GLAZED_TERRACOTTA:
                case LIGHT_GRAY_TERRACOTTA:
                case LIME_GLAZED_TERRACOTTA:
                case LIME_TERRACOTTA:
                case MAGENTA_GLAZED_TERRACOTTA:
                case MAGENTA_TERRACOTTA:
                case ORANGE_GLAZED_TERRACOTTA:
                case ORANGE_TERRACOTTA:
                case PINK_GLAZED_TERRACOTTA:
                case PINK_TERRACOTTA:
                case PURPLE_GLAZED_TERRACOTTA:
                case PURPLE_TERRACOTTA:
                case RED_GLAZED_TERRACOTTA:
                case RED_TERRACOTTA:
                case WHITE_GLAZED_TERRACOTTA:
                case WHITE_TERRACOTTA:
                case YELLOW_GLAZED_TERRACOTTA:
                case YELLOW_TERRACOTTA:
                    return pointsCore.getConfig().getDouble(pathTerracottaValue);
                case DIAMOND:
                case DIAMOND_ORE:
                case DIAMOND_BLOCK:
                case DEEPSLATE_DIAMOND_ORE:
                    return pointsCore.getConfig().getDouble(pathDiamondValue);
                case EMERALD:
                case EMERALD_BLOCK:
                case EMERALD_ORE:
                case DEEPSLATE_EMERALD_ORE:
                    return pointsCore.getConfig().getDouble(pathEmeraldValue);
                case IRON_ORE:
                case IRON_BARS:
                case RAW_IRON:
                case IRON_BLOCK:
                case IRON_TRAPDOOR:
                case RAW_IRON_BLOCK:
                case DEEPSLATE_IRON_ORE:
                    return pointsCore.getConfig().getDouble(pathIronValue);
                case GOLD_BLOCK:
                case NETHER_GOLD_ORE:
                case DEEPSLATE_GOLD_ORE:
                case GOLD_ORE:
                case RAW_GOLD:
                case RAW_GOLD_BLOCK:
                    return pointsCore.getConfig().getDouble(pathGoldValue);
                case REDSTONE_BLOCK:
                case REDSTONE_ORE:
                case DEEPSLATE_REDSTONE_ORE:
                    return pointsCore.getConfig().getDouble(pathRedstone);
                case PRISMARINE:
                case PRISMARINE_SLAB:
                case PRISMARINE_STAIRS:
                    return pointsCore.getConfig().getDouble(pathPrismarineValue);
                case PRISMARINE_BRICKS:
                case PRISMARINE_BRICK_SLAB:
                case PRISMARINE_BRICK_STAIRS:
                    return pointsCore.getConfig().getDouble(pathPrismarineBrickValue);
                case DEEPSLATE:
                case DEEPSLATE_BRICK_SLAB:
                case DEEPSLATE_BRICK_STAIRS:
                case DEEPSLATE_BRICK_WALL:
                case DEEPSLATE_BRICKS:
                case DEEPSLATE_TILE_SLAB:
                case DEEPSLATE_TILE_STAIRS:
                case DEEPSLATE_TILE_WALL:
                case DEEPSLATE_TILES:
                    return pointsCore.getConfig().getDouble(pathDeepslate);
                case SPAWNER:
                    return pointsCore.getConfig().getDouble(pathSpawner);
                case DROPPER:
                    return pointsCore.getConfig().getDouble(pathDropper);
                case OBSERVER:
                    return pointsCore.getConfig().getDouble(pathObserver);
                case SMOKER:
                    return pointsCore.getConfig().getDouble(pathSmoker);
                case FURNACE:
                    return pointsCore.getConfig().getDouble(pathFurnace);
                case RAIL:
                case ACTIVATOR_RAIL:
                case DETECTOR_RAIL:
                case POWERED_RAIL:
                    return pointsCore.getConfig().getDouble(pathRail);
                case NETHERITE_BLOCK:
                    return pointsCore.getConfig().getDouble(pathNetheriteBlock);
                case ANCIENT_DEBRIS:
                    return pointsCore.getConfig().getDouble(pathAncientDebris);
                case WAXED_COPPER_BLOCK:
                case WAXED_CUT_COPPER:
                case WAXED_CUT_COPPER_SLAB:
                case WAXED_CUT_COPPER_STAIRS:
                case WAXED_EXPOSED_COPPER:
                case WAXED_EXPOSED_CUT_COPPER:
                case WAXED_EXPOSED_CUT_COPPER_SLAB:
                case WAXED_EXPOSED_CUT_COPPER_STAIRS:
                case WAXED_OXIDIZED_COPPER:
                case WAXED_OXIDIZED_CUT_COPPER:
                case WAXED_OXIDIZED_CUT_COPPER_SLAB:
                case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
                case WAXED_WEATHERED_COPPER:
                case WAXED_WEATHERED_CUT_COPPER:
                case WAXED_WEATHERED_CUT_COPPER_SLAB:
                case WAXED_WEATHERED_CUT_COPPER_STAIRS:
                    return pointsCore.getConfig().getDouble(pathWaxedCopper);
                case DRIPSTONE_BLOCK:
                case POINTED_DRIPSTONE:
                    return pointsCore.getConfig().getDouble(pathDripstone);
                case MUD_BRICK_SLAB:
                case MUD_BRICK_STAIRS:
                case MUD_BRICKS:
                case MUD_BRICK_WALL:
                case BRICK_WALL:
                    return pointsCore.getConfig().getDouble(pathBricks);
                case TUFF:
                    return pointsCore.getConfig().getDouble(pathTuff);
                case BLACK_CONCRETE:
                case BLUE_CONCRETE:
                case CYAN_CONCRETE:
                case GRAY_CONCRETE:
                case BROWN_CONCRETE:
                case GREEN_CONCRETE:
                case LIGHT_BLUE_CONCRETE:
                case LIGHT_GRAY_CONCRETE:
                case LIME_CONCRETE:
                case MAGENTA_CONCRETE:
                case ORANGE_CONCRETE:
                case PINK_CONCRETE:
                case PURPLE_CONCRETE:
                case RED_CONCRETE:
                case WHITE_CONCRETE:
                case YELLOW_CONCRETE:
                    return pointsCore.getConfig().getDouble(pathConcrete);
                default:
                    return pointsCore.getConfig().getDouble(pathPOtherValue);
            }
        } else if(tool.equals(Tools.SHOVEL)) {
            switch (material) {
                case DIRT:
                case GRASS:
                case GRASS_BLOCK:
                    return pointsCore.getConfig().getDouble(pathDirt);
                case SAND:
                case RED_SAND:
                    return pointsCore.getConfig().getDouble(pathSand);
                case SOUL_SAND:
                    return pointsCore.getConfig().getDouble(pathSoulSand);
                case MYCELIUM:
                    return pointsCore.getConfig().getDouble(pathMycelium);
                case GRAVEL:
                    return pointsCore.getConfig().getDouble(pathGravel);
                case MUD:
                    return pointsCore.getConfig().getDouble(pathMud);
                case SNOW:
                    return pointsCore.getConfig().getDouble(pathSnow);
                case SOUL_SOIL:
                    return pointsCore.getConfig().getDouble(pathSoulSoil);
                case BLACK_CONCRETE_POWDER:
                case BLUE_CONCRETE_POWDER:
                case BROWN_CONCRETE_POWDER:
                case CYAN_CONCRETE_POWDER:
                case GRAY_CONCRETE_POWDER:
                case GREEN_CONCRETE_POWDER:
                case LIGHT_BLUE_CONCRETE_POWDER:
                case LIGHT_GRAY_CONCRETE_POWDER:
                case LIME_CONCRETE_POWDER:
                case MAGENTA_CONCRETE_POWDER:
                case ORANGE_CONCRETE_POWDER:
                case PINK_CONCRETE_POWDER:
                case PURPLE_CONCRETE_POWDER:
                case RED_CONCRETE_POWDER:
                case WHITE_CONCRETE_POWDER:
                case YELLOW_CONCRETE_POWDER:
                    return pointsCore.getConfig().getDouble(pathConcretePowder);
                case CLAY:
                    return pointsCore.getConfig().getDouble(pathClay);
                case MUDDY_MANGROVE_ROOTS:
                    return pointsCore.getConfig().getDouble(pathMuddyMangroveRoots);
                case FARMLAND:
                    return pointsCore.getConfig().getDouble(pathFarmland);
                case PODZOL:
                    return pointsCore.getConfig().getDouble(pathPodzol);
                default:
                    return pointsCore.getConfig().getDouble(pathSOtherValue);
            }
        } else if(tool.equals(Tools.AXE)) {
            switch (material) {
                case ACACIA_LOG:
                case ACACIA_WOOD:
                case STRIPPED_ACACIA_WOOD:
                case STRIPPED_ACACIA_LOG:
                case ACACIA_PLANKS:
                case ACACIA_BOAT:
                case ACACIA_BUTTON:
                case ACACIA_DOOR:
                case ACACIA_FENCE:
                case ACACIA_FENCE_GATE:
                case ACACIA_PRESSURE_PLATE:
                case ACACIA_SLAB:
                case ACACIA_STAIRS:
                case ACACIA_TRAPDOOR:
                    return pointsCore.getConfig().getDouble(pathAcaciaValues);
                case DARK_OAK_LOG:
                case DARK_OAK_WOOD:
                case STRIPPED_DARK_OAK_LOG:
                case STRIPPED_DARK_OAK_WOOD:
                case DARK_OAK_PLANKS:
                case DARK_OAK_BOAT:
                case DARK_OAK_BUTTON:
                case DARK_OAK_DOOR:
                case DARK_OAK_FENCE:
                case DARK_OAK_FENCE_GATE:
                case DARK_OAK_PRESSURE_PLATE:
                case DARK_OAK_SLAB:
                case DARK_OAK_STAIRS:
                case DARK_OAK_TRAPDOOR:
                case DARK_OAK_LEAVES:
                case DARK_OAK_SAPLING:
                    return pointsCore.getConfig().getDouble(pathDarkOakValues);
                case OAK_WOOD:
                case STRIPPED_OAK_LOG:
                case OAK_LOG:
                case STRIPPED_OAK_WOOD:
                case OAK_BOAT:
                case OAK_BUTTON:
                case OAK_DOOR:
                case OAK_FENCE:
                case OAK_FENCE_GATE:
                case OAK_PLANKS:
                case OAK_PRESSURE_PLATE:
                case OAK_SLAB:
                case OAK_STAIRS:
                case OAK_TRAPDOOR:
                    return pointsCore.getConfig().getDouble(pathOakValues);
                case JUNGLE_LOG:
                case STRIPPED_JUNGLE_LOG:
                case JUNGLE_WOOD:
                case STRIPPED_JUNGLE_WOOD:
                case JUNGLE_BOAT:
                case JUNGLE_BUTTON:
                case JUNGLE_DOOR:
                case JUNGLE_FENCE:
                case JUNGLE_FENCE_GATE:
                case JUNGLE_PLANKS:
                case JUNGLE_PRESSURE_PLATE:
                case JUNGLE_SLAB:
                case JUNGLE_STAIRS:
                case JUNGLE_TRAPDOOR:
                    return pointsCore.getConfig().getDouble(pathJungleValues);
                case SPRUCE_WOOD:
                case SPRUCE_LOG:
                case STRIPPED_SPRUCE_LOG:
                case STRIPPED_SPRUCE_WOOD:
                case SPRUCE_BOAT:
                case SPRUCE_BUTTON:
                case SPRUCE_DOOR:
                case SPRUCE_FENCE:
                case SPRUCE_FENCE_GATE:
                case SPRUCE_PLANKS:
                case SPRUCE_PRESSURE_PLATE:
                case SPRUCE_SLAB:
                case SPRUCE_STAIRS:
                case SPRUCE_TRAPDOOR:
                    return pointsCore.getConfig().getDouble(pathSpruceValues);
                case BIRCH_WOOD:
                case BIRCH_LOG:
                case STRIPPED_BIRCH_WOOD:
                case STRIPPED_BIRCH_LOG:
                case BIRCH_BOAT:
                case BIRCH_BUTTON:
                case BIRCH_DOOR:
                case BIRCH_FENCE:
                case BIRCH_FENCE_GATE:
                case BIRCH_PLANKS:
                case BIRCH_PRESSURE_PLATE:
                case BIRCH_SLAB:
                case BIRCH_STAIRS:
                case BIRCH_TRAPDOOR:
                    return pointsCore.getConfig().getDouble(pathBirchValues);
                case CHEST:
                    return pointsCore.getConfig().getDouble(pathChest);
                case LECTERN:
                    return pointsCore.getConfig().getDouble(pathLectern);
                case CRAFTING_TABLE:
                    return pointsCore.getConfig().getDouble(pathCraftingTable);
                case SMITHING_TABLE:
                    return pointsCore.getConfig().getDouble(pathSmithingTable);
                case LOOM:
                    return pointsCore.getConfig().getDouble(pathLoom);
                case CARTOGRAPHY_TABLE:
                    return pointsCore.getConfig().getDouble(pathCartographyTable);
                case FLETCHING_TABLE:
                    return pointsCore.getConfig().getDouble(pathFletchingTable);
                case BARREL:
                    return pointsCore.getConfig().getDouble(pathBarrel);
                case JUKEBOX:
                    return pointsCore.getConfig().getDouble(pathJukebox);
                case CAMPFIRE:
                case SOUL_CAMPFIRE:
                    return pointsCore.getConfig().getDouble(pathCampfire);
                case BOOKSHELF:
                    return pointsCore.getConfig().getDouble(pathBookshelf);
                case BLACK_BANNER:
                case BLACK_WALL_BANNER:
                case BLUE_BANNER:
                case BLUE_WALL_BANNER:
                case BROWN_BANNER:
                case BROWN_WALL_BANNER:
                case CREEPER_BANNER_PATTERN:
                case CYAN_BANNER:
                case CYAN_WALL_BANNER:
                case FLOWER_BANNER_PATTERN:
                case GLOBE_BANNER_PATTERN:
                case GRAY_BANNER:
                case GRAY_WALL_BANNER:
                case GREEN_BANNER:
                case GREEN_WALL_BANNER:
                case LIGHT_BLUE_BANNER:
                case LIGHT_BLUE_WALL_BANNER:
                case LIGHT_GRAY_BANNER:
                case LIGHT_GRAY_WALL_BANNER:
                case LIME_BANNER:
                case LIME_WALL_BANNER:
                case MAGENTA_BANNER:
                case MAGENTA_WALL_BANNER:
                case MOJANG_BANNER_PATTERN:
                case ORANGE_BANNER:
                case ORANGE_WALL_BANNER:
                case PIGLIN_BANNER_PATTERN:
                case PINK_BANNER:
                case PINK_WALL_BANNER:
                case PURPLE_BANNER:
                case PURPLE_WALL_BANNER:
                case RED_BANNER:
                case RED_WALL_BANNER:
                case SKULL_BANNER_PATTERN:
                case WHITE_BANNER:
                case WHITE_WALL_BANNER:
                case YELLOW_BANNER:
                case YELLOW_WALL_BANNER:
                    return pointsCore.getConfig().getDouble(pathBanner);
                case JACK_O_LANTERN:
                    return pointsCore.getConfig().getDouble(pathJackLantern);
                case PUMPKIN:
                    return pointsCore.getConfig().getDouble(pathPumpkin);
                case MELON:
                    return pointsCore.getConfig().getDouble(pathMelon);
                case SPRUCE_SIGN:
                case SPRUCE_HANGING_SIGN:
                case SPRUCE_WALL_HANGING_SIGN:
                case SPRUCE_WALL_SIGN:
                case ACACIA_HANGING_SIGN:
                case ACACIA_SIGN:
                case ACACIA_WALL_HANGING_SIGN:
                case ACACIA_WALL_SIGN:
                case BAMBOO_HANGING_SIGN:
                case BAMBOO_SIGN:
                case BAMBOO_WALL_HANGING_SIGN:
                case BAMBOO_WALL_SIGN:
                case BIRCH_HANGING_SIGN:
                case BIRCH_SIGN:
                case BIRCH_WALL_HANGING_SIGN:
                case BIRCH_WALL_SIGN:
                case CRIMSON_HANGING_SIGN:
                case CRIMSON_SIGN:
                case CRIMSON_WALL_HANGING_SIGN:
                case CRIMSON_WALL_SIGN:
                case DARK_OAK_HANGING_SIGN:
                case DARK_OAK_SIGN:
                case DARK_OAK_WALL_HANGING_SIGN:
                case DARK_OAK_WALL_SIGN:
                case JUNGLE_HANGING_SIGN:
                case JUNGLE_SIGN:
                case JUNGLE_WALL_HANGING_SIGN:
                case JUNGLE_WALL_SIGN:
                case MANGROVE_HANGING_SIGN:
                case MANGROVE_SIGN:
                case MANGROVE_WALL_HANGING_SIGN:
                case MANGROVE_WALL_SIGN:
                case OAK_HANGING_SIGN:
                case OAK_SIGN:
                case OAK_WALL_HANGING_SIGN:
                case OAK_WALL_SIGN:
                case WARPED_HANGING_SIGN:
                case WARPED_SIGN:
                case WARPED_WALL_HANGING_SIGN:
                case WARPED_WALL_SIGN:
                    return pointsCore.getConfig().getDouble(pathSign);
                case NOTE_BLOCK:
                    return pointsCore.getConfig().getDouble(pathNoteblock);
                case MANGROVE_ROOTS:
                    return pointsCore.getConfig().getDouble(pathMangroveRoots);
                case BEEHIVE:
                    return pointsCore.getConfig().getDouble(pathBeehive);
                case BEE_NEST:
                    return pointsCore.getConfig().getDouble(pathBeeNest);
                case COMPOSTER:
                    return pointsCore.getConfig().getDouble(pathComposter);
                case BAMBOO:
                case BAMBOO_BLOCK:
                case BAMBOO_BUTTON:
                case BAMBOO_CHEST_RAFT:
                case BAMBOO_DOOR:
                case BAMBOO_FENCE:
                case BAMBOO_FENCE_GATE:
                case BAMBOO_MOSAIC:
                case BAMBOO_MOSAIC_SLAB:
                case BAMBOO_MOSAIC_STAIRS:
                case BAMBOO_PLANKS:
                case BAMBOO_PRESSURE_PLATE:
                case BAMBOO_RAFT:
                case BAMBOO_SLAB:
                case BAMBOO_STAIRS:
                case BAMBOO_TRAPDOOR:
                    return pointsCore.getConfig().getDouble(pathBamboo);
                default:
                    return pointsCore.getConfig().getDouble(pathAOtherValue);
            }
        } else if (tool.equals(Tools.HOE)) {
            if(material.equals(Material.COARSE_DIRT)) {
                return pointsCore.getConfig().getDouble(pathCoarse);
            } else if(material.equals(Material.DIRT) || material.equals(Material.GRASS_BLOCK)){
                return pointsCore.getConfig().getDouble(pathHoe);
            } else {
                return 0.0;
            }
        } else if(tool.equals(Tools.FISH_ROD)) {
            switch(material) {
                case SALMON:
                    return pointsCore.getConfig().getDouble(pathSalmonValues);
                case TROPICAL_FISH:
                    return pointsCore.getConfig().getDouble(pathTropicalFishValues);
                case COD:
                    return pointsCore.getConfig().getDouble(pathCodValues);
                case PUFFERFISH:
                    return pointsCore.getConfig().getDouble(pathPufferfishValues);
                default:
                    return pointsCore.getConfig().getDouble(pathFOtherValues);
            }
        } else {
            //Message.errorMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + "An error has occurred due to the tool type not being found.", pointsCore.getServer().getConsoleSender());
            return 0.0;
        }
    }

    public double getDamageValues(Tools tool, EntityType type) {
        switch(tool) {
            case MELEE_WEAPON:
            case AXE:
                return getConfigValue(pathMeleeWeaponsValues, type);
            case RANGED_WEAPON:
                return getConfigValue(pathRangedWeaponsValues, type);
            case ARMOR:
                return getConfigValue(pathArmorPointsValues, type);
            default:
                //Message.errorMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + "An error has occurred due to the tool type not being found in weapons.", pointsCore.getServer().getConsoleSender());
                return 0.0;
        }
    }

    private double getConfigValue(String tool, EntityType entity) {
        switch(entity) {
            case BAT:
                return pointsCore.getConfig().getDouble(tool + pathBat);
            case POLAR_BEAR:
                return pointsCore.getConfig().getDouble(tool+pathPolarBear);
            case BLAZE:
                return pointsCore.getConfig().getDouble(tool+pathBlaze);
            case CAVE_SPIDER:
                return pointsCore.getConfig().getDouble(tool+pathCaveSpider);
            case PARROT:
                return pointsCore.getConfig().getDouble(tool+pathParrot);
            case CREEPER:
                return pointsCore.getConfig().getDouble(tool+pathCreeper);
            case ELDER_GUARDIAN:
                return pointsCore.getConfig().getDouble(tool+pathElderGuardian);
            case GUARDIAN:
                return pointsCore.getConfig().getDouble(tool+pathGuardian);
            case ENDER_DRAGON:
                return pointsCore.getConfig().getDouble(tool+pathEnderDragon);
            case ENDERMAN:
                return pointsCore.getConfig().getDouble(tool+pathEnderman);
            case ENDERMITE:
                return pointsCore.getConfig().getDouble(tool+pathEndermite);
            case EVOKER:
                return pointsCore.getConfig().getDouble(tool+pathEvoker);
            case GHAST:
                return pointsCore.getConfig().getDouble(tool+pathGhast);
            case HUSK:
                return pointsCore.getConfig().getDouble(tool+pathHusk);
            case ILLUSIONER:
                return pointsCore.getConfig().getDouble(tool+pathIllusioner);
            case MAGMA_CUBE:
                return pointsCore.getConfig().getDouble(tool+pathMagmaCube);
            case PHANTOM:
                return pointsCore.getConfig().getDouble(tool+pathPhantom);
            case STRAY:
                return pointsCore.getConfig().getDouble(tool+pathStray);
            case SLIME:
                return pointsCore.getConfig().getDouble(tool+pathSlime);
            case VEX:
                return pointsCore.getConfig().getDouble(tool+pathVex);
            case VINDICATOR:
                return pointsCore.getConfig().getDouble(tool+pathVindicator);
            case WITHER:
                return pointsCore.getConfig().getDouble(tool+pathWither);
            case WITHER_SKELETON:
                return pointsCore.getConfig().getDouble(tool+pathWitherSkeleton);
            case WITCH:
                return pointsCore.getConfig().getDouble(tool+pathWitch);
            case BEE:
                return pointsCore.getConfig().getDouble(tool+".Bee");
            case CAT:
                return pointsCore.getConfig().getDouble(tool+".Cat");
            case FOX:
                return pointsCore.getConfig().getDouble(tool+".Fox");
            case WARDEN:
                return pointsCore.getConfig().getDouble(tool+pathWarden);
            case ZOMBIE:
                return pointsCore.getConfig().getDouble(tool+pathZombie);
            case ZOMBIE_HORSE:
                return pointsCore.getConfig().getDouble(tool+pathZombieHorse);
            case ZOMBIFIED_PIGLIN:
                return pointsCore.getConfig().getDouble(tool+pathZombieMan);
            case CHICKEN:
                return pointsCore.getConfig().getDouble(tool+pathChicken);
            case COW:
                return pointsCore.getConfig().getDouble(tool+pathCow);
            case HORSE:
                return pointsCore.getConfig().getDouble(tool+pathHorse);
            case LLAMA:
                return pointsCore.getConfig().getDouble(tool+pathLlama);
            case COD:
                return pointsCore.getConfig().getDouble(tool+pathCod);
            case MULE:
            case DONKEY:
                return pointsCore.getConfig().getDouble(tool+pathDonkey);
            case WOLF:
                return pointsCore.getConfig().getDouble(tool+pathWolf);
            case SKELETON_HORSE:
                return pointsCore.getConfig().getDouble(tool+pathSkeletonHorse);
            case PLAYER:
                return pointsCore.getConfig().getDouble(tool+pathPlayer);
            case MUSHROOM_COW:
                return pointsCore.getConfig().getDouble(tool+pathMushroomCow);
            case SHEEP:
                return pointsCore.getConfig().getDouble(tool+pathSheep);
            case PIG:
                return pointsCore.getConfig().getDouble(tool+pathPig);
            case TURTLE:
                return pointsCore.getConfig().getDouble(tool+pathTurtle);
            case FIREBALL:
            case SMALL_FIREBALL:
                return  pointsCore.getConfig().getDouble(tool+pathFireball );
            case DRAGON_FIREBALL:
                return  pointsCore.getConfig().getDouble(tool+pathDragonFireball );
            case SPIDER:
                return pointsCore.getConfig().getDouble(tool+pathSpider);
            default:
                Message.debugMessage("There is a missing entity in getConfigValue() =>  ValueUtil, the missing entity is:\n"+entity.name(), DebugIntensity.LIGHT);
                return pointsCore.getConfig().getDouble(tool+pathOther);
        }
    }
}

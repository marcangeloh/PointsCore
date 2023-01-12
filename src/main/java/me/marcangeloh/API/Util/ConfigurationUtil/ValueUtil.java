package me.marcangeloh.API.Util.ConfigurationUtil;

import me.marcangeloh.PointsCore;
import me.marcangeloh.API.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class ValueUtil implements Paths {
    private Plugin plugin = PointsCore.plugin;

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
                || tool.equals(Material.TRIDENT)){
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
                    return plugin.getConfig().getDouble(pathIceValue);
                case BRICK:
                case BRICKS:
                case BRICK_SLAB:
                case BRICK_STAIRS:
                    return plugin.getConfig().getDouble(pathBrickValue);
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
                    return plugin.getConfig().getDouble(pathQuartzValue);
                case STONE:
                case STONE_BRICK_SLAB:
                case STONE_BRICK_STAIRS:
                case STONE_BRICK_WALL:
                case STONE_BRICKS:
                case STONE_SLAB:
                case STONE_STAIRS:
                case STONE_BUTTON:
                    return plugin.getConfig().getDouble(pathStoneValue);
                case DIORITE:
                case DIORITE_SLAB:
                case DIORITE_STAIRS:
                case DIORITE_WALL:
                case POLISHED_DIORITE:
                case POLISHED_DIORITE_SLAB:
                case POLISHED_DIORITE_STAIRS:
                    return plugin.getConfig().getDouble(pathDioriteValue);
                case GRANITE:
                case GRANITE_SLAB:
                case GRANITE_STAIRS:
                case GRANITE_WALL:
                case POLISHED_GRANITE:
                case POLISHED_GRANITE_SLAB:
                case POLISHED_GRANITE_STAIRS:
                    return plugin.getConfig().getDouble(pathGraniteValue);
                case ANDESITE:
                case ANDESITE_SLAB:
                case ANDESITE_STAIRS:
                case ANDESITE_WALL:
                case POLISHED_ANDESITE:
                case POLISHED_ANDESITE_SLAB:
                case POLISHED_ANDESITE_STAIRS:
                    return plugin.getConfig().getDouble(pathAndesiteValue);
                case SANDSTONE:
                    return plugin.getConfig().getDouble(pathSandstoneValue);
                case CHISELED_SANDSTONE:
                    return plugin.getConfig().getDouble(pathChiseledSandstoneValue);
                case CHISELED_RED_SANDSTONE:
                    return plugin.getConfig().getDouble(pathChiseledRedSandstoneValue);
                case CUT_SANDSTONE:
                    return plugin.getConfig().getDouble(pathCutSandstoneValue);
                case CUT_RED_SANDSTONE:
                    return plugin.getConfig().getDouble(pathCutRedSandstoneValue);
                case SMOOTH_SANDSTONE:
                    return plugin.getConfig().getDouble(pathSmoothSandstoneValue);
                case SMOOTH_RED_SANDSTONE:
                    return plugin.getConfig().getDouble(pathSmoothRedSandstoneValue);
                case NETHERRACK:
                    return plugin.getConfig().getDouble(pathNetherrackValue);
                case NETHER_BRICK:
                case NETHER_BRICK_FENCE:
                case NETHER_BRICK_SLAB:
                case NETHER_BRICK_STAIRS:
                case NETHER_BRICKS:
                case CHISELED_NETHER_BRICKS:
                case CRACKED_NETHER_BRICKS:
                case NETHER_BRICK_WALL:
                    return plugin.getConfig().getDouble(pathNetherBrickValue);
                case LAPIS_BLOCK:
                case LAPIS_LAZULI:
                case LAPIS_ORE:
                case DEEPSLATE_LAPIS_ORE:
                    return plugin.getConfig().getDouble(pathLapisValue);
                case COAL:
                case COAL_BLOCK:
                case COAL_ORE:
                case DEEPSLATE_COAL_ORE:
                    return plugin.getConfig().getDouble(pathCoalValue);
                case COPPER_ORE:
                case COPPER_BLOCK:
                case RAW_COPPER:
                case RAW_COPPER_BLOCK:
                case DEEPSLATE_COPPER_ORE:
                    return plugin.getConfig().getDouble(pathCopperValue);
                case CRYING_OBSIDIAN:
                    return plugin.getConfig().getDouble(pathCryingObsidian);
                case END_STONE:
                case END_STONE_BRICKS:
                    return plugin.getConfig().getDouble(pathEndStoneValue);
                case OBSIDIAN:
                    return plugin.getConfig().getDouble(pathObsidianValue);
                case BLACKSTONE:
                case BLACKSTONE_SLAB:
                case BLACKSTONE_STAIRS:
                case BLACKSTONE_WALL:
                case GILDED_BLACKSTONE:
                case POLISHED_BLACKSTONE:
                case CHISELED_POLISHED_BLACKSTONE:
                case CRACKED_POLISHED_BLACKSTONE_BRICKS:
                case POLISHED_BLACKSTONE_BRICK_SLAB:
                    return plugin.getConfig().getDouble(pathBlackstone);
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
                    return plugin.getConfig().getDouble(pathTerracottaValue);
                case DIAMOND:
                case DIAMOND_ORE:
                case DIAMOND_BLOCK:
                case DEEPSLATE_DIAMOND_ORE:
                    return plugin.getConfig().getDouble(pathDiamondValue);
                case EMERALD:
                case EMERALD_BLOCK:
                case EMERALD_ORE:
                case DEEPSLATE_EMERALD_ORE:
                    return plugin.getConfig().getDouble(pathEmeraldValue);
                case IRON_ORE:
                case IRON_BARS:
                case RAW_IRON:
                case IRON_BLOCK:
                case IRON_TRAPDOOR:
                case RAW_IRON_BLOCK:
                case DEEPSLATE_IRON_ORE:
                    return plugin.getConfig().getDouble(pathIronValue);
                case GOLD_BLOCK:
                case NETHER_GOLD_ORE:
                case DEEPSLATE_GOLD_ORE:
                case GOLD_ORE:
                case RAW_GOLD:
                case RAW_GOLD_BLOCK:
                    return plugin.getConfig().getDouble(pathGoldValue);
                case REDSTONE_BLOCK:
                case REDSTONE_ORE:
                case DEEPSLATE_REDSTONE_ORE:
                    return plugin.getConfig().getDouble(pathRedstone);
                case PRISMARINE:
                case PRISMARINE_SLAB:
                case PRISMARINE_STAIRS:
                    return plugin.getConfig().getDouble(pathPrismarineValue);
                case PRISMARINE_BRICKS:
                case PRISMARINE_BRICK_SLAB:
                case PRISMARINE_BRICK_STAIRS:
                    return plugin.getConfig().getDouble(pathPrismarineBrickValue);
                case DEEPSLATE:
                case DEEPSLATE_BRICK_SLAB:
                case DEEPSLATE_BRICK_STAIRS:
                case DEEPSLATE_BRICK_WALL:
                case DEEPSLATE_BRICKS:
                case DEEPSLATE_TILE_SLAB:
                case DEEPSLATE_TILE_STAIRS:
                case DEEPSLATE_TILE_WALL:
                case DEEPSLATE_TILES:
                    return plugin.getConfig().getDouble(pathDeepslate);
                case SPAWNER:
                    return plugin.getConfig().getDouble(pathSpawner);
                case DROPPER:
                    return plugin.getConfig().getDouble(pathDropper);
                case OBSERVER:
                    return plugin.getConfig().getDouble(pathObserver);
                case SMOKER:
                    return plugin.getConfig().getDouble(pathSmoker);
                case FURNACE:
                    return plugin.getConfig().getDouble(pathFurnace);
                case RAIL:
                case ACTIVATOR_RAIL:
                case DETECTOR_RAIL:
                case POWERED_RAIL:
                    return plugin.getConfig().getDouble(pathRail);
                case NETHERITE_BLOCK:
                    return plugin.getConfig().getDouble(pathNetheriteBlock);
                case ANCIENT_DEBRIS:
                    return plugin.getConfig().getDouble(pathAncientDebris);
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
                    return plugin.getConfig().getDouble(pathWaxedCopper);
                case DRIPSTONE_BLOCK:
                case POINTED_DRIPSTONE:
                    return plugin.getConfig().getDouble(pathDripstone);
                case MUD_BRICK_SLAB:
                case MUD_BRICK_STAIRS:
                case MUD_BRICKS:
                case MUD_BRICK_WALL:
                case BRICK_WALL:
                    return plugin.getConfig().getDouble(pathBricks);
                case TUFF:
                    return plugin.getConfig().getDouble(pathTuff);
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
                    return plugin.getConfig().getDouble(pathConcrete);
                default:
                    return plugin.getConfig().getDouble(pathPOtherValue);
            }
        } else if(tool.equals(Tools.SHOVEL)) {
            switch (material) {
                case DIRT:
                case GRASS:
                case GRASS_BLOCK:
                    return plugin.getConfig().getDouble(pathDirt);
                case SAND:
                case RED_SAND:
                    return plugin.getConfig().getDouble(pathSand);
                case SOUL_SAND:
                    return plugin.getConfig().getDouble(pathSoulSand);
                case MYCELIUM:
                    return plugin.getConfig().getDouble(pathMycelium);
                case GRAVEL:
                    return plugin.getConfig().getDouble(pathGravel);
                case MUD:
                    return plugin.getConfig().getDouble(pathMud);
                case SNOW:
                    return plugin.getConfig().getDouble(pathSnow);
                case SOUL_SOIL:
                    return plugin.getConfig().getDouble(pathSoulSoil);
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
                    return plugin.getConfig().getDouble(pathConcretePowder);
                case CLAY:
                    return plugin.getConfig().getDouble(pathClay);
                case MUDDY_MANGROVE_ROOTS:
                    return plugin.getConfig().getDouble(pathMuddyMangroveRoots);
                case FARMLAND:
                    return plugin.getConfig().getDouble(pathFarmland);
                case PODZOL:
                    return plugin.getConfig().getDouble(pathPodzol);
                default:
                    return plugin.getConfig().getDouble(pathSOtherValue);
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
                    return plugin.getConfig().getDouble(pathAcaciaValues);
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
                    return plugin.getConfig().getDouble(pathDarkOakValues);
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
                    return plugin.getConfig().getDouble(pathOakValues);
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
                    return plugin.getConfig().getDouble(pathJungleValues);
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
                    return plugin.getConfig().getDouble(pathSpruceValues);
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
                    return plugin.getConfig().getDouble(pathBirchValues);
                case CHEST:
                    return plugin.getConfig().getDouble(pathChest);
                case LECTERN:
                    return plugin.getConfig().getDouble(pathLectern);
                case CRAFTING_TABLE:
                    return plugin.getConfig().getDouble(pathCraftingTable);
                case SMITHING_TABLE:
                    return plugin.getConfig().getDouble(pathSmithingTable);
                case LOOM:
                    return plugin.getConfig().getDouble(pathLoom);
                case CARTOGRAPHY_TABLE:
                    return plugin.getConfig().getDouble(pathCartographyTable);
                case FLETCHING_TABLE:
                    return plugin.getConfig().getDouble(pathFletchingTable);
                case BARREL:
                    return plugin.getConfig().getDouble(pathBarrel);
                case JUKEBOX:
                    return plugin.getConfig().getDouble(pathJukebox);
                case CAMPFIRE:
                case SOUL_CAMPFIRE:
                    return plugin.getConfig().getDouble(pathCampfire);
                case BOOKSHELF:
                    return plugin.getConfig().getDouble(pathBookshelf);
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
                    return plugin.getConfig().getDouble(pathBanner);
                case JACK_O_LANTERN:
                    return plugin.getConfig().getDouble(pathJackLantern);
                case PUMPKIN:
                    return plugin.getConfig().getDouble(pathPumpkin);
                case MELON:
                    return plugin.getConfig().getDouble(pathMelon);
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
                    return plugin.getConfig().getDouble(pathSign);
                case NOTE_BLOCK:
                    return plugin.getConfig().getDouble(pathNoteblock);
                case MANGROVE_ROOTS:
                    return plugin.getConfig().getDouble(pathMangroveRoots);
                case BEEHIVE:
                    return plugin.getConfig().getDouble(pathBeehive);
                case BEE_NEST:
                    return plugin.getConfig().getDouble(pathBeeNest);
                case COMPOSTER:
                    return plugin.getConfig().getDouble(pathComposter);
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
                    return plugin.getConfig().getDouble(pathBamboo);
                default:
                    return plugin.getConfig().getDouble(pathAOtherValue);
            }
        } else if (tool.equals(Tools.HOE)) {
            if(material.equals(Material.COARSE_DIRT)) {
                return plugin.getConfig().getDouble(pathCoarse);
            } else if(material.equals(Material.DIRT) || material.equals(Material.GRASS_BLOCK)){
                return plugin.getConfig().getDouble(pathHoe);
            } else {
                return 0.0;
            }
        } else if(tool.equals(Tools.FISH_ROD)) {
            switch(material) {
                case SALMON:
                    return plugin.getConfig().getDouble(pathSalmonValues);
                case TROPICAL_FISH:
                    return plugin.getConfig().getDouble(pathTropicalFishValues);
                case COD:
                    return plugin.getConfig().getDouble(pathCodValues);
                case PUFFERFISH:
                    return plugin.getConfig().getDouble(pathPufferfishValues);
                default:
                    return plugin.getConfig().getDouble(pathFOtherValues);
            }
        } else {
            Message.errorMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + "An error has occurred due to the tool type not being found.", plugin.getServer().getConsoleSender());
            return 0.0;
        }
    }

    public double getDamageValues(Tools tool, EntityType type) {
        switch(tool) {
            case MELEE_WEAPON:
                return getConfigValue(pathMeleeWeaponsValues, type);
            case RANGED_WEAPON:
                return getConfigValue(pathRangedWeaponsValues, type);
            case ARMOR:
                return getConfigValue(pathArmorPointsValues, type);
            default:
                Message.errorMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + "An error has occurred due to the tool type not being found in weapons.", plugin.getServer().getConsoleSender());
                return 0.0;
        }
    }

    private double getConfigValue(String tool, EntityType entity) {
        switch(entity) {
            case BAT:
                return plugin.getConfig().getDouble(tool + pathBat);
            case POLAR_BEAR:
                return plugin.getConfig().getDouble(tool+pathPolarBear);
            case BLAZE:
                return plugin.getConfig().getDouble(tool+pathBlaze);
            case CAVE_SPIDER:
                return plugin.getConfig().getDouble(tool+pathCaveSpider);
            case PARROT:
                return plugin.getConfig().getDouble(tool+pathParrot);
            case CREEPER:
                return plugin.getConfig().getDouble(tool+pathCreeper);
            case ELDER_GUARDIAN:
                return plugin.getConfig().getDouble(tool+pathElderGuardian);
            case GUARDIAN:
                return plugin.getConfig().getDouble(tool+pathGuardian);
            case ENDER_DRAGON:
                return plugin.getConfig().getDouble(tool+pathEnderDragon);
            case ENDERMAN:
                return plugin.getConfig().getDouble(tool+pathEnderman);
            case ENDERMITE:
                return plugin.getConfig().getDouble(tool+pathEndermite);
            case EVOKER:
                return plugin.getConfig().getDouble(tool+pathEvoker);
            case GHAST:
                return plugin.getConfig().getDouble(tool+pathGhast);
            case HUSK:
                return plugin.getConfig().getDouble(tool+pathHusk);
            case ILLUSIONER:
                return plugin.getConfig().getDouble(tool+pathIllusioner);
            case MAGMA_CUBE:
                return plugin.getConfig().getDouble(tool+pathMagmaCube);
            case PHANTOM:
                return plugin.getConfig().getDouble(tool+pathPhantom);
            case STRAY:
                return plugin.getConfig().getDouble(tool+pathStray);
            case SLIME:
                return plugin.getConfig().getDouble(tool+pathSlime);
            case VEX:
                return plugin.getConfig().getDouble(tool+pathVex);
            case VINDICATOR:
                return plugin.getConfig().getDouble(tool+pathVindicator);
            case WITHER:
                return plugin.getConfig().getDouble(tool+pathWither);
            case WITHER_SKELETON:
                return plugin.getConfig().getDouble(tool+pathWitherSkeleton);
            case WITCH:
                return plugin.getConfig().getDouble(tool+pathWitch);
            case ZOMBIE:
                return plugin.getConfig().getDouble(tool+pathZombie);
            case ZOMBIE_HORSE:
                return plugin.getConfig().getDouble(tool+pathZombieHorse);
            case ZOMBIFIED_PIGLIN:
                return plugin.getConfig().getDouble(tool+pathZombieMan);
            case CHICKEN:
                return plugin.getConfig().getDouble(tool+pathChicken);
            case COW:
                return plugin.getConfig().getDouble(tool+pathCow);
            case HORSE:
                return plugin.getConfig().getDouble(tool+pathHorse);
            case LLAMA:
                return plugin.getConfig().getDouble(tool+pathLlama);
            case COD:
                return plugin.getConfig().getDouble(tool+pathCod);
            case MULE:
            case DONKEY:
                return plugin.getConfig().getDouble(tool+pathDonkey);
            case WOLF:
                return plugin.getConfig().getDouble(tool+pathWolf);
            case SKELETON_HORSE:
                return plugin.getConfig().getDouble(tool+pathSkeletonHorse);
            case PLAYER:
                return plugin.getConfig().getDouble(tool+pathPlayer);
            case MUSHROOM_COW:
                return plugin.getConfig().getDouble(tool+pathMushroomCow);
            case SHEEP:
                return plugin.getConfig().getDouble(tool+pathSheep);
            case PIG:
                return plugin.getConfig().getDouble(tool+pathPig);
            case TURTLE:
                return plugin.getConfig().getDouble(tool+pathTurtle);
            case FIREBALL:
            case SMALL_FIREBALL:
                return  plugin.getConfig().getDouble(tool+pathFireball );
            case DRAGON_FIREBALL:
                return  plugin.getConfig().getDouble(tool+pathDragonFireball );
            case SPIDER:
                return plugin.getConfig().getDouble(tool+pathSpider);
            default:
                Message.debugMessage("There is a missing entity in getConfigValue() =>  ValueUtil, the missing entity is:\n"+entity.name(), DebugIntensity.LIGHT);
                return plugin.getConfig().getDouble(tool+pathOther);
        }
    }
}

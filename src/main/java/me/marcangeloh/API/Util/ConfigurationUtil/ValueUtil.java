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
                tool.equals(Material.WOODEN_AXE)) {
            return Tools.AXE;
        } else if(tool.equals(Material.DIAMOND_PICKAXE) ||
                tool.equals(Material.GOLDEN_PICKAXE) ||
                tool.equals(Material.IRON_PICKAXE) ||
                tool.equals(Material.STONE_PICKAXE) ||
                tool.equals(Material.WOODEN_PICKAXE)) {
            return Tools.PICKAXE;
        } else if(tool.equals(Material.DIAMOND_SHOVEL) ||
                tool.equals(Material.GOLDEN_SHOVEL) ||
                tool.equals(Material.IRON_SHOVEL) ||
                tool.equals(Material.STONE_SHOVEL) ||
                tool.equals(Material.WOODEN_SHOVEL)) {
            return Tools.SHOVEL;
        }else if(tool.equals(Material.DIAMOND_HOE) ||
                tool.equals(Material.GOLDEN_HOE) ||
                tool.equals(Material.IRON_HOE) ||
                tool.equals(Material.STONE_HOE) ||
                tool.equals(Material.WOODEN_HOE)) {
            return Tools.HOE;
        } else if(tool.equals(Material.FISHING_ROD)) {
            return Tools.FISH_ROD;
        } else if(tool.equals(Material.DIAMOND_SWORD) ||
                tool.equals(Material.GOLDEN_SWORD) ||
                tool.equals(Material.IRON_SWORD) ||
                tool.equals(Material.STONE_SWORD) ||
                tool.equals(Material.WOODEN_SWORD)) {
            return Tools.MELEE_WEAPON;
        } else if(tool.equals(Material.BOW)
                || tool.equals(Material.TRIDENT)){
            return Tools.RANGED_WEAPON;
        } else if(tool.equals(Material.CHAINMAIL_BOOTS) ||
                tool.equals(Material.IRON_BOOTS)||
                tool.equals(Material.LEATHER_BOOTS)||
                tool.equals(Material.DIAMOND_BOOTS)
                || tool.equals(Material.GOLDEN_BOOTS)||
                tool.equals(Material.CHAINMAIL_LEGGINGS) ||
                tool.equals(Material.IRON_LEGGINGS)||
                tool.equals(Material.LEATHER_LEGGINGS)||
                tool.equals(Material.DIAMOND_LEGGINGS)
                || tool.equals(Material.GOLDEN_LEGGINGS)||
                tool.equals(Material.CHAINMAIL_CHESTPLATE) ||
                tool.equals(Material.IRON_CHESTPLATE)||
                tool.equals(Material.LEATHER_CHESTPLATE)||
                tool.equals(Material.DIAMOND_CHESTPLATE)
                || tool.equals(Material.GOLDEN_CHESTPLATE)||
                tool.equals(Material.CHAINMAIL_HELMET) ||
                tool.equals(Material.IRON_HELMET)||
                tool.equals(Material.LEATHER_HELMET)||
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
                    return plugin.getConfig().getDouble(pathQuartzValue);
                case STONE:
                    return plugin.getConfig().getDouble(pathStoneValue);
                case DIORITE:
                    return plugin.getConfig().getDouble(pathDioriteValue);
                case GRANITE:
                    return plugin.getConfig().getDouble(pathGraniteValue);
                case ANDESITE:
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
                    return plugin.getConfig().getDouble(pathNetherBrickValue);
                case LAPIS_BLOCK:
                case LAPIS_LAZULI:
                case LAPIS_ORE:
                    return plugin.getConfig().getDouble(pathLapisValue);
                case COAL:
                case COAL_BLOCK:
                case COAL_ORE:
                    return plugin.getConfig().getDouble(pathCoalValue);
                case END_STONE:
                case END_STONE_BRICKS:
                    return plugin.getConfig().getDouble(pathEndStoneValue);
                case OBSIDIAN:
                    return plugin.getConfig().getDouble(pathObsidianValue);
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
                    return plugin.getConfig().getDouble(pathDiamondValue);
                case EMERALD:
                case EMERALD_BLOCK:
                case EMERALD_ORE:
                    return plugin.getConfig().getDouble(pathEmeraldValue);
                case IRON_ORE:
                case IRON_BARS:
                case IRON_BLOCK:
                case IRON_TRAPDOOR:
                    return plugin.getConfig().getDouble(pathIronValue);
                case GOLD_BLOCK:
                case GOLD_ORE:
                    return plugin.getConfig().getDouble(pathGoldValue);
                case PRISMARINE:
                case PRISMARINE_SLAB:
                case PRISMARINE_STAIRS:
                    return plugin.getConfig().getDouble(pathPrismarineValue);
                case PRISMARINE_BRICKS:
                case PRISMARINE_BRICK_SLAB:
                case PRISMARINE_BRICK_STAIRS:
                    return plugin.getConfig().getDouble(pathPrismarineBrickValue);
                default:
                    return plugin.getConfig().getDouble(pathPOtherValue);
            }
        } else if(tool.equals(Tools.SHOVEL)) {
            switch (material) {
                case DIRT:
                case GRASS:
                case GRASS_BLOCK:
                case GRASS_PATH:
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
                default:
                    return plugin.getConfig().getDouble(pathSOtherValue);
            }
        } else if(tool.equals(Tools.AXE)) {
            switch (material) {
                case ACACIA_LOG:
                case ACACIA_WOOD:
                case STRIPPED_ACACIA_WOOD:
                case STRIPPED_ACACIA_LOG:
                    return plugin.getConfig().getDouble(pathAcaciaValues);
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
                    return plugin.getConfig().getDouble(pathAcaciaValues)/4;
                case DARK_OAK_LOG:
                case DARK_OAK_WOOD:
                case STRIPPED_DARK_OAK_LOG:
                case STRIPPED_DARK_OAK_WOOD:
                    return plugin.getConfig().getDouble(pathDarkOakValues);
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
                    return plugin.getConfig().getDouble(pathDarkOakValues)/4;
                case OAK_WOOD:
                case STRIPPED_OAK_LOG:
                case OAK_LOG:
                case STRIPPED_OAK_WOOD:
                    return plugin.getConfig().getDouble(pathOakValues);
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
                    return plugin.getConfig().getDouble(pathOakValues)/4;
                case JUNGLE_LOG:
                case STRIPPED_JUNGLE_LOG:
                case JUNGLE_WOOD:
                case STRIPPED_JUNGLE_WOOD:
                    return plugin.getConfig().getDouble(pathJungleValues);
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
                    return plugin.getConfig().getDouble(pathJungleValues)/4;
                case SPRUCE_WOOD:
                case SPRUCE_LOG:
                case STRIPPED_SPRUCE_LOG:
                case STRIPPED_SPRUCE_WOOD:
                    return plugin.getConfig().getDouble(pathSpruceValues);
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
                    return plugin.getConfig().getDouble(pathSpruceValues)/4;
                case BIRCH_WOOD:
                case BIRCH_LOG:
                case STRIPPED_BIRCH_WOOD:
                case STRIPPED_BIRCH_LOG:
                    return plugin.getConfig().getDouble(pathBirchValues);
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
                    return plugin.getConfig().getDouble(pathBirchValues)/4;
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
            case PIG_ZOMBIE:
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

package me.marcangeloh.Events;

import me.marcangeloh.PointsCore;
import me.marcangeloh.Util.ConfigurationUtil.ValueUtil;
import me.marcangeloh.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.Util.GeneralUtil.Message;
import me.marcangeloh.Util.GeneralUtil.Tools;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ToolEvents implements Listener {
    private ValueUtil valueUtil = new ValueUtil(PointsCore.plugin);


    /**
     * Adds the points to the proper tool
     * @param player Player to add points to
     * @param incrementValue The amount of points to add
     * @param toolType the tool used
     */
    private void addPoints(Player player, Double incrementValue, Tools toolType) {
        if(toolType.equals(Tools.AXE)) {
            PointsCore.playerPoints.axePoints.addPointsToPlayer(player, incrementValue);
        } else if (toolType.equals(Tools.SHOVEL)) {
            Message.debugMessage("Added points to shovel value=" + incrementValue, DebugIntensity.LIGHT);
            PointsCore.playerPoints.shovelPoints.addPointsToPlayer(player, incrementValue);
        } else if (toolType.equals(Tools.PICKAXE)) {
            PointsCore.playerPoints.pickaxePoints.addPointsToPlayer(player, incrementValue);
        } else if(toolType.equals(Tools.FISH_ROD)) {
            PointsCore.playerPoints.fishingPoints.addPointsToPlayer(player,incrementValue);
        } else if(toolType.equals(Tools.HOE)) {
            PointsCore.playerPoints.hoePoints.addPointsToPlayer(player,incrementValue);
        }
    }

    /**
     * The event to check if the player has broken a block
     * Upon calling this event adds the respective amount of points to the player
     * @param event PlayerInteractEvent is triggered when a player breaks a bock
     */
    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Material tool = event.getPlayer().getInventory().getItemInMainHand().getType(),
            blockBroken = event.getBlock().getType();
        Player player = event.getPlayer();
        Tools toolType = valueUtil.getToolType(tool);

        if(toolType.equals(Tools.NONE)) {
            //Debug
            Message.debugMessage("Block Break Event was not triggered due to ToolType = null? " + toolType, DebugIntensity.INTENSE);
            return;
        }

        //Debug
        Message.debugMessage("BlockBreak enabled with: tool" +tool.name()+" \nBlock Broken: " +blockBroken, DebugIntensity.INTENSE );

        double incrementValue = valueUtil.getMaterialValue(toolType, blockBroken);

        addPoints(player,incrementValue,toolType);

    }

    /**
     * The event to check if the player has hoed a block
     * Upon calling this event adds the respective amount of points to the player
     * @param event PlayerInteractEvent is triggered when a player interacts with a bock
     */
    @EventHandler
    public void blockHoe(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }
        Material tool = player.getInventory().getItemInMainHand().getType(),
        blockHoed = event.getClickedBlock().getType();
        Tools toolType = valueUtil.getToolType(tool);
        if(toolType.equals(Tools.HOE)) {//if it's a hoe

            double incrementValue = valueUtil.getMaterialValue(toolType, blockHoed);
            addPoints(player,incrementValue,Tools.HOE);
        }
    }


    /**
     * The event to check if the player fished a fish
     * Upon calling this event adds the respective amount of points to the player
     * @param event PlayerFishEvent is triggered when a player fishes a fish
     */
    @EventHandler
    public void fishFished(PlayerFishEvent event) {
        Player player = event.getPlayer();
        Material material;
        switch (event.getCaught().getType()) {
            case SALMON:
                material = Material.SALMON;
                break;
            case COD:
                material = Material.COD;
                break;
            case TROPICAL_FISH:
                material = Material.TROPICAL_FISH;
                break;
            case PUFFERFISH:
                material = Material.PUFFERFISH;
                break;
            default:
                material = Material.AIR;
                break;
        }
        Material tool = player.getInventory().getItemInMainHand().getType();
        double incrementValue = valueUtil.getMaterialValue(valueUtil.getToolType(tool), material);
        addPoints(player,incrementValue,Tools.FISH_ROD);
    }

}

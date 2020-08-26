package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerFishingEvent;
import me.marcangeloh.API.Events.PlayerHoeBlockEvent;
import me.marcangeloh.PointsCore;
import me.marcangeloh.Util.ConfigurationUtil.ValueUtil;
import me.marcangeloh.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.Util.GeneralUtil.Message;
import me.marcangeloh.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ToolEvents implements Listener {
    private ValueUtil valueUtil = new ValueUtil();


    /**
     * Adds the points to the proper tool
     * @param player Player to add points to
     * @param incrementValue The amount of points to add
     * @param toolType the tool used
     */
    private void addPoints(Player player, Double incrementValue, Tools toolType) {
        if(toolType.equals(Tools.AXE)) {
            Message.debugMessage("Added points to axe value=" + incrementValue, DebugIntensity.INTENSE);
            PointsCore.playerPoints.axePoints.addPointsToPlayer(player, incrementValue);
        } else if (toolType.equals(Tools.SHOVEL)) {
            Message.debugMessage("Added points to shovel value=" + incrementValue, DebugIntensity.INTENSE);
            PointsCore.playerPoints.shovelPoints.addPointsToPlayer(player, incrementValue);
        } else if (toolType.equals(Tools.PICKAXE)) {
            Message.debugMessage("Added points to pickaxe value=" + incrementValue, DebugIntensity.INTENSE);
            PointsCore.playerPoints.pickaxePoints.addPointsToPlayer(player, incrementValue);
        } else if(toolType.equals(Tools.FISH_ROD)) {
            Message.debugMessage("Added points to fishing value=" + incrementValue, DebugIntensity.INTENSE);
            PointsCore.playerPoints.fishingPoints.addPointsToPlayer(player,incrementValue);
        } else if(toolType.equals(Tools.HOE)) {
            Message.debugMessage("Added points to hoe value=" + incrementValue, DebugIntensity.INTENSE);
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
        Message.debugMessage("BlockBreak enabled with tool : " +tool.name()+" \nBlock Broken: " +blockBroken, DebugIntensity.INTENSE );

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

        //If the item used is = to null
        if(event.getItem() == null) {
            return;
        }

        //if the item in hand is air
        if(event.getItem().getType().equals(Material.AIR)) {
            return;
        }

        //If it's not a right click
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Message.debugMessage("The action was not a right click!", DebugIntensity.INTENSE);
            return;
        }


        Material tool = event.getItem().getType(),
            blockHoed = event.getClickedBlock().getType();
        Tools toolType = valueUtil.getToolType(tool);

        if(!toolType.equals(Tools.HOE)) {//if it's not a hoe
            return;
        }

        //Executes the Player Hoe Block Event
        PlayerHoeBlockEvent playerHoeBlockEvent = new PlayerHoeBlockEvent(player, blockHoed, toolType);
        Bukkit.getPluginManager().callEvent(playerHoeBlockEvent);

        if(playerHoeBlockEvent.isCancelled()) {
            //If the event was cancelled stop adding points to the player
            return;
        }

        Message.debugMessage("The HoeEvent was successfully run, tool type: " + tool.getKey().getKey() +
                "\nBlock Hoed: " + blockHoed.getKey().getKey(), DebugIntensity.INTENSE);

        double incrementValue = valueUtil.getMaterialValue(toolType, blockHoed);
        Message.debugMessage("The tool was a hoe and the increment value is: "+ incrementValue, DebugIntensity.INTENSE);
        addPoints(player,incrementValue,Tools.HOE);

    }


    /**
     * The event to check if the player fished a fish
     * Upon calling this event adds the respective amount of points to the player
     * @param event PlayerFishingEvent is triggered when a player goes to fish a fish
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
        Tools tool = valueUtil.getToolType(player.getInventory().getItemInMainHand().getType());

        //Executes the custom event (Fishing event)
        PlayerFishingEvent playerFishingEvent = new PlayerFishingEvent(player, material, tool);
        Bukkit.getPluginManager().callEvent(playerFishingEvent);

        if(playerFishingEvent.isCancelled()) {
            //And if that event is cancelled stop adding points to the player
            return;
        }

        double incrementValue = valueUtil.getMaterialValue(tool, material);
        addPoints(player,incrementValue,Tools.FISH_ROD);
    }

}

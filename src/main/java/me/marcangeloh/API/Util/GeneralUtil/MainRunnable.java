package me.marcangeloh.API.Util.GeneralUtil;

import me.marcangeloh.API.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.API.Util.SQLUtil.SQLManager;
import me.marcangeloh.API.Util.TeleportUtil.HashMapUtil;
import me.marcangeloh.API.Util.TeleportUtil.TPARequest;
import me.marcangeloh.API.Util.TeleportUtil.TeleportUtil;
import me.marcangeloh.Commands.TeleportCommands.RandomTP;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class MainRunnable {
    private boolean isMySQLEnabled;
    private HashMapUtil hashMapUtil;
    private HashMap<Player, TeleportUtil> noMoveTimeHome;
    private HashMap<Player, TeleportUtil> noMoveTimeSpawn;
    private HashMap<Player, Integer> chatPointsCooldown;
    private int newPlayerCooldown;
    private SQLManager sqlManager;
    private DataManager dataManager;
    private PointsCore pointsCore;

    public MainRunnable(PointsCore pointsCore, boolean isMySQLEnabled, HashMapUtil hashMapUtil, SQLManager sqlManager, DataManager dataManager, HashMap<Player, TeleportUtil> noMoveTimeHome,HashMap<Player, TeleportUtil> noMoveTimeSpawn, HashMap<Player, Integer> chatPointsCooldown, int newPlayerCooldown) {
        this.pointsCore = pointsCore;
        this.isMySQLEnabled = isMySQLEnabled;
        this.hashMapUtil = hashMapUtil;
        this.sqlManager = sqlManager;
        this.dataManager = dataManager;
        this.noMoveTimeHome = noMoveTimeHome;
        this.chatPointsCooldown = chatPointsCooldown;
        this.noMoveTimeSpawn = noMoveTimeSpawn;
        this.newPlayerCooldown = newPlayerCooldown;
    }

    private void registerCooldowns() {
        new BukkitRunnable(

        ) {
            int counter = 0;
            @Override
            public void run() {
                handleRTPCooldowns();
                handleTPACooldowns();
                handleHomeCooldowns();
                handleSpawnCooldowns();
                handleChatCooldowns();
                handleNewPlayerCooldowns();
                if(counter %60 == 0) {
                    saveDataInCaseOfCrash();
                }

            }

            private void handleNewPlayerCooldowns() {
                if(newPlayerCooldown <= 0)
                    return;

                newPlayerCooldown -= 1;
            }

            private void handleHomeCooldowns() {
                for (Player player: noMoveTimeHome.keySet()) {
                    if(noMoveTimeHome.get(player).countdownToTp == 0) {
                        player.teleport(noMoveTimeHome.get(player).locationToTp);
                        noMoveTimeHome.remove(player);
                        Message.notifyMessage("You have been teleported home.", player);
                        continue;
                    }

                    noMoveTimeHome.get(player).countdownToTp = noMoveTimeHome.get(player).countdownToTp-1;
                    player.sendTitle(Message.formatLinGradient("Teleporting Home", new Color(3,255,0), new Color(159,159,159)),
                            Message.format(pointsCore, player,noMoveTimeHome.get(player).countdownToTp + " &7Seconds"), 20, 20, 20);
                }
            }

            private void handleSpawnCooldowns() {
                for (Player player: noMoveTimeSpawn.keySet()) {
                    if(noMoveTimeSpawn.get(player).countdownToTp == 0) {
                        player.teleport(pointsCore.getConfig().getLocation("Spawn.Location"));
                        noMoveTimeSpawn.remove(player);
                        Message.notifyMessage("You have been teleported to spawn.", player);
                        continue;
                    }

                    noMoveTimeSpawn.get(player).countdownToTp = noMoveTimeSpawn.get(player).countdownToTp-1;
                    player.sendTitle(Message.formatLinGradient("Teleporting to Spawn", new Color(3,255,0), new Color(159,159,159)),
                            Message.format(pointsCore, player,noMoveTimeHome.get(player).countdownToTp + " &7Seconds"), 20, 20, 20);
                }
            }

            private void handleTPACooldowns() {
                for(Player player: hashMapUtil.teleportMap.keySet()) {
                    TPARequest tpRequest = hashMapUtil.teleportMap.get(player);
                    if(tpRequest == null)
                        continue;

                    if(tpRequest.cooldown == 0 && !tpRequest.isConfirmed) {
                        hashMapUtil.teleportMap.remove(player);
                        continue;
                    }
                    if(tpRequest.cooldown > 0 && !tpRequest.isConfirmed) {
                        hashMapUtil.teleportMap.get(player).cooldown = tpRequest.cooldown - 1;
                        continue;
                    }

                    if(tpRequest.moveCooldown > 0 && tpRequest.isConfirmed) {
                        hashMapUtil.teleportMap.get(player).moveCooldown = tpRequest.moveCooldown -1;
                        if(tpRequest.isInverted) {
                            player.sendTitle(Message.format(pointsCore,player, "&#2dfb0bT&#3cf31ee&#4beb31l&#5ae343e&#69db56p&#78d469o&#87cc7cr&#96c48et &#a5bca1i&#b4b4b4n " + tpRequest.moveCooldown),
                                    Message.format(pointsCore,player,"&7Seconds"), 20, 20, 20);
                        } else {
                            tpRequest.player.sendTitle(Message.format(pointsCore,player,"&#2dfb0bT&#3cf31ee&#4beb31l&#5ae343e&#69db56p&#78d469o&#87cc7cr&#96c48et &#a5bca1i&#b4b4b4n " + tpRequest.moveCooldown),
                                    Message.format(pointsCore,player,"&7Seconds"), 20, 20, 20);
                        }
                        continue;
                    }

                    if(tpRequest.moveCooldown == 0 && tpRequest.isConfirmed) {
                        if(tpRequest.isInverted) {
                            tpRequest.player2.teleport(tpRequest.player.getLocation());
                            Message.notifyMessage("You have successfully been teleported to " + tpRequest.player.getDisplayName()+".", tpRequest.player2);
                            Message.notifyMessage(tpRequest.player2.getDisplayName()+" has successfully teleported to you.", tpRequest.player);
                            hashMapUtil.teleportMap.remove(player);
                        } else {
                            tpRequest.player.teleport(tpRequest.player2.getLocation());
                            Message.notifyMessage("You have successfully teleported to " + tpRequest.player2.getDisplayName()+".", tpRequest.player);
                            Message.notifyMessage(tpRequest.player.getDisplayName()+" has successfully teleported to you.", tpRequest.player2);
                            hashMapUtil.teleportMap.remove(player);
                        }
                    }
                }
            }

            private void handleRTPCooldowns() {
                for(UUID player: RandomTP.cooldown.keySet()) {
                    if(RandomTP.cooldown.get(player) == 0) {
                        RandomTP.cooldown.remove(player);
                        continue;
                    }

                    RandomTP.cooldown.replace(player, RandomTP.cooldown.get(player) - 1);
                }
            }

            private void handleChatCooldowns() {
                for(Player player: chatPointsCooldown.keySet()) {
                    if(chatPointsCooldown.get(player) == 0) {
                        chatPointsCooldown.remove(player);
                        continue;
                    }

                    chatPointsCooldown.replace(player, chatPointsCooldown.get(player) - 1);
                }
            }

            private void saveDataInCaseOfCrash() {
                if(isMySQLEnabled) {
                    sqlManager.saveData();
                } else{
                    dataManager.saveAll();
                }
            }
        }.runTaskTimer(pointsCore, 0, 20 );
    }

    public void run() {
        registerCooldowns();
    }
}

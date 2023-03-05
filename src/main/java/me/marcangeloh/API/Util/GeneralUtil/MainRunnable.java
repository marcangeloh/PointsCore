package me.marcangeloh.API.Util.GeneralUtil;

import me.marcangeloh.API.Util.ConfigurationUtil.DataManager;
import me.marcangeloh.API.Util.SQLUtil.SQLManager;
import me.marcangeloh.Commands.TeleportCommands.RandomTP;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class MainRunnable {
    private boolean isMySQLEnabled;
    private HashMapUtil hashMapUtil;
    private SQLManager sqlManager;
    private DataManager dataManager;

    public MainRunnable(boolean isMySQLEnabled, HashMapUtil hashMapUtil, SQLManager sqlManager, DataManager dataManager) {
        this.isMySQLEnabled = isMySQLEnabled;
        this.hashMapUtil = hashMapUtil;
        this.sqlManager = sqlManager;
        this.dataManager = dataManager;
    }

    private void registerCooldowns() {
        new BukkitRunnable(

        ) {
            int counter = 0;
            @Override
            public void run() {
                handleRTPCooldowns();
                handleTPACooldowns();
                if(counter %60 == 0) {
                    saveDataInCaseOfCrash();
                }

            }

            private void handleTPACooldowns() {
                for(Player player: hashMapUtil.teleportMap.keySet()) {
                    TeleportRequest tpRequest = hashMapUtil.teleportMap.get(player);
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
                            player.sendTitle(Message.format(player, "&#2dfb0bT&#3cf31ee&#4beb31l&#5ae343e&#69db56p&#78d469o&#87cc7cr&#96c48et &#a5bca1i&#b4b4b4n " + tpRequest.moveCooldown),
                                    Message.format(player,"&7Seconds"), 20, 20, 20);
                        } else {
                            tpRequest.player.sendTitle(Message.format(player,"&#2dfb0bT&#3cf31ee&#4beb31l&#5ae343e&#69db56p&#78d469o&#87cc7cr&#96c48et &#a5bca1i&#b4b4b4n " + tpRequest.moveCooldown),
                                    Message.format(player,"&7Seconds"), 20, 20, 20);
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

            private void saveDataInCaseOfCrash() {
                if(isMySQLEnabled) {
                    sqlManager.saveData();
                } else{
                    dataManager.saveAll();
                }
            }
        }.runTaskTimer(PointsCore.plugin, 0, 20 );
    }

    public void run() {
        registerCooldowns();
    }
}

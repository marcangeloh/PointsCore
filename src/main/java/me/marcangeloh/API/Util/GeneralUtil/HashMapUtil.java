package me.marcangeloh.API.Util.GeneralUtil;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class HashMapUtil {
    public final HashMap<Player, TPARequest> teleportMap;
    public final HashMap<Player, Player> lastMessaged;
    public HashMapUtil(){
        teleportMap = new HashMap<>();
        lastMessaged = new HashMap<>();
    }
}

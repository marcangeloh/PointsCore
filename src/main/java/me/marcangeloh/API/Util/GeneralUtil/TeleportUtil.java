package me.marcangeloh.API.Util.GeneralUtil;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportUtil {
    public final HashMap<Player, TeleportRequest> teleportMap;
    public TeleportUtil(){
        teleportMap = new HashMap<>();
    }
}

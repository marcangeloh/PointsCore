package me.marcangeloh.API.Util.TeleportUtil;

import org.bukkit.Location;

public class TeleportUtil {
    public Integer countdownToTp;
    public Location locationToTp;
    public Double distance;

    public TeleportUtil(Integer countdownToTp, Location locationToTp) {
        this.countdownToTp = countdownToTp;
        this.locationToTp = locationToTp;
        this.distance = 0.0;
    }
}

package me.marcangeloh.API.Util.GeneralUtil;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil {

    public static void spawnRedstoneParticle(Location location, int amount, int r, int g, int b, float size) {
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(r, g, b), size);
        location.getWorld().spawnParticle(Particle.REDSTONE, location, amount, dustOptions);
    }



}
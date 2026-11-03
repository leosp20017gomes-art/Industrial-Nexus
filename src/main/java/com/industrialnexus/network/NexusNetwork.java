package com.industrialnexus.network;

import com.industrialnexus.tile.TileNexusController;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/** Registo de todos os Nexus Controllers carregados (so existem os de chunks carregados). */
public class NexusNetwork {
    private static final Set<TileNexusController> CONTROLLERS =
            Collections.newSetFromMap(new ConcurrentHashMap<TileNexusController, Boolean>());

    public static void register(TileNexusController t) {
        CONTROLLERS.add(t);
    }

    public static void unregister(TileNexusController t) {
        CONTROLLERS.remove(t);
    }

    public static List<TileNexusController> inRange(EntityPlayer player, double range) {
        List<TileNexusController> result = new ArrayList<TileNexusController>();
        double r2 = range * range;
        for (TileNexusController c : CONTROLLERS) {
            if (c.isInvalid() || c.getWorld() != player.world) continue;
            if (c.getDistanceSq(player.posX, player.posY, player.posZ) <= r2) result.add(c);
        }
        return result;
    }
}

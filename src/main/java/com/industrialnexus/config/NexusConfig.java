package com.industrialnexus.config;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class NexusConfig {
    public static int chargerRange = 64;
    public static int chargerRatePerSecond = 20000;
    public static int controllerCapacity = 10000000;
    public static int controllerMaxTransfer = 100000;

    public static void load(File file) {
        Configuration cfg = new Configuration(file);
        try {
            cfg.load();
            chargerRange = cfg.getInt("chargerRange", "wireless", 64, 1, 1024,
                    "Raio (em blocos) em que o Wireless Charger encontra um Nexus Controller.");
            chargerRatePerSecond = cfg.getInt("chargerRatePerSecond", "wireless", 20000, 1, 10000000,
                    "Maximo de FE por segundo entregue a cada item do inventario.");
            controllerCapacity = cfg.getInt("controllerCapacity", "controller", 10000000, 1000, Integer.MAX_VALUE,
                    "Capacidade de energia do Nexus Controller (FE).");
            controllerMaxTransfer = cfg.getInt("controllerMaxTransfer", "controller", 100000, 1, 100000000,
                    "Maximo de FE por tick que o Nexus Controller aceita receber.");
        } finally {
            if (cfg.hasChanged()) cfg.save();
        }
    }
}

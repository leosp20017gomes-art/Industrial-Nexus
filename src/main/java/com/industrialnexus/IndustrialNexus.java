package com.industrialnexus;

import com.industrialnexus.config.NexusConfig;
import com.industrialnexus.integration.IntegrationManager;
import com.industrialnexus.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * Classe principal. As dependencias sao SOFT ("after:"): o mod carrega com ou sem
 * Immersive Engineering, HBM's NTM e TerraFirmaCraft.
 */
@Mod(modid = IndustrialNexus.MODID,
        name = IndustrialNexus.NAME,
        version = IndustrialNexus.VERSION,
        acceptedMinecraftVersions = "[1.12.2]",
        dependencies = "after:immersiveengineering;after:hbm;after:tfc")
public class IndustrialNexus {
    public static final String MODID = "industrialnexus";
    public static final String NAME = "Industrial Nexus";
    public static final String VERSION = "0.1.0";

    public static Logger logger;

    @SidedProxy(clientSide = "com.industrialnexus.proxy.ClientProxy",
            serverSide = "com.industrialnexus.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        NexusConfig.load(event.getSuggestedConfigurationFile());
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        IntegrationManager.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}

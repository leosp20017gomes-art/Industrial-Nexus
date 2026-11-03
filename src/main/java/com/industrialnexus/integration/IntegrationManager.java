package com.industrialnexus.integration;

import com.industrialnexus.IndustrialNexus;
import net.minecraftforge.fml.common.Loader;

/**
 * Regra de ouro anti-ClassNotFoundException:
 * 1) So se chama uma classe de integracao DENTRO de um "if (Loader.isModLoaded(...))".
 * 2) As classes de integracao sao as unicas que podem importar classes do outro mod.
 * 3) Nenhuma outra classe do mod referencia classes do HBM/TFC/IE diretamente.
 */
public class IntegrationManager {
    public static boolean immersiveEngineering;
    public static boolean hbm;
    public static boolean tfc;

    public static void init() {
        immersiveEngineering = Loader.isModLoaded("immersiveengineering");
        hbm = Loader.isModLoaded("hbm");
        tfc = Loader.isModLoaded("tfc");

        IndustrialNexus.logger.info("Industrial Nexus - IE: {}, HBM: {}, TFC: {}", immersiveEngineering, hbm, tfc);

        if (hbm) HBMIntegration.init();
        if (tfc) TFCIntegration.init();
    }
}

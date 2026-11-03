package com.industrialnexus.init;

import com.industrialnexus.IndustrialNexus;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = IndustrialNexus.MODID, value = Side.CLIENT)
public class ClientModels {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        register(ModItems.WIRELESS_CHARGER);
        register(Item.getItemFromBlock(ModBlocks.NEXUS_CONTROLLER));
    }

    private static void register(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}

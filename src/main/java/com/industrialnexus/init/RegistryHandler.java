package com.industrialnexus.init;

import com.industrialnexus.IndustrialNexus;
import com.industrialnexus.tile.TileNexusController;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = IndustrialNexus.MODID)
public class RegistryHandler {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ModBlocks.NEXUS_CONTROLLER);
        GameRegistry.registerTileEntity(TileNexusController.class,
                new ResourceLocation(IndustrialNexus.MODID, "nexus_controller"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ModItems.WIRELESS_CHARGER);
        event.getRegistry().register(
                new ItemBlock(ModBlocks.NEXUS_CONTROLLER).setRegistryName(ModBlocks.NEXUS_CONTROLLER.getRegistryName()));
    }
}

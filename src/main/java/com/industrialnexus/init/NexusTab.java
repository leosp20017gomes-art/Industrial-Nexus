package com.industrialnexus.init;

import com.industrialnexus.IndustrialNexus;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class NexusTab extends CreativeTabs {
    public static final NexusTab INSTANCE = new NexusTab();

    private NexusTab() {
        super(IndustrialNexus.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.WIRELESS_CHARGER);
    }
}

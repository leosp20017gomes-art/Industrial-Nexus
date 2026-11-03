package com.industrialnexus.item;

import com.industrialnexus.IndustrialNexus;
import com.industrialnexus.config.NexusConfig;
import com.industrialnexus.init.NexusTab;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemWirelessCharger extends Item {

    public ItemWirelessCharger() {
        setRegistryName(IndustrialNexus.MODID, "wireless_charger");
        setUnlocalizedName(IndustrialNexus.MODID + ".wireless_charger");
        setCreativeTab(NexusTab.INSTANCE);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(I18n.format("item.industrialnexus.wireless_charger.tooltip", NexusConfig.chargerRange));
    }
}

package com.industrialnexus.event;

import com.industrialnexus.IndustrialNexus;
import com.industrialnexus.config.NexusConfig;
import com.industrialnexus.item.ItemWirelessCharger;
import com.industrialnexus.network.NexusNetwork;
import com.industrialnexus.tile.TileNexusController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

/** Se o jogador tem um Wireless Charger no inventario, recarrega itens FE a partir dos Nexus Controllers proximos. */
@Mod.EventBusSubscriber(modid = IndustrialNexus.MODID)
public class ChargerHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side != Side.SERVER) return;
        EntityPlayer player = event.player;
        if (player.world.getTotalWorldTime() % 20 != 0) return; // 1x por segundo
        if (!hasCharger(player)) return;

        List<TileNexusController> controllers = NexusNetwork.inRange(player, NexusConfig.chargerRange);
        if (controllers.isEmpty()) return;

        chargeAll(player.inventory.mainInventory, controllers);
        chargeAll(player.inventory.armorInventory, controllers);
        chargeAll(player.inventory.offHandInventory, controllers);
    }

    private static boolean hasCharger(EntityPlayer player) {
        for (ItemStack s : player.inventory.mainInventory) if (s.getItem() instanceof ItemWirelessCharger) return true;
        for (ItemStack s : player.inventory.offHandInventory) if (s.getItem() instanceof ItemWirelessCharger) return true;
        return false;
    }

    private static void chargeAll(NonNullList<ItemStack> stacks, List<TileNexusController> controllers) {
        for (ItemStack stack : stacks) {
            if (stack.isEmpty()) continue;
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage == null || !storage.canReceive()) continue;

            int wanted = storage.receiveEnergy(NexusConfig.chargerRatePerSecond, true);
            if (wanted <= 0) continue;

            int pulled = pull(controllers, wanted);
            if (pulled <= 0) return; // controladores sem energia
            int accepted = storage.receiveEnergy(pulled, false);
            if (accepted < pulled) refund(controllers, pulled - accepted);
        }
    }

    private static int pull(List<TileNexusController> controllers, int amount) {
        int got = 0;
        for (TileNexusController c : controllers) {
            if (got >= amount) break;
            got += c.getEnergy().drain(amount - got);
        }
        return got;
    }

    private static void refund(List<TileNexusController> controllers, int amount) {
        for (TileNexusController c : controllers) {
            if (amount <= 0) break;
            amount -= c.getEnergy().fill(amount);
        }
    }
}

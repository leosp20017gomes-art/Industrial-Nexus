package com.industrialnexus.tile;

import com.industrialnexus.config.NexusConfig;
import com.industrialnexus.network.NexusNetwork;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public class TileNexusController extends TileEntity {
    private final NexusEnergyStorage energy =
            new NexusEnergyStorage(NexusConfig.controllerCapacity, NexusConfig.controllerMaxTransfer, this::markDirty);

    public NexusEnergyStorage getEnergy() {
        return energy;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (world != null && !world.isRemote) NexusNetwork.register(this);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        NexusNetwork.unregister(this);
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        NexusNetwork.unregister(this);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return CapabilityEnergy.ENERGY.cast(energy);
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energy.setEnergy(tag.getInteger("Energy"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("Energy", energy.getEnergyStored());
        return tag;
    }
}

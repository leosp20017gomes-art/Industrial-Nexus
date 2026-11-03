package com.industrialnexus.tile;

import net.minecraftforge.energy.EnergyStorage;

/** Armazenamento FE: aceita energia de fora; so o carregador sem fios a retira (drain). */
public class NexusEnergyStorage extends EnergyStorage {
    private final Runnable onChange;

    public NexusEnergyStorage(int capacity, int maxReceive, Runnable onChange) {
        super(capacity, maxReceive, 0);
        this.onChange = onChange;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int r = super.receiveEnergy(maxReceive, simulate);
        if (r > 0 && !simulate) onChange.run();
        return r;
    }

    public int drain(int amount) {
        int e = Math.min(energy, amount);
        if (e > 0) {
            energy -= e;
            onChange.run();
        }
        return e;
    }

    public int fill(int amount) {
        int e = Math.min(capacity - energy, amount);
        if (e > 0) {
            energy += e;
            onChange.run();
        }
        return e;
    }

    public void setEnergy(int value) {
        energy = Math.max(0, Math.min(capacity, value));
        onChange.run();
    }
}

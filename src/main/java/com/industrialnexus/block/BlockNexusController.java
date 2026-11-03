package com.industrialnexus.block;

import com.industrialnexus.IndustrialNexus;
import com.industrialnexus.init.NexusTab;
import com.industrialnexus.tile.NexusEnergyStorage;
import com.industrialnexus.tile.TileNexusController;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockNexusController extends Block {

    public BlockNexusController() {
        super(Material.IRON);
        setRegistryName(IndustrialNexus.MODID, "nexus_controller");
        setUnlocalizedName(IndustrialNexus.MODID + ".nexus_controller");
        setCreativeTab(NexusTab.INSTANCE);
        setHardness(5.0F);
        setResistance(30.0F);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileNexusController();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileNexusController) {
                NexusEnergyStorage e = ((TileNexusController) te).getEnergy();
                // Teste rapido: em criativo, agachado e de mao vazia, enche o controlador.
                if (player.isCreative() && player.isSneaking() && player.getHeldItem(hand).isEmpty()) {
                    e.setEnergy(e.getMaxEnergyStored());
                }
                player.sendStatusMessage(new TextComponentString(
                        "Nexus Controller: " + e.getEnergyStored() + " / " + e.getMaxEnergyStored() + " FE"), true);
            }
        }
        return true;
    }
}

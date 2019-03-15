package com.sunekaer.mods.marblecraftingtable.block;

import com.sunekaer.mods.marblecraftingtable.MarbleCraftingTable;
import com.sunekaer.mods.marblecraftingtable.tile.TileMarbleCT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMarbleCT extends Block {

    public BlockMarbleCT() {
        super(Material.ROCK);
        setHardness(2F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote)
        playerIn.openGui(MarbleCraftingTable.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
        return true;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (!world.isRemote)
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) world.getTileEntity(pos));
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileMarbleCT();
    }
}
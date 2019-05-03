package com.sunekaer.mods.marblecraftingtable.container;

import com.sunekaer.mods.marblecraftingtable.tile.TileMarbleCT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryCT extends InventoryCrafting {

    private final Container container;
    private final TileMarbleCT tileEntity;

    public InventoryCT(Container eventHandlerIn, TileMarbleCT tileEntity) {
        super(eventHandlerIn, 3, 3);
        this.tileEntity = tileEntity;
        container = eventHandlerIn;
    }

    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return i < 0 || i >= getSizeInventory() ? ItemStack.EMPTY : tileEntity.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int size) {
        ItemStack is = tileEntity.decrStackSize(i, size);
        if (is != ItemStack.EMPTY)
            tileEntity.updateInvs();
        return is;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        tileEntity.setInventorySlotContents(index, stack);
        tileEntity.updateInvs();
    }

    @Override
    public void markDirty() {
        tileEntity.markDirty();
        IBlockState state = tileEntity.getWorld().getBlockState(tileEntity.getPos());
        tileEntity.getWorld().notifyBlockUpdate(tileEntity.getPos(), state, state, 3);
    }

    @Override
    public void openInventory(EntityPlayer player) {
        super.openInventory(player);
        tileEntity.onOpen(this);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        super.closeInventory(player);
        tileEntity.onClose(this);
    }

    public void changed() {
        container.onCraftMatrixChanged(this);
    }
}

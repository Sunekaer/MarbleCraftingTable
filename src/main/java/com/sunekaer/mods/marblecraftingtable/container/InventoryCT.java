package com.sunekaer.mods.marblecraftingtable.container;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryCT extends InventoryCrafting {

    private final Container container;
    private final IInventory tileEntity;

    public InventoryCT(Container eventHandlerIn, IInventory tileEntity) {
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
            container.onCraftMatrixChanged(this);
        return is;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        tileEntity.setInventorySlotContents(index, stack);
        container.onCraftMatrixChanged(this);
    }

    @Override
    public void markDirty() {
        tileEntity.markDirty();
        container.onCraftMatrixChanged(this);
    }
}

package com.sunekaer.mods.marblecraftingtable.container;

import com.sunekaer.mods.marblecraftingtable.tile.TileMarbleCT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerCT extends Container {

    private TileMarbleCT te;
    private InventoryCT crafter;
    private InventoryCraftResult craftResult = new InventoryCraftResult();
    private EntityPlayer player;
    private World world;

    public ContainerCT(TileMarbleCT tile, EntityPlayer player) {
        this.te = te;
        this.world = world;
        this.player = player;

        crafter = new InventoryCT(this, tile);

        addSlotToContainer(new SlotCrafting(player, crafter, craftResult, 0, 124, 35));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                addSlotToContainer(new Slot(crafter, j + i * 3, 30 + j * 18, 17 + i * 18));

        for (int k = 0; k < 3; ++k)
            for (int i1 = 0; i1 < 9; ++i1)
                addSlotToContainer(new Slot(player.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));

        for (int l = 0; l < 9; ++l)
            addSlotToContainer(new Slot(player.inventory, l, 8 + l * 18, 142));
        onCraftMatrixChanged(crafter);
    }
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.slotChangedCraftingGrid(world, player, crafter, craftResult);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0) {
                itemstack1.getItem().onCreated(itemstack1, te.getWorld(), playerIn);

                if (!mergeItemStack(itemstack1, 10, 46, true))
                    return ItemStack.EMPTY;

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 10 && index < 37) {
                if (!mergeItemStack(itemstack1, 37, 46, false))
                    return ItemStack.EMPTY;
            } else if (index >= 37 && index < 46) {
                if (!mergeItemStack(itemstack1, 10, 37, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(itemstack1, 10, 46, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

            if (index == 0)
                playerIn.dropItem(itemstack2, false);
        }

        return itemstack;
    }

    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != craftResult && super.canMergeSlot(stack, slotIn);
    }
}

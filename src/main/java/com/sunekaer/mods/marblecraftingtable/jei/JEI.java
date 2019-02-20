package com.sunekaer.mods.marblecraftingtable.jei;

import com.sunekaer.mods.marblecraftingtable.block.MarbleCraftingTableBlocks;
import com.sunekaer.mods.marblecraftingtable.container.ContainerCT;
import com.sunekaer.mods.marblecraftingtable.container.GuiCT;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

public class JEI implements IModPlugin {
    @Override
    public void register(IModRegistry registry) {
        registry.addRecipeClickArea(GuiCT.class, 88, 32, 28, 23, VanillaRecipeCategoryUid.CRAFTING);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerCT.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        registry.addRecipeCatalyst(new ItemStack(MarbleCraftingTableBlocks.MARBLE_CRAFTING_TABLE), VanillaRecipeCategoryUid.CRAFTING);
    }
}

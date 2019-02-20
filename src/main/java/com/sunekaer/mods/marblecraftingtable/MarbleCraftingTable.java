package com.sunekaer.mods.marblecraftingtable;

import com.sunekaer.mods.marblecraftingtable.block.MarbleCraftingTableBlocks;
import com.sunekaer.mods.marblecraftingtable.item.MarbleCraftingTableItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = MarbleCraftingTable.MOD_ID,
        name = MarbleCraftingTable.MOD_NAME,
        version = MarbleCraftingTable.VERSION
)

public class MarbleCraftingTable {
    public static final String MOD_ID = "marblecraftingtable";
    public static final String MOD_NAME = "MarbleCraftingTable";
    public static final String VERSION = "0.0.0.marblecraftingtable";

    public static final CreativeTabs TAB = new CreativeTabs(MOD_ID)
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(MarbleCraftingTableItems.MARBLE_CRAFTING_TABLE);
        }
    };

    @Mod.Instance(MarbleCraftingTable.MOD_ID)
    public static MarbleCraftingTable instance;
}

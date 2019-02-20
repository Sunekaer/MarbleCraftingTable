package com.sunekaer.mods.marblecraftingtable;

import com.sunekaer.mods.marblecraftingtable.block.BlockMarbleCT;
import com.sunekaer.mods.marblecraftingtable.block.MarbleCraftingTableBlocks;
import com.sunekaer.mods.marblecraftingtable.container.GuiHandler;
import com.sunekaer.mods.marblecraftingtable.item.MarbleCraftingTableItems;
import com.sunekaer.mods.marblecraftingtable.tile.TileMarbleCT;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = MarbleCraftingTable.MOD_ID)
public class MarbleCraftingTableEventHandler {

    private static Block withName(Block block, String name)
    {
        block.setCreativeTab(MarbleCraftingTable.TAB);
        block.setRegistryName(name);
        block.setTranslationKey(MarbleCraftingTable.MOD_ID + "." + name);
        return block;
    }

    private static Item withName(Item item, String name)
    {
        item.setCreativeTab(MarbleCraftingTable.TAB);
        item.setRegistryName(name);
        item.setTranslationKey(MarbleCraftingTable.MOD_ID + "." + name);
        return item;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(withName(new BlockMarbleCT(), "marble_crafting_table"));
        GameRegistry.registerTileEntity(TileMarbleCT.class, new ResourceLocation(MarbleCraftingTable.MOD_ID, "marble_crafting_table"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemBlock(MarbleCraftingTableBlocks.MARBLE_CRAFTING_TABLE).setRegistryName("marble_crafting_table"));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(MarbleCraftingTableItems.MARBLE_CRAFTING_TABLE, 0, new ModelResourceLocation(MarbleCraftingTableItems.MARBLE_CRAFTING_TABLE.getRegistryName(), "inventory"));

    }

    @Mod.EventHandler
    public void postInit(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(MarbleCraftingTable.instance, new GuiHandler());
    }
}

package com.sunekaer.mods.marblecraftingtable.container;

import com.sunekaer.mods.marblecraftingtable.tile.TileMarbleCT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        if (tile != null && tile instanceof TileMarbleCT) {
            return new ContainerCT((TileMarbleCT) tile, player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        if (tile != null && tile instanceof TileMarbleCT) {
            return new GuiCT(new ContainerCT((TileMarbleCT) tile, player));
        }
        return null;
    }
}

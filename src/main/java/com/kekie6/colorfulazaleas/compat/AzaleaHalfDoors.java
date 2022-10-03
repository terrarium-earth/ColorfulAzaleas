package com.kekie6.colorfulazaleas.compat;

import amymialee.halfdoors.Halfdoors;
import amymialee.halfdoors.blocks.HalfDoorBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AzaleaHalfDoors {
    public static void registerHalfDoor(ResourceLocation resourceLocation) {
        Block block = new HalfDoorBlock(FabricBlockSettings.copyOf(Halfdoors.OAK_HALFDOOR));
        Registry.register(Registry.BLOCK, resourceLocation, block);
        Registry.register(Registry.ITEM, resourceLocation, new BlockItem(block, new Item.Properties().tab(Halfdoors.DOOR_GROUP)));
    }
}
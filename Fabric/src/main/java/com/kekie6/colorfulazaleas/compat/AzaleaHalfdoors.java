package com.kekie6.colorfulazaleas.compat;

import amymialee.halfdoors.Halfdoors;
import amymialee.halfdoors.blocks.HalfDoorBlock;
import com.kekie6.colorfulazaleas.ColorfulAzaleas;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AzaleaHalfdoors {
    public static void registerHalfdoors(AzaleaBlocks.WoodType woodType) {
        ResourceLocation resourceLocation = ColorfulAzaleas.id(woodType.name + "_azalea_halfdoor");
        Block block = Registry.register(Registry.BLOCK, resourceLocation, new HalfDoorBlock(FabricBlockSettings.copyOf(Halfdoors.OAK_HALFDOOR)));
        Registry.register(Registry.ITEM, resourceLocation, new BlockItem(block, new Item.Properties().tab(Halfdoors.DOOR_GROUP)));
    }
}

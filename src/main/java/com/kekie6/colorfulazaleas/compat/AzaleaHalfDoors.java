package com.kekie6.colorfulazaleas.compat;

import amymialee.halfdoors.Halfdoors;
import amymialee.halfdoors.blocks.HalfDoorBlock;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.level.block.Block;

public class AzaleaHalfDoors {
    public static Block registerHalfDoor(String name) {
        return AzaleaBlocks.registerBlock(name + "_azalea_halfdoor", new HalfDoorBlock(FabricBlockSettings.copyOf(Halfdoors.OAK_HALFDOOR)));
    }
}
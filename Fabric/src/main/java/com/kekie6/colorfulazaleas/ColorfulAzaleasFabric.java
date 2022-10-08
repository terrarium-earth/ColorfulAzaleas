package com.kekie6.colorfulazaleas;

import amymialee.halfdoors.Halfdoors;
import amymialee.halfdoors.blocks.HalfDoorBlock;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ColorfulAzaleasFabric implements ModInitializer {
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.create(ColorfulAzaleas.id("colorful_azaleas")).icon(() -> new ItemStack(AzaleaBlocks.trees[5].sapling.get().asItem())).build();

    @Override
    public void onInitialize() {
        ColorfulAzaleas.init();

        for (int i = 0; i < AzaleaBlocks.trees.length; i++) {
            AzaleaBlocks.ColorfulTree tree = AzaleaBlocks.trees[i];
            StrippableBlockRegistry.register(tree.woodType.log.get(), tree.woodType.stripped_log.get());
            StrippableBlockRegistry.register(tree.woodType.wood.get(), tree.woodType.stripped_wood.get());
            if (FabricLoader.getInstance().isModLoaded("halfdoors")) {
                ResourceLocation resourceLocation = ColorfulAzaleas.id(tree.woodType.name + "_azalea_halfdoor");
                Block block = Registry.register(Registry.BLOCK, resourceLocation, new HalfDoorBlock(FabricBlockSettings.copyOf(Halfdoors.OAK_HALFDOOR)));
                Registry.register(Registry.ITEM, resourceLocation, new BlockItem(block, new Item.Properties().tab(Halfdoors.DOOR_GROUP)));
            }
        }
    }
}
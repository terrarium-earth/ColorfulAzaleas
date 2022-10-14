package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.compat.AzaleaHalfdoors;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ColorfulAzaleasFabric implements ModInitializer {
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.create(ColorfulAzaleas.id("colorful_azaleas")).icon(() -> new ItemStack(AzaleaBlocks.trees[5].sapling.get().asItem())).build();

    @Override
    public void onInitialize() {
        ColorfulAzaleas.init();

        for (int i = 0; i < AzaleaBlocks.trees.length; i++) {
            AzaleaBlocks.ColorfulTree tree = AzaleaBlocks.trees[i];
            registerStrippableBlocks(tree.woodType);
            if (FabricLoader.getInstance().isModLoaded("halfdoors")) {
                AzaleaHalfdoors.registerHalfdoors(tree.woodType);
            }
        }
    }

    private static void registerStrippableBlocks(AzaleaBlocks.WoodType woodType) {
        StrippableBlockRegistry.register(woodType.log.get(), woodType.stripped_log.get());
        StrippableBlockRegistry.register(woodType.wood.get(), woodType.stripped_wood.get());
    }
}
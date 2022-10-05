package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ColorfulAzaleasFabric implements ModInitializer {
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.create(ColorfulAzaleas.id("colorful_azaleas")).icon(() -> new ItemStack(AzaleaBlocks.trees[5].sapling.get().asItem())).build();

    @Override
    public void onInitialize() {
        ColorfulAzaleas.init();

        for (int i = 0; i < AzaleaBlocks.AzaleaColors.values().length; i++) {
            AzaleaBlocks.ColorfulTree tree = AzaleaBlocks.trees[i];
            StrippableBlockRegistry.register(tree.woodType.log.get(), tree.woodType.stripped_log.get());
            StrippableBlockRegistry.register(tree.woodType.wood.get(), tree.woodType.stripped_wood.get());
        }
    }
}
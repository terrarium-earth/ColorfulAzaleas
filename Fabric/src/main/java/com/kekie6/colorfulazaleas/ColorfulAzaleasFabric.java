package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ColorfulAzaleasFabric implements ModInitializer {
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.create(ColorfulAzaleas.id("colorfulazaleas")).icon(() -> new ItemStack(AzaleaBlocks.trees[5].sapling.get().asItem())).build();

    @Override
    public void onInitialize() {
        ColorfulAzaleas.init();
    }
}
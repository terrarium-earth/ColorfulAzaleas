package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.mixin.TreeDecoratorTypeMixin;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import com.kekie6.colorfulazaleas.util.ColorfulTreeDecorator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ColorfulAzaleas implements ModInitializer {
    public static final String MOD_ID = "colorful-azaleas";
    public static final CreativeModeTab COLORFUL_AZALEAS = FabricItemGroupBuilder.create(ColorfulAzaleas.id("path")).icon(() -> new ItemStack(AzaleaBlocks.trees[5].sapling.asItem())).build();

    public static TreeDecoratorType<?> COLORFUL_TREE_DECORATOR = TreeDecoratorTypeMixin.callRegister("colorful_azalea_decorator", ColorfulTreeDecorator.CODEC);

    @Override
    public void onInitialize() {
        AzaleaBlocks.init();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
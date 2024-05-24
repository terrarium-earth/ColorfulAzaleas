package com.kekie6.colorfulazaleas.util;

import com.kekie6.colorfulazaleas.*;
import com.kekie6.colorfulazaleas.registry.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.fabricmc.loader.api.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;

public class ColorfulAzaleasItemGroups {

    public static final CreativeModeTab CREATIVE_MODE_TAB = FabricItemGroup.builder()
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(AzaleaBlocks.DROOPING_AZALEA_LEAVES);

                for(AzaleaBlocks.ColorfulTree tree : AzaleaBlocks.trees) {
                    output.accept(tree.sapling);
                    output.accept(tree.azaleaLeaves);
                    output.accept(tree.bloomingLeaves);
                    output.accept(tree.floweringLeaves);
                    output.accept(tree.droopingLeaves);
                }
            })
            .icon(() -> new ItemStack(AzaleaBlocks.trees[5].sapling.asItem()))
            .title(Component.translatable("itemGroup.colorfulazaleas.colorful_azaleas"))
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ColorfulAzaleas.id("colorful_azaleas"),
                CREATIVE_MODE_TAB
        );
    }

}
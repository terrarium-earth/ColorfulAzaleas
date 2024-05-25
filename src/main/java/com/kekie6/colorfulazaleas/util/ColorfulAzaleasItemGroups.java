package com.kekie6.colorfulazaleas.util;

import com.kekie6.colorfulazaleas.*;
import com.kekie6.colorfulazaleas.registry.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
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
                    
                    AzaleaBlocks.WoodSet woodSet = tree.woodSet;
                    output.accept(woodSet.log);
                    output.accept(woodSet.wood);
                    output.accept(woodSet.stripped_log);
                    output.accept(woodSet.stripped_wood);
                    output.accept(woodSet.planks);
                    output.accept(woodSet.stairs);
                    output.accept(woodSet.slab);
                    output.accept(woodSet.fence);
                    output.accept(woodSet.fence_gate);
                    output.accept(woodSet.door);
                    output.accept(woodSet.trapdoor);
                    output.accept(woodSet.pressure_plate);
                    output.accept(woodSet.button);
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
package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.platform.Services;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.minecraft.client.renderer.RenderType;

public class ColorfulAzaleasClient {
    public static void init() {
        for (int i = 0; i < AzaleaBlocks.AzaleaColors.values().length; i++) {
            AzaleaBlocks.ColorfulTree tree = AzaleaBlocks.trees[i];
            Services.PLATFORM.setRenderType(tree.sapling.get(), RenderType.cutout());
            Services.PLATFORM.setRenderType(tree.floweringLeaves.get(), RenderType.cutout());
            Services.PLATFORM.setRenderType(tree.bloomingLeaves.get(), RenderType.cutout());
            Services.PLATFORM.setRenderType(tree.azaleaLeaves.get(), RenderType.cutout());
            Services.PLATFORM.setRenderType(tree.droopingLeaves.get(), RenderType.cutout());
            AzaleaBlocks.WoodType wood = tree.woodType;
            Services.PLATFORM.setRenderType(wood.door.get(), RenderType.cutout());
            Services.PLATFORM.setRenderType(wood.trapdoor.get(), RenderType.cutout());
        }
        Services.PLATFORM.setRenderType(AzaleaBlocks.DROOPING_AZALEA_LEAVES.get(), RenderType.cutout());
    }
}

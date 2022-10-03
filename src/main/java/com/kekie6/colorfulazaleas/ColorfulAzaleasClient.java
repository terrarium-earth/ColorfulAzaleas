package com.kekie6.colorfulazaleas;

import net.fabricmc.api.ClientModInitializer;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class ColorfulAzaleasClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (int i = 0; i < AzaleaBlocks.AzaleaColors.values().length; i++) {
            AzaleaBlocks.ColorfulTree tree = AzaleaBlocks.trees[i];
            BlockRenderLayerMap.INSTANCE.putBlock(tree.sapling, RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.azaleaLeaves, RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.bloomingLeaves, RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.floweringLeaves, RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.droopingLeaves, RenderType.cutout());
            AzaleaBlocks.WoodType wood = tree.woodType;
            BlockRenderLayerMap.INSTANCE.putBlock(wood.door, RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(wood.trapdoor, RenderType.cutout());
        }
        BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.DROOPING_AZALEA_LEAVES, RenderType.cutout());
    }
}
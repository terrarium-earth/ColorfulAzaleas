package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class ColorfulAzaleasClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (int i = 0; i < AzaleaBlocks.AzaleaColors.values().length; i++) {
            AzaleaBlocks.ColorfulTree tree = AzaleaBlocks.trees[i];
            BlockRenderLayerMap.INSTANCE.putBlock(tree.sapling.get(), RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.floweringLeaves.get(), RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.bloomingLeaves.get(), RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.azaleaLeaves.get(), RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(tree.droopingLeaves.get(), RenderType.cutout());
            AzaleaBlocks.WoodType wood = tree.woodType;
            BlockRenderLayerMap.INSTANCE.putBlock(wood.door.get(), RenderType.cutout());
            BlockRenderLayerMap.INSTANCE.putBlock(wood.trapdoor.get(), RenderType.cutout());
        }
        BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.DROOPING_AZALEA_LEAVES.get(), RenderType.cutout());
    }
}
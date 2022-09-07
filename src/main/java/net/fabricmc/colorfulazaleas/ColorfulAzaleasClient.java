package net.fabricmc.colorfulazaleas;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.FallingDustParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

import java.awt.*;

public class ColorfulAzaleasClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.ORANGE_AZALEA_SAPLING, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.ORANGE_DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.WHITE_AZALEA_SAPLING, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.WHITE_DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.PURPLE_AZALEA_SAPLING, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.PURPLE_DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.RED_AZALEA_SAPLING, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.RED_DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.PINK_AZALEA_SAPLING, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.PINK_DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.YELLOW_AZALEA_SAPLING, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.YELLOW_DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.BLUE_AZALEA_SAPLING, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.BLUE_DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.DROOPING_AZALEA_LEAVES, RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(AzaleaBlocks.BRIGHT_AZALEA_DOOR, RenderType.cutout());
    }

}

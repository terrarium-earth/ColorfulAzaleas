package com.kekie6.colorfulazaleas.platform.services;

import com.kekie6.colorfulazaleas.registry.RegistryObject;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.function.Supplier;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();


    /**
     *
     * Sets the render type of a block.
     *
     * @param block The block to set the render type for.
     * @param renderType The render type to set the block to.
     */
    void setRenderType(Block block, RenderType renderType);

    /**
     *
     * Adds a block item to the azalea leaves loot table.
     *
     * @param block The block to add to the azalea leaves loot table.
     */
    void addBlockToAzaleaLootTable(Block block);

    /**
     *
     * Registers a half door, this should only be run if the halfdoors mod is present.
     * @param resourceLocation The resource location to register the half door under.
     */
    void registerHalfDoor(ResourceLocation resourceLocation);

    CreativeModeTab getCreativeTab();
}

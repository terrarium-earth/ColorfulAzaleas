package com.kekie6.colorfulazaleas.platform;

import com.google.auto.service.AutoService;
import com.kekie6.colorfulazaleas.ColorfulAzaleasForge;
import com.kekie6.colorfulazaleas.platform.services.IPlatformHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.RegistryObject;

import java.util.NoSuchElementException;

@AutoService(IPlatformHelper.class)
public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public void setRenderType(Block block, RenderType renderType) {
        // Handled through Block Model JSON.
    }

    @Override
    public void addBlockToAzaleaLootTable(Block block) {
        // Handled through Global Loot Modifiers.
    }

    @Override
    public void registerHalfDoor(ResourceLocation resourceLocation) {
        // Not used on Forge, Half Doors does not have a Forge version.
    }

    @Override
    public CreativeModeTab getCreativeTab() {
        return ColorfulAzaleasForge.CREATIVE_TAB;
    }

    @Override
    public Holder<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name, ConfiguredFeature<?, ?> value) {
        RegistryObject<ConfiguredFeature<?, ?>> registeredValue = ColorfulAzaleasForge.CONFIGURED_FEATURES.register(name, () -> value);
        return registeredValue.getHolder().orElseThrow(NoSuchElementException::new);
    }
}

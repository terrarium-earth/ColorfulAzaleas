package com.kekie6.colorfulazaleas.util;

import com.kekie6.colorfulazaleas.registry.RegistryObject;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ColorfulAzaleaTreeGrower extends AbstractTreeGrower {
    private final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> tree;

    public ColorfulAzaleaTreeGrower(RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> tree) {
        this.tree = tree;
    }

    @Override
    @Nullable
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean largeHive) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getResourceKey(tree.get()).flatMap(BuiltinRegistries.CONFIGURED_FEATURE::getHolder).orElseThrow(IllegalStateException::new);
    }
}
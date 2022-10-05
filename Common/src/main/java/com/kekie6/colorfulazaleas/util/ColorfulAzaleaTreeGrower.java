package com.kekie6.colorfulazaleas.util;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ColorfulAzaleaTreeGrower extends AbstractTreeGrower {
    private final ConfiguredFeature<?, ?> tree;

    public ColorfulAzaleaTreeGrower(ConfiguredFeature<?, ?> tree) {
        this.tree = tree;
    }

    @Override
    @Nullable
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean largeHive) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getResourceKey(tree).flatMap(BuiltinRegistries.CONFIGURED_FEATURE::getHolder).orElseThrow(IllegalStateException::new);
    }
}
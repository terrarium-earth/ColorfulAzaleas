
package net.fabricmc.colorfulazaleas;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class PinkAzaleaTreeGrower
        extends AbstractTreeGrower {
    @Override
    @Nullable
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        return BuiltinRegistries.CONFIGURED_FEATURE
                .getResourceKey(ColorfulAzaleaTreeFeatures.PINK_AZALEA_TREE).flatMap(BuiltinRegistries.CONFIGURED_FEATURE::getHolder)
                .orElseThrow(IllegalStateException::new);
    }
}


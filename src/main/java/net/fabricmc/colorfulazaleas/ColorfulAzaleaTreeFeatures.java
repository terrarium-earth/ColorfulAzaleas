package net.fabricmc.colorfulazaleas;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.List;

public class ColorfulAzaleaTreeFeatures {
    public static final ConfiguredFeature<TreeConfiguration, ?> ORANGE_AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AzaleaBlocks.TECAL_AZALEA_WOOD),
                    new FancyTrunkPlacer(8, 4, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                            .add(AzaleaBlocks.ORANGE_FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                            .build()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                    .decorators(List.of(
                            new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(AzaleaBlocks.ORANGE_BLOOMING_AZALEA_LEAVES.defaultBlockState()), 0, List.of(Direction.DOWN)),
                            new ColorfulTreeDecorator(AzaleaBlocks.ORANGE_DROOPING_AZALEA_LEAVES, AzaleaBlocks.TECAL_AZALEA_WOOD)))
                    .forceDirt()
                    .build());

    public static final ConfiguredFeature<TreeConfiguration, ?> YELLOW_AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AzaleaBlocks.FISS_AZALEA_WOOD),
                    new FancyTrunkPlacer(8, 4, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                            .add(AzaleaBlocks.YELLOW_FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                            .build()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                    .decorators(List.of(
                            new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(AzaleaBlocks.YELLOW_BLOOMING_AZALEA_LEAVES.defaultBlockState()), 0, List.of(Direction.DOWN)),
                            new ColorfulTreeDecorator(AzaleaBlocks.YELLOW_DROOPING_AZALEA_LEAVES, AzaleaBlocks.FISS_AZALEA_WOOD)))
                    .forceDirt()
                    .build());

    public static final ConfiguredFeature<TreeConfiguration, ?> RED_AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AzaleaBlocks.ROZE_AZALEA_WOOD),
                    new FancyTrunkPlacer(8, 4, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                            .add(AzaleaBlocks.RED_FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                            .build()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                    .decorators(List.of(
                            new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(AzaleaBlocks.RED_BLOOMING_AZALEA_LEAVES.defaultBlockState()), 0, List.of(Direction.DOWN)),
                            new ColorfulTreeDecorator(AzaleaBlocks.RED_DROOPING_AZALEA_LEAVES, AzaleaBlocks.ROZE_AZALEA_WOOD)))
                    .forceDirt()
                    .build());

    public static final ConfiguredFeature<TreeConfiguration, ?> BLUE_AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AzaleaBlocks.AZULE_AZALEA_WOOD),
                    new FancyTrunkPlacer(8, 4, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                            .add(AzaleaBlocks.BLUE_FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                            .build()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                    .decorators(List.of(
                            new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(AzaleaBlocks.BLUE_BLOOMING_AZALEA_LEAVES.defaultBlockState()), 0, List.of(Direction.DOWN)),
                            new ColorfulTreeDecorator(AzaleaBlocks.BLUE_DROOPING_AZALEA_LEAVES, AzaleaBlocks.AZULE_AZALEA_WOOD)))
                    .forceDirt()
                    .build());

    public static final ConfiguredFeature<TreeConfiguration, ?> PINK_AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AzaleaBlocks.BRIGHT_AZALEA_WOOD),
                    new FancyTrunkPlacer(8, 4, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                            .add(AzaleaBlocks.PINK_FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                            .build()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                    .decorators(List.of(
                            new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(AzaleaBlocks.PINK_BLOOMING_AZALEA_LEAVES.defaultBlockState()), 0, List.of(Direction.DOWN)),
                            new ColorfulTreeDecorator(AzaleaBlocks.PINK_DROOPING_AZALEA_LEAVES, AzaleaBlocks.BRIGHT_AZALEA_WOOD)))
                    .forceDirt()
                    .build());

    public static final ConfiguredFeature<TreeConfiguration, ?> PURPLE_AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AzaleaBlocks.WALNUT_AZALEA_WOOD),
                    new FancyTrunkPlacer(8, 4, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                            .add(AzaleaBlocks.PURPLE_FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                            .build()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                    .decorators(List.of(
                            new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(AzaleaBlocks.PURPLE_BLOOMING_AZALEA_LEAVES.defaultBlockState()), 0, List.of(Direction.DOWN)),
                            new ColorfulTreeDecorator(AzaleaBlocks.PURPLE_DROOPING_AZALEA_LEAVES, AzaleaBlocks.WALNUT_AZALEA_WOOD)))
                    .forceDirt()
                    .build());

    public static final ConfiguredFeature<TreeConfiguration, ?> WHITE_AZALEA_TREE = new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(AzaleaBlocks.TITANIUM_AZALEA_WOOD),
                    new FancyTrunkPlacer(8, 4, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                            .add(AzaleaBlocks.WHITE_FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                            .build()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                    .decorators(List.of(
                            new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(AzaleaBlocks.WHITE_BLOOMING_AZALEA_LEAVES.defaultBlockState()), 0, List.of(Direction.DOWN)),
                            new ColorfulTreeDecorator(AzaleaBlocks.WHITE_DROOPING_AZALEA_LEAVES, AzaleaBlocks.TITANIUM_AZALEA_WOOD)))
                    .forceDirt()
                    .build());


    public static void registerFeatures() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MODID, "orange_azalea_tree"), ORANGE_AZALEA_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MODID, "yellow_azalea_tree"), YELLOW_AZALEA_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MODID, "red_azalea_tree"), RED_AZALEA_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MODID, "blue_azalea_tree"), BLUE_AZALEA_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MODID, "pink_azalea_tree"), PINK_AZALEA_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MODID, "purple_azalea_tree"), PURPLE_AZALEA_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MODID, "white_azalea_tree"), WHITE_AZALEA_TREE);
    }
}

package com.kekie6.colorfulazaleas.registry;

import com.kekie6.colorfulazaleas.ColorfulAzaleas;
import com.kekie6.colorfulazaleas.blocks.ColorfulAzaleaBushBlock;
import com.kekie6.colorfulazaleas.blocks.DroopingLeavesBlock;
import com.kekie6.colorfulazaleas.platform.Services;
import com.kekie6.colorfulazaleas.util.ColorfulAzaleaTreeGrower;
import com.kekie6.colorfulazaleas.util.ColorfulTreeDecorator;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.material.Material;

import java.util.List;
import java.util.function.Supplier;

public class AzaleaBlocks {
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registry.BLOCK, ColorfulAzaleas.MOD_ID);
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM, ColorfulAzaleas.MOD_ID);

    public static ColorfulTree[] trees;
    public static final RegistryObject<Block> DROOPING_AZALEA_LEAVES = registerBlock("drooping_azalea_leaves", () -> new DroopingLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).noCollission().sound(SoundType.CAVE_VINES)));

    public static void init() {
        trees = new ColorfulTree[AzaleaColors.values().length];
        for (int i = 0; i < AzaleaColors.values().length; i++) {
            trees[i] = new ColorfulTree(AzaleaColors.values()[i]);
        }
    }

    public enum AzaleaColors {
        orange("tecal"),
        yellow("fiss"),
        red("roze"),
        blue("azule"),
        pink("bright"),
        purple("walnut"),
        white("titanium");

        final String title;

        AzaleaColors(String title) {
            this.title = title;
        }
    }

    public static class ColorfulTree {
        public final WoodType woodType;
        public final RegistryObject<Block> sapling;
        public final RegistryObject<Block> azaleaLeaves;
        public final RegistryObject<Block> floweringLeaves;
        public final RegistryObject<Block> bloomingLeaves;
        public final RegistryObject<Block> droopingLeaves;
        public final ConfiguredFeature<?, ?> feature;

        public ColorfulTree(AzaleaColors color) {
            String name = color.name();
            this.woodType = new WoodType(color);
            this.azaleaLeaves = registerBlock(name + "_azalea_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)));
            this.floweringLeaves = registerBlock(name + "_flowering_azalea_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)));
            this.bloomingLeaves = registerBlock(name + "_blooming_azalea_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES).requiresCorrectToolForDrops()));
            this.droopingLeaves = registerBlock(name + "_drooping_azalea_leaves", () -> new DroopingLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).noCollission().sound(SoundType.CAVE_VINES)));
            this.feature = Services.PLATFORM.registerConfiguredFeature(name + "_azalea_tree", makeAzaleaFeature(this.woodType.log.get(), this.floweringLeaves.get(), this.bloomingLeaves.get(), this.droopingLeaves.get()));
            this.sapling = registerBlock(name + "_azalea_sapling", () -> new ColorfulAzaleaBushBlock(new ColorfulAzaleaTreeGrower(feature), BlockBehaviour.Properties.copy(Blocks.AZALEA).noOcclusion()));
            Services.PLATFORM.addBlockToAzaleaLootTable(sapling.get());
        }

        private static ConfiguredFeature<TreeConfiguration, ?> makeAzaleaFeature(Block log, Block floweringLeaves, Block bloomingLeaves, Block droopingLeaves) {
            return new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new FancyTrunkPlacer(8, 4, 6), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3).add(floweringLeaves.defaultBlockState(), 1).build()), new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3), new TwoLayersFeatureSize(1, 0, 1)).dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).decorators(List.of(new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(bloomingLeaves.defaultBlockState()), 0, List.of(Direction.DOWN)), new ColorfulTreeDecorator(droopingLeaves, log))).forceDirt().build());
        }
    }

    public static class WoodType {
        public final RegistryObject<Block> log;
        public final RegistryObject<Block> wood;
        public final RegistryObject<Block> stripped_log;
        public final RegistryObject<Block> stripped_wood;
        public final RegistryObject<Block> planks;
        public final RegistryObject<Block> stair;
        public final RegistryObject<Block> slab;
        public final RegistryObject<Block> door;
        public final RegistryObject<Block> trapdoor;
        public final RegistryObject<Block> fence;
        public final RegistryObject<Block> fence_gate;
        public final RegistryObject<Block> pressure_plate;
        public final RegistryObject<Block> button;

        public WoodType(AzaleaColors color) {
            String name = color.title;
            this.log = registerBlock(name + "_azalea_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
            this.wood = registerBlock(name + "_azalea_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
            this.stripped_log = registerBlock("stripped_" + name + "_azalea_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
            this.stripped_wood = registerBlock("stripped_" + name + "_azalea_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
            this.planks = registerBlock(name + "_azalea_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
            this.stair = registerBlock(name + "_azalea_stairs", () -> new StairBlock(planks.get().defaultBlockState(), BlockBehaviour.Properties.copy(planks.get())));
            this.slab = registerBlock(name + "_azalea_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
            this.door = registerBlock(name + "_azalea_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
            if (Services.PLATFORM.isModLoaded("halfdoors")) {
                Services.PLATFORM.registerHalfDoor(ColorfulAzaleas.id(name + "_azalea_halfdoor"));
            }
            this.trapdoor = registerBlock(name + "_azalea_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
            this.fence = registerBlock(name + "_azalea_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
            this.fence_gate = registerBlock(name + "_azalea_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)));
            this.pressure_plate = registerBlock(name + "_azalea_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
            this.button = registerBlock(name + "_azalea_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
        }
    }

    public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(Services.PLATFORM.getCreativeTab())));
        return registryObject;
    }
}
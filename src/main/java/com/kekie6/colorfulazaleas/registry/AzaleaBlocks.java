package com.kekie6.colorfulazaleas.registry;

import com.kekie6.colorfulazaleas.ColorfulAzaleas;
import com.kekie6.colorfulazaleas.blocks.ColorfulAzaleaBushBlock;
import com.kekie6.colorfulazaleas.blocks.DroopingLeavesBlock;
import com.kekie6.colorfulazaleas.util.ColorfulAzaleaTreeGrower;
import com.kekie6.colorfulazaleas.util.ColorfulTreeDecorator;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
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
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;

public class AzaleaBlocks {
    public static ColorfulTree[] trees;
    public static final Block DROOPING_AZALEA_LEAVES = new DroopingLeavesBlock(FabricBlockSettings.of(Material.LEAVES).noCollision().sounds(SoundType.CAVE_VINES));

    public static void init() {
        trees = new ColorfulTree[AzaleaColors.values().length];
        for (int i = 0; i < AzaleaColors.values().length; i++) {
            trees[i] = new ColorfulTree(AzaleaColors.values()[i]);
        }
        registerBlock("drooping_azalea_leaves", DROOPING_AZALEA_LEAVES);
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
        public final Block sapling;
        public final Block azaleaLeaves;
        public final Block floweringLeaves;
        public final Block bloomingLeaves;
        public final Block droopingLeaves;
        public final ConfiguredFeature<TreeConfiguration, ?> feature;

        public ColorfulTree(AzaleaColors color) {
            String name = color.name();
            this.woodType = new WoodType(color);
            this.azaleaLeaves = registerBlock(name + "_azalea_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.AZALEA_LEAVES)));
            this.floweringLeaves = registerBlock(name + "_flowering_azalea_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.AZALEA_LEAVES)));
            this.bloomingLeaves = registerBlock(name + "_blooming_azalea_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.AZALEA_LEAVES).requiresTool()));
            this.droopingLeaves = registerBlock(name + "_drooping_azalea_leaves", new DroopingLeavesBlock(FabricBlockSettings.of(Material.LEAVES).noCollision().sounds(SoundType.CAVE_VINES)));
            this.feature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ColorfulAzaleas.MOD_ID, name + "_azalea_tree"), makeAzaleaFeature(this.woodType.log, this.floweringLeaves, this.bloomingLeaves, this.droopingLeaves));
            this.sapling = registerBlock(name + "_azalea_sapling", new ColorfulAzaleaBushBlock(new ColorfulAzaleaTreeGrower(feature), FabricBlockSettings.copyOf(Blocks.AZALEA).nonOpaque()));
            LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
                if (Blocks.AZALEA_LEAVES.getLootTable().equals(id)) {
                    LootPool.Builder poolBuilder1 = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(0.001f))
                            .add(LootItem.lootTableItem(this.sapling));
                    tableBuilder.pool(poolBuilder1.build());
                }
            }));
        }

        private static ConfiguredFeature<TreeConfiguration, ?> makeAzaleaFeature(Block log, Block floweringLeaves, Block bloomingLeaves, Block droopingLeaves) {
            return new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new FancyTrunkPlacer(8, 4, 6), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3).add(floweringLeaves.defaultBlockState(), 1).build()), new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3), new TwoLayersFeatureSize(1, 0, 1)).dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).decorators(List.of(new AttachedToLeavesDecorator(Integer.MAX_VALUE, 0, 1, BlockStateProvider.simple(bloomingLeaves.defaultBlockState()), 0, List.of(Direction.DOWN)), new ColorfulTreeDecorator(droopingLeaves, log))).forceDirt().build());
        }
    }

    public static class WoodType {
        public final Block log;
        public final Block wood;
        public final Block stripped_log;
        public final Block stripped_wood;
        public final Block planks;
        public final Block stair;
        public final Block slab;
        public final Block door;
        public final Block trapdoor;
        public final Block fence;
        public final Block fence_gate;
        public final Block pressure_plate;
        public final Block button;

        public WoodType(AzaleaColors color) {
            String name = color.title;
            this.log = registerBlock(name + "_azalea_log", new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
            this.wood = registerBlock(name + "_azalea_wood", new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
            this.stripped_log = registerBlock("stripped_" + name + "_azalea_log", new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
            this.stripped_wood = registerBlock("stripped_" + name + "_azalea_wood", new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
            this.planks = registerBlock(name + "_azalea_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
            this.stair = registerBlock(name + "_azalea_stairs", new StairBlock(planks.defaultBlockState(), FabricBlockSettings.copyOf(planks)));
            this.slab = registerBlock(name + "_azalea_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
            this.door = registerBlock(name + "_azalea_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR)));
            this.trapdoor = registerBlock(name + "_azalea_trapdoor", new TrapDoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR)));
            this.fence = registerBlock(name + "_azalea_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
            this.fence_gate = registerBlock(name + "_azalea_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE)));
            this.pressure_plate = registerBlock(name + "_azalea_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE)));
            this.button = registerBlock(name + "_azalea_button", new WoodButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON)));
            StrippableBlockRegistry.register(this.log, this.stripped_log);
            StrippableBlockRegistry.register(this.wood, this.stripped_wood);
        }
    }

    public static Block registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, ColorfulAzaleas.id(name), block);
        Registry.register(Registry.ITEM, ColorfulAzaleas.id(name), new BlockItem(block, new Item.Properties().tab(ColorfulAzaleas.COLORFUL_AZALEAS)));
        return block;
    }
}
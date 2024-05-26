package com.kekie6.colorfulazaleas.registry;

import com.kekie6.colorfulazaleas.*;
import com.kekie6.colorfulazaleas.blocks.*;
import net.fabricmc.fabric.api.loot.v2.*;
import net.fabricmc.fabric.api.object.builder.v1.block.type.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.*;

public class AzaleaBlocks {

    public static ColorfulTree[] trees;
    public static final Block DROOPING_AZALEA_LEAVES = registerBlock("drooping_azalea_leaves", new DroopingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES).noCollission().sound(SoundType.CAVE_VINES)));

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
        public final String name;
        public final WoodSet woodSet;
        public final Block sapling;
        public final Block azaleaLeaves;
        public final Block floweringLeaves;
        public final Block bloomingLeaves;
        public final Block droopingLeaves;

        public ColorfulTree(AzaleaColors color) {
            this.name = color.name();
            this.woodSet = new WoodSet(color);
            this.azaleaLeaves = registerBlock(name + "_azalea_leaves", new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)));
            this.floweringLeaves = registerBlock(name + "_flowering_azalea_leaves", new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)));
            this.bloomingLeaves = registerBlock(name + "_blooming_azalea_leaves", new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES).requiresCorrectToolForDrops()));
            this.droopingLeaves = registerBlock(name + "_drooping_azalea_leaves", new DroopingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES).noCollission().sound(SoundType.CAVE_VINES)));

            ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, ColorfulAzaleas.id(name));
            this.sapling = registerBlock(name + "_azalea_sapling", new ColorfulAzaleaBushBlock(new AbstractTreeGrower() {
                @Override
                protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
					return configuredFeatureKey;
                }
            }, BlockBehaviour.Properties.copy(Blocks.AZALEA).noOcclusion()));

            addBlockToAzaleaLootTable(sapling);
            
            CompostingChanceRegistry.INSTANCE.add(sapling, 0.65F);
        }

    }

    public static void addBlockToAzaleaLootTable(Block block) {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (Blocks.AZALEA_LEAVES.getLootTable().equals(id)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                        .add(LootItem.lootTableItem(block));
                tableBuilder.pool(poolBuilder1.build());
            }
        }));
    }

    public static class WoodSet {
        public final String name;

        public static final BlockSetType BLOCK_SET_TYPE = BlockSetTypeBuilder.copyOf(BlockSetType.ACACIA).register(ColorfulAzaleas.id("colorful_azaleas"));
        public static final WoodType WOOD_TYPE = new WoodTypeBuilder().register(ColorfulAzaleas.id("colorful_azaleas"), BLOCK_SET_TYPE);

        public final Block log;
        public final Block wood;
        public final Block stripped_log;
        public final Block stripped_wood;
        public final Block planks;
        public final Block stairs;
        public final Block slab;
        public final Block fence;
        public final Block fence_gate;
        public final Block door;
        public final Block trapdoor;
        public final Block pressure_plate;
        public final Block button;

        public WoodSet(AzaleaColors color) {
            this.name = color.title;

            this.log = registerBlock(name + "_azalea_log", new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
            this.wood = registerBlock(name + "_azalea_wood", new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
            this.stripped_log = registerBlock("stripped_" + name + "_azalea_log", new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
            this.stripped_wood = registerBlock("stripped_" + name + "_azalea_wood", new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
            this.planks = registerBlock(name + "_azalea_planks", new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
            this.stairs = registerBlock(name + "_azalea_stairs", new StairBlock(planks.defaultBlockState(), BlockBehaviour.Properties.copy(planks)));
            this.slab = registerBlock(name + "_azalea_slab", new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
            this.fence = registerBlock(name + "_azalea_fence", new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
            this.fence_gate = registerBlock(name + "_azalea_fence_gate", new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), WOOD_TYPE));
            this.door = registerBlock(name + "_azalea_door", new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BLOCK_SET_TYPE));
            this.trapdoor = registerBlock(name + "_azalea_trapdoor", new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BLOCK_SET_TYPE));
            this.pressure_plate = registerBlock(name + "_azalea_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BLOCK_SET_TYPE));
            this.button = registerBlock(name + "_azalea_button", new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BLOCK_SET_TYPE, 30, true));

            StrippableBlockRegistry.register(log, stripped_log);
            StrippableBlockRegistry.register(wood, stripped_wood);
        }
    }

    public static Block registerBlock(String name, Block block) {
        ResourceLocation resourceLocation = ColorfulAzaleas.id(name);
        Registry.register(BuiltInRegistries.BLOCK, resourceLocation, block);
        Registry.register(BuiltInRegistries.ITEM, resourceLocation, new BlockItem(block, new Item.Properties()));
        return block;
    }
}
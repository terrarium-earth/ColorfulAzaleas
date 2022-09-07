package net.fabricmc.colorfulazaleas;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.colorfulazaleas.mixin.TreeDecoratorTypeMixin;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ColorfulAzaleas implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MODID = "colorful-azaleas";

    private static final ResourceLocation AZALEA_LEAVES_ID = Blocks.AZALEA_LEAVES.getLootTable();
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(AZALEA_LEAVES_ID.equals(id)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.001f))
                        .add(LootItem.lootTableItem(AzaleaBlocks.BLUE_AZALEA_SAPLING_ITEM));
                tableBuilder.pool(poolBuilder1.build());

                LootPool.Builder poolBuilder2 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.001f))
                        .add(LootItem.lootTableItem(AzaleaBlocks.PURPLE_AZALEA_SAPLING_ITEM));
                tableBuilder.pool(poolBuilder2.build());

                LootPool.Builder poolBuilder3 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.001f))
                        .add(LootItem.lootTableItem(AzaleaBlocks.YELLOW_AZALEA_SAPLING_ITEM));
                tableBuilder.pool(poolBuilder3.build());

                LootPool.Builder poolBuilder4 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.001f))
                        .add(LootItem.lootTableItem(AzaleaBlocks.PINK_AZALEA_SAPLING_ITEM));
                tableBuilder.pool(poolBuilder4.build());

                LootPool.Builder poolBuilder5 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.001f))
                        .add(LootItem.lootTableItem(AzaleaBlocks.ORANGE_AZALEA_SAPLING_ITEM));
                tableBuilder.pool(poolBuilder5.build());

                LootPool.Builder poolBuilder6 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.001f))
                        .add(LootItem.lootTableItem(AzaleaBlocks.RED_AZALEA_SAPLING_ITEM));
                tableBuilder.pool(poolBuilder6.build());

                LootPool.Builder poolBuilder7 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.001f))
                        .add(LootItem.lootTableItem(AzaleaBlocks.WHITE_AZALEA_SAPLING_ITEM));
                tableBuilder.pool(poolBuilder7.build());
            }
        }));
    }

    public static TreeDecoratorType<?> COLORFUL_TREE_DECORATOR = TreeDecoratorTypeMixin.callRegister("colorful_azalea_decorator", ColorfulTreeDecorator.CODEC);

    @Override
    public void onInitialize() {
        AzaleaBlocks.registerBlock();
        ColorfulAzaleas.registerStrippables();
        ColorfulAzaleaTreeFeatures.registerFeatures();
        ColorfulAzaleas.modifyLootTables();
        ColorfulAzaleas.modifyLootTables();


        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
    }

    public static void registerStrippables() {
        StrippableBlockRegistry.register(AzaleaBlocks.AZULE_AZALEA_LOG, AzaleaBlocks.STRIPPED_AZULE_AZALEA_LOG);
        StrippableBlockRegistry.register(AzaleaBlocks.BRIGHT_AZALEA_LOG, AzaleaBlocks.STRIPPED_BRIGHT_AZALEA_LOG);
        StrippableBlockRegistry.register(AzaleaBlocks.TECAL_AZALEA_LOG, AzaleaBlocks.STRIPPED_TECAL_AZALEA_LOG);
        StrippableBlockRegistry.register(AzaleaBlocks.ROZE_AZALEA_LOG, AzaleaBlocks.STRIPPED_ROZE_AZALEA_LOG);
        StrippableBlockRegistry.register(AzaleaBlocks.TITANIUM_AZALEA_LOG, AzaleaBlocks.STRIPPED_TITANIUM_AZALEA_LOG);
        StrippableBlockRegistry.register(AzaleaBlocks.WALNUT_AZALEA_LOG, AzaleaBlocks.STRIPPED_WALNUT_AZALEA_LOG);
        StrippableBlockRegistry.register(AzaleaBlocks.AZULE_AZALEA_WOOD, AzaleaBlocks.STRIPPED_AZULE_AZALEA_WOOD);
        StrippableBlockRegistry.register(AzaleaBlocks.BRIGHT_AZALEA_WOOD, AzaleaBlocks.STRIPPED_BRIGHT_AZALEA_WOOD);
        StrippableBlockRegistry.register(AzaleaBlocks.TECAL_AZALEA_WOOD, AzaleaBlocks.STRIPPED_TECAL_AZALEA_WOOD);
        StrippableBlockRegistry.register(AzaleaBlocks.ROZE_AZALEA_WOOD, AzaleaBlocks.STRIPPED_ROZE_AZALEA_WOOD);
        StrippableBlockRegistry.register(AzaleaBlocks.TITANIUM_AZALEA_WOOD, AzaleaBlocks.STRIPPED_TITANIUM_AZALEA_WOOD);
        StrippableBlockRegistry.register(AzaleaBlocks.WALNUT_AZALEA_WOOD, AzaleaBlocks.STRIPPED_WALNUT_AZALEA_WOOD);
        StrippableBlockRegistry.register(AzaleaBlocks.FISS_AZALEA_WOOD, AzaleaBlocks.STRIPPED_FISS_AZALEA_WOOD);
        StrippableBlockRegistry.register(AzaleaBlocks.FISS_AZALEA_LOG, AzaleaBlocks.STRIPPED_FISS_AZALEA_LOG);
    }
}

/*
.add(LootItem.lootTableItem(AzaleaBlocks.YELLOW_AZALEA_SAPLING_ITEM.asItem()))
                        .add(LootItem.lootTableItem(AzaleaBlocks.RED_AZALEA_SAPLING_ITEM.asItem()))
                        .add(LootItem.lootTableItem(AzaleaBlocks.ORANGE_AZALEA_SAPLING_ITEM.asItem()))
                        .add(LootItem.lootTableItem(AzaleaBlocks.PINK_AZALEA_SAPLING_ITEM.asItem()))
                        .add(LootItem.lootTableItem(AzaleaBlocks.PURPLE_AZALEA_SAPLING_ITEM.asItem()))
                        .add(LootItem.lootTableItem(AzaleaBlocks.WHITE_AZALEA_SAPLING_ITEM.asItem()))
 */

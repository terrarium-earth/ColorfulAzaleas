package com.kekie6.colorfulazaleas.platform;

import com.google.auto.service.AutoService;
import com.kekie6.colorfulazaleas.ColorfulAzaleasFabric;
import com.kekie6.colorfulazaleas.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.Supplier;

@AutoService(IPlatformHelper.class)
public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void addBlockToAzaleaLootTable(Supplier<Block> block) {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (Blocks.AZALEA_LEAVES.getLootTable().equals(id)) {
                LootPool.Builder poolBuilder1 = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                        .add(LootItem.lootTableItem(block.get()));
                tableBuilder.pool(poolBuilder1.build());
            }
        }));
    }

    @Override
    public CreativeModeTab getCreativeTab() {
        return ColorfulAzaleasFabric.CREATIVE_TAB;
    }
}

package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.gameplay.loot.GenericLootTableModifier;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod(ColorfulAzaleas.MOD_ID)
public class ColorfulAzaleasForge {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ColorfulAzaleas.MOD_ID);

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ColorfulAzaleas.MOD_ID);
    private static final RegistryObject<Codec<GenericLootTableModifier>> GENERIC_LOOT_TABLE_MODIFIER = GLOBAL_LOOT_MODIFIERS.register("generic", GenericLootTableModifier.CODEC);

    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("colorfulAzaleas") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AzaleaBlocks.trees[5].sapling.get().asItem());
        }
    };

    public ColorfulAzaleasForge() {
        ColorfulAzaleas.init();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GLOBAL_LOOT_MODIFIERS.register(eventBus);
    }
}
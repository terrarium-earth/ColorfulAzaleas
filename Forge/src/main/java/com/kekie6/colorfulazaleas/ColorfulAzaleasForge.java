package com.kekie6.colorfulazaleas;

import com.kekie6.colorfulazaleas.gameplay.loot.GenericLootTableModifier;
import com.kekie6.colorfulazaleas.registry.AzaleaBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Optional;

@Mod(ColorfulAzaleas.MOD_ID)
public class ColorfulAzaleasForge {
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ColorfulAzaleas.MOD_ID);
    private static final RegistryObject<Codec<GenericLootTableModifier>> COLORFUL_AZALEA_LOOT_TABLE_MODIFIER = GLOBAL_LOOT_MODIFIERS.register("colorful_azalea", GenericLootTableModifier.CODEC);

    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("colorfulazaleas.colorful_azaleas") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AzaleaBlocks.trees[5].sapling.get().asItem());
        }
    };

    public ColorfulAzaleasForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.addListener((PlayerInteractEvent.RightClickBlock event) -> {
            if (event.getItemStack().getItem() instanceof AxeItem) {
                Level level = event.getLevel();
                BlockPos pos = event.getPos();
                BlockState state = level.getBlockState(pos);
                Optional<AzaleaBlocks.ColorfulTree> optionalTree = Arrays.stream(AzaleaBlocks.trees).filter(tree -> state.is(tree.woodType.log.get()) || state.is(tree.woodType.wood.get())).findFirst();

                if (optionalTree.isPresent()) {
                    Player player = event.getEntity();
                    level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    AzaleaBlocks.WoodType woodType = optionalTree.get().woodType;

                    if (player != null) {
                        player.swing(event.getHand());
                    }

                    if (!level.isClientSide) {
                        BlockState newState = state.is(woodType.log.get()) ? woodType.stripped_log.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)) : woodType.stripped_wood.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                        level.setBlock(pos, newState, 11);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
                        if (player != null) {
                            event.getItemStack().hurtAndBreak(1, player, (playerx) -> {
                                playerx.broadcastBreakEvent(event.getHand());
                            });
                        }
                    }
                }
            }
        });

        ColorfulAzaleas.init();
        GLOBAL_LOOT_MODIFIERS.register(eventBus);
    }
}
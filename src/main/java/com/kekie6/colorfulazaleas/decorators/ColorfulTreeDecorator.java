package com.kekie6.colorfulazaleas.decorators;

import com.kekie6.colorfulazaleas.ColorfulAzaleas;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ColorfulTreeDecorator extends TreeDecorator {

    public static final Codec<ColorfulTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("leaf_block").forGetter(ColorfulTreeDecorator::getLeafBlock),
            BlockStateProvider.CODEC.fieldOf("log_block").forGetter(ColorfulTreeDecorator::getLogBlock)
    ).apply(instance, ColorfulTreeDecorator::new));

    public static final List<Direction> ACCEPTABLE_POS = List.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
    public final BlockStateProvider leafBlock;
    public final BlockStateProvider logBlock;

    public ColorfulTreeDecorator(BlockStateProvider leafBlock, BlockStateProvider logBlock) {
        this.leafBlock = leafBlock;
        this.logBlock = logBlock;
    }

    public BlockStateProvider getLeafBlock() {
        return leafBlock;
    }

    public BlockStateProvider getLogBlock() {
        return logBlock;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ColorfulAzaleas.COLORFUL_TREE_DECORATOR;
    }

    @Override
    public void place(Context context) {
        List<BlockPos> leaves = context.leaves().stream().filter(blockPos -> context.isAir(blockPos.below())).toList();
        for (BlockPos leaf : leaves) {
            if (context.random().nextFloat() >= 0.4f) continue;
            int limit = context.random().nextInt(2, 4);
            for (int i = 1; i <= limit; i++) {
                BlockPos decoration = leaf.below(i).immutable();
                if (context.isAir(decoration)) {
                    context.setBlock(decoration, this.getLeafBlock().getState(context.random(), decoration));
                }
            }
        }
        ObjectArrayList<BlockPos> logs = new ObjectArrayList<>(context.logs());
        logs.sort(Comparator.comparingInt(Vec3i::getY));
        Collections.reverse(logs);
        BlockPos bottomLog = logs.stream().findFirst().orElseThrow();
        for (Direction acceptablePos : ACCEPTABLE_POS) {
            if (context.random().nextFloat() >= 0.55f) continue;
            BlockPos placementPosition = bottomLog.relative(acceptablePos).immutable();
            if (context.isAir(placementPosition)) {
                context.setBlock(placementPosition, this.getLogBlock().getState(context.random(), placementPosition));
            }
        }
    }
}
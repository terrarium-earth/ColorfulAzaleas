package net.fabricmc.colorfulazaleas;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ColorfulTreeDecorator extends TreeDecorator {
    public static final float PROBABILITY = 0.2f;
    public static final Codec<ColorfulTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registry.BLOCK.byNameCodec().fieldOf("leafBlock").forGetter(ColorfulTreeDecorator::getLeafBlock),
            Registry.BLOCK.byNameCodec().fieldOf("logBlock").forGetter(ColorfulTreeDecorator::getLogBlock)
    ).apply(instance, ColorfulTreeDecorator::new));
    public static final List<Direction> ACCEPTABLE_POS = List.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
    public final Block leafBlock;
    public final Block logBlock;


    public ColorfulTreeDecorator(Block leafBlock, Block logBlock) {
        this.leafBlock = leafBlock;
        this.logBlock = logBlock;
    }

    public Block getLeafBlock() {
        return leafBlock;
    }

    public Block getLogBlock() {
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
                    context.setBlock(decoration, this.getLeafBlock().defaultBlockState());
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
                context.setBlock(placementPosition, this.getLogBlock().defaultBlockState());
            }
        }

    }
}


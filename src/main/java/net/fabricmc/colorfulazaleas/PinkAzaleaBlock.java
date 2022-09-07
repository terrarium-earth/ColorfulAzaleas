package net.fabricmc.colorfulazaleas;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PinkAzaleaBlock extends SaplingBlock implements BonemealableBlock {
    private static final VoxelShape SHAPE = Shapes.or(Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0), Block.box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0));

    public PinkAzaleaBlock(Properties properties) {
        super(new PinkAzaleaTreeGrower(), properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.CLAY) || super.mayPlaceOn(state, level, pos);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        return level.getFluidState(pos.above()).isEmpty();
    }
}
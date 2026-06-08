package ether.control_flow.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;




public class BarrelDrumBlock extends Block implements FluidDrainable, FluidFillable {
    private static final BooleanProperty LAVALOGGED = ControlFlowProperties.LAVALOGGED;
    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final DirectionProperty FACING = Properties.FACING;
    private static final VoxelShape DOWN_SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0F, 14.0F, 0.0F, 16.0F,  16.0F, 16.0F), Block.createCuboidShape(0.0F, 0.0F, 0.0F, 2.0F, 16.0F,16.0F), Block.createCuboidShape(14.0F,0.0F, 0.0F, 16.0F, 16.0F, 16.0F), Block.createCuboidShape(2.0F, 0.0F, 14.0F, 14.0F,16.0F, 16.0F), Block.createCuboidShape(2.0F, 0.0F, 0.0F, 14.0F,16.0F, 2.0F));
    private static final VoxelShape UP_SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0F, 0.0F,    0.0F,  16.0F, 2.0F,  16.0F), Block.createCuboidShape(0.0F, 0.0F, 0.0F, 2.0F, 16.0F,16.0F), Block.createCuboidShape(14.0F,0.0F, 0.0F, 16.0F, 16.0F, 16.0F), Block.createCuboidShape(2.0F, 0.0F, 0.0F, 14.0F,16.0F, 2.0F), Block.createCuboidShape(  2.0F, 0.0F, 14.0F,14.0F,16.0F, 16.0F));
    private static final VoxelShape WEST_SHAPE = VoxelShapes.union(Block.createCuboidShape(14.0F, 0.0F, 0.0F,  16.0F, 16.0F, 16.0F), Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F,2.0F, 16.0F), Block.createCuboidShape(0.0F, 14.0F,0.0F, 16.0F, 16.0F, 16.0F), Block.createCuboidShape(0.0F, 2.0F, 0.0F, 16.0F,14.0F, 2.0F), Block.createCuboidShape(  0.0F, 2.0F, 14.0F,16.0F,14.0F, 16.0F));
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(Block.createCuboidShape( 0.0F, 0.0F, 0.0F,  2.0F,  16.0F, 16.0F), Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F,2.0F, 16.0F), Block.createCuboidShape(0.0F, 14.0F,0.0F, 16.0F, 16.0F, 16.0F), Block.createCuboidShape(0.0F, 2.0F, 14.0F, 16.0F,14.0F, 16.0F), Block.createCuboidShape(0.0F, 2.0F, 0.0F, 16.0F,14.0F, 2.0F));
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 16.0F), Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F,2.0F, 16.0F), Block.createCuboidShape(0.0F, 14.0F,0.0F, 16.0F, 16.0F, 16.0F), Block.createCuboidShape(14.0F, 2.0F, 0.0F, 16.0F,14.0F, 16.0F), Block.createCuboidShape(0.0F, 2.0F, 0.0F, 2.0F, 14.0F, 16.0F));
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0F, 0.0F, 0.0F,  16.0F, 16.0F, 2.0F), Block.createCuboidShape( 0.0F, 0.0F, 0.0F, 16.0F,2.0F, 16.0F), Block.createCuboidShape(0.0F, 14.0F,0.0F, 16.0F, 16.0F, 16.0F), Block.createCuboidShape(0.0F, 2.0F, 0.0F, 2.0F, 14.0F, 16.0F), Block.createCuboidShape( 14.0F,2.0F, 0.0F, 16.0F,14.0F, 16.0F));

    public BarrelDrumBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LAVALOGGED, false).with(WATERLOGGED, false).with(FACING, Direction.NORTH));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, LAVALOGGED);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(FACING, direction).with(FACING, direction).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(LAVALOGGED, fluidState.getFluid() == Fluids.LAVA);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Fluid fluid = state.get(WATERLOGGED) ? Fluids.WATER : state.get(LAVALOGGED) ? Fluids.LAVA : null;
        if (fluid != null) {
            world.scheduleFluidTick(pos, fluid, fluid.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case Direction.DOWN -> DOWN_SHAPE;
            case Direction.UP -> UP_SHAPE;
            case Direction.WEST -> WEST_SHAPE;
            case Direction.EAST -> EAST_SHAPE;
            case Direction.NORTH -> NORTH_SHAPE;
            case Direction.SOUTH -> SOUTH_SHAPE;
        };
    }

    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return !(state.get(ControlFlowProperties.LAVALOGGED) || state.get(Properties.WATERLOGGED));
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluid_state) {
        boolean filled = state.get(ControlFlowProperties.LAVALOGGED) || state.get(Properties.WATERLOGGED);
        Fluid fluid = fluid_state.getFluid();
        BooleanProperty property = fluid == Fluids.WATER ? Properties.WATERLOGGED : fluid == Fluids.LAVA ? ControlFlowProperties.LAVALOGGED : null;
        boolean can_fill = !filled && property != null;
        if (can_fill && !world.isClient()) {
            world.setBlockState(pos, state.with(property, true), 3);
            world.scheduleFluidTick(pos, fluid, fluid.getTickRate(world));
        }
        return can_fill;
    }

     public ItemStack tryDrainFluid(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        BooleanProperty property = state.get(Properties.WATERLOGGED) ? Properties.WATERLOGGED : state.get(ControlFlowProperties.LAVALOGGED) ? ControlFlowProperties.LAVALOGGED : null;
        if (property != null) {
            world.setBlockState(pos, state.with(property, false), 3);
            if (!state.canPlaceAt(world, pos)) {
                world.breakBlock(pos, true);
            }
        }
        Item bucket = state.get(Properties.WATERLOGGED) ? Items.WATER_BUCKET :  state.get(ControlFlowProperties.LAVALOGGED)  ? Items.LAVA_BUCKET : null;
        return bucket != null ? new ItemStack(bucket) : ItemStack.EMPTY;
    }

    public FluidState getFluidState(BlockState state) {
        FlowableFluid fluid = state.get(WATERLOGGED) ? Fluids.WATER : state.get(LAVALOGGED) ? Fluids.LAVA : null;
        return fluid != null ? fluid.getStill(false) : super.getFluidState(state);
    }

    public Optional<SoundEvent> getBucketFillSound() {
        return Fluids.WATER.getBucketFillSound();
    }
}

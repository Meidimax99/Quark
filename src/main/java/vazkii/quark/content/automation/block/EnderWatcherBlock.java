package vazkii.quark.content.automation.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.automation.block.be.EnderWatcherBlockEntity;
import vazkii.quark.content.automation.module.EnderWatcherModule;

public class EnderWatcherBlock extends QuarkBlock implements EntityBlock {
	
	public static final BooleanProperty WATCHED = BooleanProperty.create("watched");
	public static final IntegerProperty POWER = BlockStateProperties.POWER;

	public EnderWatcherBlock(QuarkModule module) {
		super("ender_watcher", module, CreativeModeTab.TAB_REDSTONE, 
				Block.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN)
				.strength(3F, 10F)
				.sound(SoundType.METAL));
		
		registerDefaultState(defaultBlockState().setValue(WATCHED, false).setValue(POWER, 0));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(WATCHED, POWER);
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}
	
	@Override
	public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
		return blockState.getValue(POWER);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new EnderWatcherBlockEntity(p_153215_, p_153216_);
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, EnderWatcherModule.blockEntityType, EnderWatcherBlockEntity::tick);
	}

}

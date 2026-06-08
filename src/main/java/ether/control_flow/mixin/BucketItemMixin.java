package ether.control_flow.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.FluidDrainable;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.item.BucketItem;

import java.util.Optional;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends Item implements BucketItemAccessor {
	@Shadow
	@Final
	private Fluid fluid;
	private BucketItemMixin(Item.Settings settings) {
		super(settings);
	}

	@WrapOperation(
			method = "use",
			at = @At(value = "FIELD", target = "Lnet/minecraft/fluid/Fluids;WATER:Lnet/minecraft/fluid/FlowableFluid;", opcode = Opcodes.GETSTATIC)
	)
	private FlowableFluid use(Operation<FlowableFluid> original) {
		return (FlowableFluid) this.fluid;
	}

	@WrapOperation(
			method = "placeFluid",
			at = @At(value = "FIELD", target = "Lnet/minecraft/fluid/Fluids;WATER:Lnet/minecraft/fluid/FlowableFluid;",	opcode = Opcodes.GETSTATIC)
	)
	private FlowableFluid placeFluid(Operation<FlowableFluid> original) {
		return (FlowableFluid) this.fluid;
	}

	@WrapOperation(
			method = "use",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FluidDrainable;getBucketFillSound()Ljava/util/Optional;")
	)
	private Optional<SoundEvent> redirect_sound(FluidDrainable instance, Operation<Optional<SoundEvent>> original, @Local(name = "itemStack2") ItemStack filledBucket) {
		return ((BucketItemAccessor)filledBucket.getItem()).getFluid().getBucketFillSound();
	}

}

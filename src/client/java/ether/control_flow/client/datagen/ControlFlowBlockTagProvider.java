package ether.control_flow.client.datagen;

import ether.control_flow.block.ControlFlowBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ControlFlowBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ControlFlowBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(ControlFlowBlocks.BARREL_DRUM);
        this.getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(ControlFlowBlocks.BARREL_DRUM);
    }
}
package ether.control_flow.client.datagen;

import ether.control_flow.block.ControlFlowBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ControlFlowLootTableProvider extends FabricBlockLootTableProvider {
    public ControlFlowLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.addDrop(ControlFlowBlocks.BARREL_DRUM);
    }
}

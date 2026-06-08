package ether.control_flow.client.datagen;

import ether.control_flow.block.ControlFlowBlocks;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

public class ControlFlowLanguageProvider extends FabricLanguageProvider {

    public ControlFlowLanguageProvider(
        FabricDataOutput dataOutput,
        CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup
    ) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(
        RegistryWrapper.WrapperLookup wrapperLookup,
        TranslationBuilder translationBuilder
    ) {
        translationBuilder.add(ControlFlowBlocks.BARREL_DRUM, "Barrel Drum");
    }
}

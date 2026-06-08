package ether.control_flow.client;

import ether.control_flow.client.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ControlFlowDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ControlFlowLanguageProvider::new);
		pack.addProvider(ControlFlowRecipeProvider::new);
		pack.addProvider(ControlFlowLootTableProvider::new);
		pack.addProvider(ControlFlowBlockTagProvider::new);
	}
}

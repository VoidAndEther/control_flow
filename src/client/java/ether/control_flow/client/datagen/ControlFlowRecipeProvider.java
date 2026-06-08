package ether.control_flow.client.datagen;

import ether.control_flow.block.ControlFlowBlocks;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class ControlFlowRecipeProvider extends FabricRecipeProvider {

    public ControlFlowRecipeProvider(
        FabricDataOutput output,
        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(
            RecipeCategory.MISC,
            ControlFlowBlocks.BARREL_DRUM,
            2
        )
            .pattern("IBI")
            .pattern("III")
            .input('I', Items.IRON_INGOT)
            .input('B', Items.BLUE_DYE)
            .criterion(
                hasItem(Items.IRON_INGOT),
                conditionsFromItem(Items.IRON_INGOT)
            )
            .criterion(
                hasItem(Items.BLUE_DYE),
                conditionsFromItem(Items.BLUE_DYE)
            )
            .offerTo(
                exporter,
                Identifier.of(getRecipeName(ControlFlowBlocks.BARREL_DRUM))
            );
    }
}

package ether.control_flow.block;

import ether.control_flow.ControlFlow;
import ether.control_flow.item.ControlFlowItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ControlFlowBlocks {
    public static BarrelDrumBlock BARREL_DRUM = registerBlockitem("barrel_drum", new BarrelDrumBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));


    private static <T extends Block> T registerBlock(String name, T block) {
        return Registry.register(Registries.BLOCK, Identifier.of(ControlFlow.MOD_ID, name), block);
    }
    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> T registerBlockitem(String name, T block) {
        ControlFlowItems.registerItem(name, new BlockItem(block, new Item.Settings()));
        return registerBlock(name, block);
    }

    private static void addFunctionalBlockTab(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.addAfter(Items.BARREL, BARREL_DRUM);
    }

    public static void register() {
        ControlFlow.LOGGER.info("Control Flow blocks registering");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ControlFlowBlocks::addFunctionalBlockTab);
    }
}
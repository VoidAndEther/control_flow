package ether.control_flow.item;

import ether.control_flow.ControlFlow;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ControlFlowItems {
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, Identifier.of(ControlFlow.MOD_ID, name), item);
    }
}

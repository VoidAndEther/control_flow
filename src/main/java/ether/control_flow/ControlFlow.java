package ether.control_flow;

import ether.control_flow.block.ControlFlowBlocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControlFlow implements ModInitializer {

    public static final String MOD_ID = "control_flow";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Control Flow registering");
        ControlFlowBlocks.register();
    }
}

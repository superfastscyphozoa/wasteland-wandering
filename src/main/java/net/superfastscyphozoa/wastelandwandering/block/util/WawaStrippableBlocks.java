package net.superfastscyphozoa.wastelandwandering.block.util;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

public class WawaStrippableBlocks {
    public static void registerStrippableBlocks(){
        StrippableBlockRegistry.register(RegisterBlocks.MUTFRUIT_LOG, RegisterBlocks.STRIPPED_MUTFRUIT_LOG);
    }
}

package net.superfastscyphozoa.wastelandwandering.block.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

public class WawaFlammableBlocks {
    public static void registerFlammableBlocks(){

        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.MUTFRUIT_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.STRIPPED_MUTFRUIT_LOG, 5, 5);

        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.IRRADIATED_SHORT_GRASS, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.IRRADIATED_TALL_GRASS, 30, 60);

        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.POISONED_IVY, 30, 60);

        //FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.MUTFRUIT_LEAVES, 30, 60);
    }
}

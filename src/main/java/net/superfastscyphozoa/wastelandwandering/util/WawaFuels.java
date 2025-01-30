package net.superfastscyphozoa.wastelandwandering.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class WawaFuels {
    public static void registerFuels(){
        FuelRegistry.INSTANCE.add(RegisterItems.OIL, 2800);

        FuelRegistry.INSTANCE.add(RegisterBlocks.MUTFRUIT_LOG, 100);
        FuelRegistry.INSTANCE.add(RegisterBlocks.STRIPPED_MUTFRUIT_LOG, 100);
    }
}

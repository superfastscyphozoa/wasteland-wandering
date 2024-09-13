package net.superfastscyphozoa.wastelandwandering.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class WawaFuels {
    public static void registerFuels(){
        FuelRegistry.INSTANCE.add(RegisterItems.OIL, 2800);
    }
}

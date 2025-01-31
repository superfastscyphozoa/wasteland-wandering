package net.superfastscyphozoa.wastelandwandering.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class WawaItemBlockUtil {

    public static void registerItemBlockUtil(){
        registerFuels();
        registerStrippableBlocks();
        registerFlammableBlocks();
    }

    //item and block util
    public static void registerFuels(){
        FuelRegistry.INSTANCE.add(RegisterItems.OIL, 2800);

        FuelRegistry.INSTANCE.add(RegisterBlocks.MUTFRUIT_LOG, 100);
        FuelRegistry.INSTANCE.add(RegisterBlocks.STRIPPED_MUTFRUIT_LOG, 100);
    }

    public static void registerStrippableBlocks(){
        StrippableBlockRegistry.register(RegisterBlocks.MUTFRUIT_LOG, RegisterBlocks.STRIPPED_MUTFRUIT_LOG);
    }

    public static void registerFlammableBlocks(){

        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.MUTFRUIT_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.STRIPPED_MUTFRUIT_LOG, 5, 5);

        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.MUTFRUIT_LEAVES, 30, 60);

        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.IRRADIATED_SHORT_GRASS, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.IRRADIATED_TALL_GRASS, 30, 60);

        FlammableBlockRegistry.getDefaultInstance().add(RegisterBlocks.POISONED_IVY, 30, 60);
    }
}

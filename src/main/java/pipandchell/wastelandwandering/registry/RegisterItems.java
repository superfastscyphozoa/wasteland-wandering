package pipandchell.wastelandwandering.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pipandchell.wastelandwandering.WastelandWandering;

public class RegisterItems {

    //registry

    public static final Item STIMPAK = registerItem("stimpak", new Item(new Item.Settings()));

    //registry end

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WastelandWandering.MOD_ID, name), item);
    }

    public static void registerWawaItems() {
        WastelandWandering.LOGGER.info("Registering Items for " + WastelandWandering.MOD_ID);

        //tells the game where to put the items in the inventory
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(STIMPAK);
        });
    }
}

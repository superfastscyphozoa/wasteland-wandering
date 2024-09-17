package net.superfastscyphozoa.wastelandwandering.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.item.DynamiteItem;
import net.superfastscyphozoa.wastelandwandering.item.MolotovCocktailItem;
import net.superfastscyphozoa.wastelandwandering.item.OilItem;
import net.superfastscyphozoa.wastelandwandering.item.util.WawaFoodComponents;

public class RegisterItems {

    //registry

    //chems and health items

    public static final Item STIMPAK = registerItem("stimpak", new Item(new Item.Settings()));

    //food

    public static final Item MUTFRUIT = registerItem("mutfruit", new Item(new Item.Settings()
            .food(WawaFoodComponents.MUTFRUIT)));

    //materials

    public static final Item OIL = registerItem("oil", new OilItem(new Item.Settings()));

    //combat

    public static final Item DYNAMITE = registerItem("dynamite", new DynamiteItem(new Item.Settings()));

    public static final Item MOLOTOV_COCKTAIL = registerItem("molotov_cocktail", new MolotovCocktailItem(new Item.Settings()));

    //registry end

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WastelandWandering.MOD_ID, name), item);
    }

    public static void registerWawaItems() {
        WastelandWandering.LOGGER.info("Registering Items for " + WastelandWandering.MOD_ID);

        //tells the game where to put the items in the inventory

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(STIMPAK);
            entries.addAfter(Items.APPLE, MUTFRUIT);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addBefore(Items.CLAY_BALL, OIL);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.WIND_CHARGE, OIL);

            entries.addAfter(Blocks.TNT, DYNAMITE);
            entries.addAfter(DYNAMITE, MOLOTOV_COCKTAIL);
        });
    }
}

package net.superfastscyphozoa.wastelandwandering.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.item.*;
import net.superfastscyphozoa.wastelandwandering.item.util.WawaFoodComponents;

public class RegisterItems {

    //registry

    //chems and health items

    public static final Item STIMPAK = registerItem("stimpak", new Item(new Item.Settings()));

    public static final Item RADAWAY = registerItem("radaway", new Item(new Item.Settings()));

    public static final Item BLOOD_PACK = registerItem("blood_pack", new Item(new Item.Settings()));
    public static final Item GLOWING_BLOOD_PACK = registerItem("glowing_blood_pack", new Item(new Item.Settings()));

    //food

    public static final Item MUTFRUIT = registerItem("mutfruit", new Item(new Item.Settings()
            .food(WawaFoodComponents.MUTFRUIT)));

    //alcohol

    public static final Item BEER = registerItem("beer", new AlcoholBottleItem(new Item.Settings()
            .food(WawaFoodComponents.ALCOHOL)
            .maxCount(16)));

    public static final Item VODKA = registerItem("vodka", new AlcoholBottleItem(new Item.Settings()
            .food(WawaFoodComponents.ALCOHOL)
            .maxCount(16)));

    //combat

    //melee

    public static final Item BUMPER_SWORD = registerItem("bumper_sword", new BumperSwordItem(new Item.Settings()
            .rarity(Rarity.EPIC)
            .maxDamage(250)
            .component(DataComponentTypes.TOOL, BumperSwordItem.createToolComponent())
            .attributeModifiers(BumperSwordItem.createAttributeModifiers())
    ));

    public static final Item BROKEN_BOTTLE = registerItem("broken_bottle", new BrokenBottleItem(new Item.Settings()
            .maxDamage(8)
            .component(DataComponentTypes.TOOL, BrokenBottleItem.createToolComponent())
            .attributeModifiers(BrokenBottleItem.createAttributeModifiers())
    ));

    //throwable

    public static final Item DYNAMITE = registerItem("dynamite", new DynamiteItem(new Item.Settings()));

    public static final Item MOLOTOV_COCKTAIL = registerItem("molotov_cocktail", new MolotovCocktailItem(new Item.Settings()));

    //materials

    public static final Item OIL = registerItem("oil", new OilItem(new Item.Settings()));

    public static final Item IV_BAG = registerItem("iv_bag", new Item(new Item.Settings()));

    //registry end

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WastelandWandering.MOD_ID, name), item);
    }

    public static void registerWawaItems() {
        WastelandWandering.LOGGER.info("Registering Items for " + WastelandWandering.MOD_ID);

        //tells the game where to put the items in the inventory

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {

            //chems and healing items
            entries.addAfter(Items.SPIDER_EYE, STIMPAK);
            entries.addAfter(STIMPAK, RADAWAY);
            entries.addAfter(RADAWAY, BLOOD_PACK);
            entries.addAfter(BLOOD_PACK, GLOWING_BLOOD_PACK);

            //food
            entries.addAfter(Items.APPLE, MUTFRUIT);

            //alcohol
            entries.addAfter(Items.HONEY_BOTTLE, BEER);
            entries.addAfter(BEER, VODKA);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

            entries.addBefore(Items.CLAY_BALL, OIL);

            entries.addBefore(Items.BOWL, IV_BAG);

            entries.addAfter(Items.GLASS_BOTTLE, VODKA);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            //melee

            entries.addAfter(Items.MACE, BUMPER_SWORD);
            entries.addAfter(BUMPER_SWORD, BROKEN_BOTTLE);

            //throwable

            entries.addAfter(Items.WIND_CHARGE, OIL);

            entries.addAfter(Blocks.TNT, DYNAMITE);
            entries.addAfter(DYNAMITE, MOLOTOV_COCKTAIL);
        });
    }
}

package net.superfastscyphozoa.wastelandwandering.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class WawaTrades {
    public static void registerTrades(){

        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 3),
                    new ItemStack(RegisterItems.STIMPAK,2),
                    5, 5, 0.05f
            ));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    new ItemStack(RegisterItems.MUTFRUIT, 4),
                    4, 5, 0.05f
            ));
        });

    }
}

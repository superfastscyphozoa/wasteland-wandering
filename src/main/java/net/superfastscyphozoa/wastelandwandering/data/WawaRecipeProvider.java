package net.superfastscyphozoa.wastelandwandering.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class WawaRecipeProvider extends FabricRecipeProvider {
    public WawaRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        wawaShapelessRecipe(exporter, RegisterItems.DYNAMITE, 4, Blocks.TNT, RecipeCategory.COMBAT, "dynamite");

        molotovRecipe(exporter, RegisterItems.MOLOTOV_COCKTAIL, RegisterItems.VODKA, Items.STRING);
        molotovRecipe(exporter, RegisterItems.MOLOTOV_COCKTAIL, RegisterItems.VODKA, Items.PAPER);

    }

    public static void wawaShapelessRecipe(
            RecipeExporter exporter,
            ItemConvertible output, int outputCount,
            ItemConvertible input,
            RecipeCategory recipeCategory, @Nullable String group)
    {
        ShapelessRecipeJsonBuilder.create(recipeCategory, output, outputCount)
                .input(input)
                .group(group)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, Identifier.of(WastelandWandering.MOD_ID, convertBetween(output, input)));
    }

    public static void molotovRecipe(
            RecipeExporter exporter,
            ItemConvertible output,
            ItemConvertible input, ItemConvertible input2)
    {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input(input).input(input2)
                .group("molotov_cocktail")
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, Identifier.of(WastelandWandering.MOD_ID, convertBetween(output, input2)));
    }
}

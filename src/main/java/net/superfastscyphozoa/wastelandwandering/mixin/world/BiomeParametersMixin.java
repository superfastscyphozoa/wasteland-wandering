package net.superfastscyphozoa.wastelandwandering.mixin.world;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.superfastscyphozoa.wastelandwandering.world.biome.WawaBiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VanillaBiomeParameters.class)
public class BiomeParametersMixin {

	@Unique
	private final RegistryKey<Biome>[][] wastelandBiomes = new RegistryKey[][]{
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
	};

	@Redirect(method = "writeMidBiomes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getRegularBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
	private RegistryKey<Biome> inject(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {

		return getWastelandBiome(temperature, humidity, weirdness);
	}

	@Unique
	private RegistryKey<Biome> getWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return this.wastelandBiomes[temperature][humidity];
	}
}
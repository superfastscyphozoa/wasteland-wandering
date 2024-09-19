package net.superfastscyphozoa.wastelandwandering.mixin.world;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.superfastscyphozoa.wastelandwandering.world.biome.WawaBiomeKeys;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(VanillaBiomeParameters.class)
public abstract class BiomeParametersMixin {

	@Unique
	private final RegistryKey<Biome>[][] commonWastelandBiomes = new RegistryKey[][]{
			{BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA},
			{BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
			{BiomeKeys.DESERT, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST},
			{BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT}
	};

	@Unique
	private final RegistryKey<Biome>[][] uncommonWastelandBiomes = new RegistryKey[][]{
			{null, null, BiomeKeys.SNOWY_TAIGA, null, null},
			{null, null, null, null, BiomeKeys.OLD_GROWTH_PINE_TAIGA},
			{BiomeKeys.SUNFLOWER_PLAINS, null, null, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, null},
			{null, null, BiomeKeys.PLAINS, null, null},
			{null, null, null, null, null}
	};

	@Unique
	private final RegistryKey<Biome>[][] nearMountainWastelandBiomes = new RegistryKey[][]{
			{BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA},
			{BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
			{BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.DARK_FOREST},
			{BiomeKeys.FOREST, BiomeKeys.FOREST, BiomeKeys.FOREST, BiomeKeys.FOREST, BiomeKeys.DARK_FOREST},
			{BiomeKeys.BADLANDS, BiomeKeys.BADLANDS, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS, BiomeKeys.WOODED_BADLANDS}
	};

	@Unique
	private final RegistryKey<Biome>[][] specialNearMountainWastelandBiomes = new RegistryKey[][]{
			{BiomeKeys.ICE_SPIKES, null, null, null, null},
			{BiomeKeys.MEADOW, null, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA},
			{BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, null},
			{null, null, null, null, null},
			{BiomeKeys.ERODED_BADLANDS, BiomeKeys.ERODED_BADLANDS, null, null, null}
	};

	@Unique
	private final RegistryKey<Biome>[][] windsweptWastelandBiomes = new RegistryKey[][]{
			{BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST},
			{BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST},
			{BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST},
			{null, null, null, null, null},
			{null, null, null, null, null}
	};

	//overwrite biomes

	//@Redirect(method = "getWindsweptOrRegularBiome")

	@Redirect(method = "writeMidBiomes", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getRegularBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
	private RegistryKey<Biome> midReg(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return getRegularWastelandBiome(temperature, humidity, weirdness);
	}

	@Redirect(method = "writeMidBiomes", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getBadlandsOrRegularBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
	private RegistryKey<Biome> midBadlandsOrReg(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return getBadlandsOrRegularWastelandBiome(temperature, humidity, weirdness);
	}

	@Redirect(method = "writeMidBiomes", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getMountainStartBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
	private RegistryKey<Biome> midMountainStart(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return getMountainStartWastelandBiome(temperature, humidity, weirdness);
	}

	@Redirect(method = "writeMidBiomes", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getWindsweptOrRegularBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
	private RegistryKey<Biome> midWindsweptOrReg(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return getWindsweptOrRegularWastelandBiome(temperature, humidity, weirdness);
	}

	@Redirect(method = "writeMidBiomes", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;getWindsweptOrRegularBiome(IILnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;)Lnet/minecraft/registry/RegistryKey;"))
	private RegistryKey<Biome> midNearMount(VanillaBiomeParameters instance, int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return getNearMountainWastelandBiome(temperature, humidity, weirdness);
	}

	// get biomes

	@Contract(pure = true)
	@Unique
	private RegistryKey<Biome> getRegularWastelandBiome(int temperature, int humidity, MultiNoiseUtil.@NotNull ParameterRange weirdness) {
		if (weirdness.max() < 0L) {
			return this.commonWastelandBiomes[temperature][humidity];
		} else {
			RegistryKey<Biome> registryKey = this.uncommonWastelandBiomes[temperature][humidity];
			return registryKey == null ? this.commonWastelandBiomes[temperature][humidity] : registryKey;
		}
	}

	@Unique
	private RegistryKey<Biome> getBadlandsOrRegularWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return temperature == 4 ? this.getBadlandsWastelandBiome(humidity, weirdness) : this.getRegularWastelandBiome(temperature, humidity, weirdness);
	}

	@Unique
	private RegistryKey<Biome> getMountainStartWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		return temperature == 0 ? this.getMountainSlopeWastelandBiome(temperature, humidity, weirdness) : this.getBadlandsOrRegularWastelandBiome(temperature, humidity, weirdness);
	}

	@Unique
	private RegistryKey<Biome> getWastelandBiomeOrWindsweptHills(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, RegistryKey<Biome> biomeKey) {
		return temperature > 1 && humidity < 4 && weirdness.max() >= 0L ? BiomeKeys.WINDSWEPT_HILLS : biomeKey;
	}

	@Unique
	private RegistryKey<Biome> getErodedShoreWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		RegistryKey<Biome> registryKey = weirdness.max() >= 0L ? this.getRegularWastelandBiome(temperature, humidity, weirdness) : this.getShoreWastelandBiome(temperature, humidity);
		return this.getWastelandBiomeOrWindsweptHills(temperature, humidity, weirdness, registryKey);
	}

	@Unique
	private RegistryKey<Biome> getShoreWastelandBiome(int temperature, int humidity) {
		if (temperature == 0) {
			return BiomeKeys.SNOWY_BEACH;
		} else {
			return temperature == 4 ? BiomeKeys.DESERT : BiomeKeys.BEACH;
		}
	}

	@Unique
	private RegistryKey<Biome> getBadlandsWastelandBiome(int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		if (humidity < 2) {
			return weirdness.max() < 0L ? BiomeKeys.BADLANDS : BiomeKeys.ERODED_BADLANDS;
		} else {
			return humidity < 3 ? BiomeKeys.BADLANDS : BiomeKeys.WOODED_BADLANDS;
		}
	}

	@Unique
	private RegistryKey<Biome> getNearMountainWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		if (weirdness.max() >= 0L) {
			RegistryKey<Biome> registryKey = this.specialNearMountainWastelandBiomes[temperature][humidity];
			if (registryKey != null) {
				return registryKey;
			}
		}

		return this.nearMountainWastelandBiomes[temperature][humidity];
	}

	@Unique
	private RegistryKey<Biome> getPeakWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		if (temperature <= 2) {
			return weirdness.max() < 0L ? BiomeKeys.JAGGED_PEAKS : BiomeKeys.FROZEN_PEAKS;
		} else {
			return temperature == 3 ? BiomeKeys.STONY_PEAKS : this.getBadlandsWastelandBiome(humidity, weirdness);
		}
	}

	@Unique
	private RegistryKey<Biome> getMountainSlopeWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		if (temperature >= 3) {
			return this.getNearMountainWastelandBiome(temperature, humidity, weirdness);
		} else {
			return humidity <= 1 ? BiomeKeys.SNOWY_SLOPES : BiomeKeys.GROVE;
		}
	}

	@Unique
	private RegistryKey<Biome> getWindsweptOrRegularWastelandBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
		RegistryKey<Biome> registryKey = this.windsweptWastelandBiomes[temperature][humidity];
		return registryKey == null ? this.getRegularWastelandBiome(temperature, humidity, weirdness) : registryKey;
	}

}
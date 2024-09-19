package net.superfastscyphozoa.wastelandwandering.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.entity.projectile.DynamiteEntity;
import net.superfastscyphozoa.wastelandwandering.entity.projectile.MolotovCocktailEntity;
import net.superfastscyphozoa.wastelandwandering.entity.projectile.OilEntity;

public class RegisterEntities {

    public static final EntityType<OilEntity> OIL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(WastelandWandering.MOD_ID, "oil_projectile"),
            EntityType.Builder.<OilEntity>create(OilEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10).build());

    public static final EntityType<DynamiteEntity> DYNAMITE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(WastelandWandering.MOD_ID, "dynamite_projectile"),
            EntityType.Builder.<DynamiteEntity>create(DynamiteEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10).build());

    public static final EntityType<MolotovCocktailEntity> MOLOTOV_COCKTAIL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(WastelandWandering.MOD_ID, "molotov_cocktail_projectile"),
            EntityType.Builder.<MolotovCocktailEntity>create(MolotovCocktailEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10).build());

    public static void registerWawaEntities() {
        WastelandWandering.LOGGER.info("Registering Entities for " + WastelandWandering.MOD_ID);
    }

}

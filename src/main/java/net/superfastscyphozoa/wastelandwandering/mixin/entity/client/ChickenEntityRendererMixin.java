package net.superfastscyphozoa.wastelandwandering.mixin.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.entity.variant.ChickenVariant;
import net.superfastscyphozoa.wastelandwandering.entity.variant.ChickenVariantGetterInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(ChickenEntityRenderer.class)
public class ChickenEntityRendererMixin {

    @Unique
    private static final Map<ChickenVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ChickenVariant.class), map -> {
                map.put(ChickenVariant.BLACK, Identifier.of(WastelandWandering.MOD_ID, "textures/entity/chicken/black_chicken.png"));
                map.put(ChickenVariant.BROWN, Identifier.of(WastelandWandering.MOD_ID, "textures/entity/chicken/brown_chicken.png"));
                map.put(ChickenVariant.WHITE, Identifier.of(WastelandWandering.MOD_ID, "textures/entity/chicken/white_chicken.png"));
                map.put(ChickenVariant.SEMIRAD, Identifier.of(WastelandWandering.MOD_ID, "textures/entity/chicken/semi_rad_chicken.png"));
                map.put(ChickenVariant.RAD, Identifier.of(WastelandWandering.MOD_ID, "textures/entity/chicken/rad_chicken.png"));
            });

    @Redirect(method = "getTexture(Lnet/minecraft/entity/passive/ChickenEntity;)Lnet/minecraft/util/Identifier;", at = @At(value = "FIELD",
            target = "Lnet/minecraft/client/render/entity/ChickenEntityRenderer;TEXTURE:Lnet/minecraft/util/Identifier;"))
    public Identifier getTexture(ChickenEntity chickenEntity) {
        if (chickenEntity instanceof ChickenVariantGetterInterface){
            return LOCATION_BY_VARIANT.get(((ChickenVariantGetterInterface) chickenEntity).wasteland_wandering$getVariant());
        } else {
            return Identifier.of(WastelandWandering.MOD_ID, "textures/entity/chicken/semi_rad_chicken.png");
        }
    }
}

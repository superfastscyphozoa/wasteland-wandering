package net.superfastscyphozoa.wastelandwandering.mixin.world;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VanillaSurfaceRules.class)
public class SurfaceRulesMixin {

//    @WrapOperation(
//            method = "createDefaultRule",
//            at = @At(value = "FIELD", target = "Lnet/minecraft/world/gen/surfacebuilder/VanillaSurfaceRules;GRASS_BLOCK:Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialRule;")
//    )
//    private static MaterialRules.MaterialRule replaceGrass(Operation<MaterialRules.MaterialRule> original) {
//
//        return block(RegisterBlocks.IRRADIATED_GRASS_BLOCK.getDefaultState());
//    }

    @WrapMethod(
            method = "createDefaultRule"
    )
    private static MaterialRules.MaterialRule e
            (boolean surface, boolean bedrockRoof, boolean bedrockFloor, Operation<MaterialRules.MaterialRule> original)
    {

        return null;
    }

}

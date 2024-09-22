package net.superfastscyphozoa.wastelandwandering.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useBumperSwordModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        if (stack.isOf(RegisterItems.BUMPER_SWORD) && renderMode != ModelTransformationMode.GUI && renderMode != ModelTransformationMode.GROUND) {
            return ((ItemRendererAccessor) this).wasteland_wandering$getModels().getModelManager()
                    .getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(WastelandWandering.MOD_ID,"bumper_sword_large")));
        }

        return value;
    }
}

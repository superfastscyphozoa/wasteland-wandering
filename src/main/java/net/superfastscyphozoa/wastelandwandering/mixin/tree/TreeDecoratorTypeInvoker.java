package net.superfastscyphozoa.wastelandwandering.mixin.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeInvoker {
    @Invoker
    static <P extends TreeDecorator> TreeDecoratorType<P> callRegister(String id, MapCodec<P> codec) {
        throw new IllegalStateException();
    }
}

package net.superfastscyphozoa.wastelandwandering.world.tree.decorator;

import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.mixin.tree.TreeDecoratorTypeInvoker;

public class WawaTreeDecoratorTypes {
    public static final TreeDecoratorType<?> BRANCH_TREE_DECORATOR =
            TreeDecoratorTypeInvoker.callRegister("branch_tree_decorator", BranchTreeDecorator.CODEC);

    public static void register() {
        WastelandWandering.LOGGER.info("registering tree decorators for " + WastelandWandering.MOD_ID);
    }
}

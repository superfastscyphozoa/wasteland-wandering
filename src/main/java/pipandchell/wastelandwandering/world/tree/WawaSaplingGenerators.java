package pipandchell.wastelandwandering.world.tree;

import net.minecraft.block.SaplingGenerator;
import pipandchell.wastelandwandering.world.features.configured.WawaTreeConfiguredFeatures;

import java.util.Optional;

public class WawaSaplingGenerators {
    public static final SaplingGenerator WASTEWOOD =
            new SaplingGenerator("wastewood", 0f, Optional.empty(),
                    Optional.empty(),
                    Optional.of(WawaTreeConfiguredFeatures.WASTEWOOD_KEY),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());

    public static final SaplingGenerator RADPINE =
            new SaplingGenerator("radpine", 0f, Optional.empty(),
                    Optional.empty(),
                    Optional.of(WawaTreeConfiguredFeatures.RADPINE_KEY),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());
}

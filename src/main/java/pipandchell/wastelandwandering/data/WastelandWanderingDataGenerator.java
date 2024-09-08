package pipandchell.wastelandwandering.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WastelandWanderingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(WawaLootTableProvider::new);
		pack.addProvider(WawaModelProvider::new);
		pack.addProvider(WawaRecipeProvider::new);

	}
}

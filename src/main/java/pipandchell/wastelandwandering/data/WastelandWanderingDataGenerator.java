package pipandchell.wastelandwandering.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import pipandchell.wastelandwandering.data.tags.WawaBlockTagProvider;
import pipandchell.wastelandwandering.data.tags.WawaItemTagProvider;

public class WastelandWanderingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(WawaLootTableProvider::new);
		pack.addProvider(WawaModelProvider::new);
		pack.addProvider(WawaRecipeProvider::new);

		pack.addProvider(WawaBlockTagProvider::new);
		pack.addProvider(WawaItemTagProvider::new);



	}
}

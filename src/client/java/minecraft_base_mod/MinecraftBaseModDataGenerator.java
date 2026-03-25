package minecraft_base_mod;

import minecraft_base_mod.datagen.ItemTagProvider;
import minecraft_base_mod.datagen.ModelProvider;
import minecraft_base_mod.datagen.RecipeProvider;
import minecraft_base_mod.datagen.lang.EnglishLangProvider;
import minecraft_base_mod.datagen.lang.GermanLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MinecraftBaseModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EnglishLangProvider::new);
		pack.addProvider(GermanLangProvider::new);
		pack.addProvider(ModelProvider::new);
		pack.addProvider(RecipeProvider:: new);
		pack.addProvider(ItemTagProvider:: new);
	}
}

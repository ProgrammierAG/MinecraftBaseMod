package minecraft_base_mod;

import minecraft_base_mod.datagen.MinecraftBaseModItemTagProvider;
import minecraft_base_mod.datagen.MinecraftBaseModModelProvider;
import minecraft_base_mod.datagen.MinecraftBaseModRecipeProvider;
import minecraft_base_mod.datagen.lang.MinecraftBaseModEnglishLangProvider;
import minecraft_base_mod.datagen.lang.MinecraftBaseModGermanLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MinecraftBaseModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(MinecraftBaseModEnglishLangProvider::new);
		pack.addProvider(MinecraftBaseModGermanLangProvider::new);
		pack.addProvider(MinecraftBaseModModelProvider::new);
		pack.addProvider(MinecraftBaseModRecipeProvider:: new);
		pack.addProvider(MinecraftBaseModItemTagProvider:: new);
	}
}

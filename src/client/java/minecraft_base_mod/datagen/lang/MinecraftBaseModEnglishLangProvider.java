package minecraft_base_mod.datagen.lang;

import minecraft_base_mod.items.EnderRod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static minecraft_base_mod.items.ModItems.ENDER_ROD;

public class MinecraftBaseModEnglishLangProvider extends FabricLanguageProvider {
    public static final String LANGUAGE_CODE = "en_us";

    public MinecraftBaseModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, LANGUAGE_CODE, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ENDER_ROD, EnderRod.ENGLISH_NAME);
        translationBuilder.add(EnderRod.TOOL_TIP_TRANSLATION_KEY, EnderRod.TOOL_TIP);
    }
}

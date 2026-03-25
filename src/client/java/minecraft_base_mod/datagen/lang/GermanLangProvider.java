package minecraft_base_mod.datagen.lang;

import minecraft_base_mod.items.AbilityOrb;
import minecraft_base_mod.items.EnderRod;
import minecraft_base_mod.items.Enderite;
import minecraft_base_mod.items.EnderiteChestplate;
import minecraft_base_mod.items.SmileyFace;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static minecraft_base_mod.items.ModItems.ABILITY_ORB;
import static minecraft_base_mod.items.ModItems.ENDERITE;
import static minecraft_base_mod.items.ModItems.ENDERITE_CHESTPLATE;
import static minecraft_base_mod.items.ModItems.ENDER_ROD;
import static minecraft_base_mod.items.ModItems.SMILEY_FACE;

public class GermanLangProvider extends FabricLanguageProvider {
    public static final String LANGUAGE_CODE = "de_de";

    public GermanLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, LANGUAGE_CODE, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ENDER_ROD, EnderRod.GERMAN_NAME);
        translationBuilder.add(EnderRod.TOOL_TIP_TRANSLATION_KEY, EnderRod.TOOL_TIP);

        translationBuilder.add(ENDERITE_CHESTPLATE, EnderiteChestplate.GERMAN_NAME);
        translationBuilder.add(EnderiteChestplate.TOOL_TIP_TRANSLATION_KEY, EnderiteChestplate.GERMAN_TOOL_TIP);

        translationBuilder.add(ENDERITE, Enderite.GERMAN_NAME);

        translationBuilder.add(ABILITY_ORB, AbilityOrb.GERMAN_NAME);
        translationBuilder.add(AbilityOrb.TOOL_TIP_TRANSLATION_KEY, AbilityOrb.GERMAN_TOOL_TIP);

        translationBuilder.add(SMILEY_FACE, SmileyFace.GERMAN_NAME);
    }
}
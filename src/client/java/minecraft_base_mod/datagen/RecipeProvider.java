package minecraft_base_mod.datagen;

import minecraft_base_mod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static minecraft_base_mod.items.ModItems.ABILITY_ORB;
import static minecraft_base_mod.items.ModItems.ENDERITE;
import static minecraft_base_mod.items.ModItems.ENDERITE_CHESTPLATE;
import static minecraft_base_mod.items.ModItems.ENDER_ROD;
import static net.minecraft.world.item.Items.DIAMOND;
import static net.minecraft.world.item.Items.ENDER_PEARL;
import static net.minecraft.world.item.Items.GLOWSTONE_DUST;
import static net.minecraft.world.item.Items.STICK;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricDataOutput output,
                          CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput output) {
        return new net.minecraft.data.recipes.RecipeProvider(registryLookup, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.TOOLS, ENDER_ROD)
                        .pattern("CAC")
                        .pattern("CBC")
                        .pattern(" B ")
                        .define('A', ENDER_PEARL)
                        .define('B', STICK)
                        .define('C', GLOWSTONE_DUST)
                        .unlockedBy(getHasName(ENDER_PEARL), has(GLOWSTONE_DUST))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ENDERITE_CHESTPLATE)
                        .pattern("ABA")
                        .pattern("AAA")
                        .pattern("AAA")
                        .define('A', ENDERITE)
                        .define('B', ENDER_PEARL)
                        .unlockedBy(getHasName(ENDER_PEARL), has(ENDERITE))
                        .save(output);

                shaped(RecipeCategory.MISC, ABILITY_ORB)
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern("AAA")
                        .define('A', ENDERITE)
                        .define('B', DIAMOND)
                        .unlockedBy(getHasName(DIAMOND), has(ENDERITE))
                        .save(output);

                oreSmelting(
                        List.of(Items.GOLD_NUGGET), // Inputs
                        RecipeCategory.MISC, // Category
                        ModItems.SMILEY_FACE, // Output
                        7000f, // Experience
                        1, // Cooking time
                        "gold_to_gold" // group
                );

                oreSmelting(
                        List.of(ModItems.SMILEY_FACE), // Inputs
                        RecipeCategory.MISC, // Category
                        Items.GOLD_NUGGET, // Output
                        7000f, // Experience
                        10, // Cooking time
                        "gold_to_gold" // group
                );
            }
        };
    }

    @Override
    public String getName() {
        return "MinecraftBaseModRecipeProvider";
    }
}
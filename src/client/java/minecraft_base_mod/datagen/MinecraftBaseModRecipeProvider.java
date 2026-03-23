package minecraft_base_mod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

import static minecraft_base_mod.items.ModItems.ENDER_ROD;
import static net.minecraft.world.item.Items.ENDER_PEARL;
import static net.minecraft.world.item.Items.GLOWSTONE_DUST;
import static net.minecraft.world.item.Items.STICK;

public class MinecraftBaseModRecipeProvider extends FabricRecipeProvider {

    public MinecraftBaseModRecipeProvider(FabricDataOutput output,
                                          CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput output) {
        return new RecipeProvider(registryLookup, output) {
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
            }
        };
    }

    @Override
    public String getName() {
        return "MinecraftBaseModRecipeProvider";
    }
}
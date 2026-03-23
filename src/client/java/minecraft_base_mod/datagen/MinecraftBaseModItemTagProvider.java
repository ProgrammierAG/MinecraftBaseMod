package minecraft_base_mod.datagen;

import minecraft_base_mod.items.Enderite;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class MinecraftBaseModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public MinecraftBaseModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(Enderite.REPAIRS_ENDERITE_ARMOR)
                .add(Items.ENDER_PEARL)
                .setReplace(false);
    }
}
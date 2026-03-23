package minecraft_base_mod.datagen;

import minecraft_base_mod.items.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class MinecraftBaseModModelProvider extends FabricModelProvider {
    public MinecraftBaseModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) { }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.ENDER_ROD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENDERITE, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "MinecraftBaseModModelProvider";
    }
}
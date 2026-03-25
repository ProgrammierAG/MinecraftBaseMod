package minecraft_base_mod.items;

import minecraft_base_mod.MinecraftBaseMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class SmileyFace extends ModItem {
    public static final String NAME_ID = "smiley_face";
    public static final String ENGLISH_NAME = "Smiley Face";
    public static final String GERMAN_NAME = "Lächelndes Gesicht";

    public static final ResourceKey<Item> ITEM_KEY = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, NAME_ID));

    public SmileyFace() {
        super(new Properties().setId(ITEM_KEY));
    }

    @Override
    public ResourceKey<Item> getItemKey() {
        return ITEM_KEY;
    }
}

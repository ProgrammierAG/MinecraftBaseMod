package minecraft_base_mod.items;

import minecraft_base_mod.MinecraftBaseMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class Enderite extends ModItem{
    public static final String NAME_ID = "enderite";
    public static final String ENGLISH_NAME = "Enderite";
    public static final String GERMAN_NAME = "Enderite";

    public static final ResourceKey<Item> ITEM_KEY = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, NAME_ID));

    public static final ResourceKey<EquipmentAsset> ENDERITE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, NAME_ID));

    public static final String REPAIRS_ENDERITE_ARMOR_NAME_ID = "repairs_enderite_armor";
    public static final TagKey<Item> REPAIRS_ENDERITE_ARMOR = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, REPAIRS_ENDERITE_ARMOR_NAME_ID));

    public static final int BASE_DURABILITY = 15;

    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.BOOTS, 3
            ),
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            REPAIRS_ENDERITE_ARMOR,
            ENDERITE_ARMOR_MATERIAL_KEY
    );

    public Enderite() {
        super(new Properties().setId(ITEM_KEY));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(ModItems.ENDERITE));
    }

    @Override
    public ResourceKey<Item> getItemKey() {
        return ITEM_KEY;
    }
}

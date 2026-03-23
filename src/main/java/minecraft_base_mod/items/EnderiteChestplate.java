package minecraft_base_mod.items;

import com.mojang.serialization.Codec;
import minecraft_base_mod.MinecraftBaseMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;

public class EnderiteChestplate extends ModItem {
    public static final String NAME_ID = "enderite_chestplate";
    public static final String ENGLISH_NAME = "Enderite Chestplate";
    public static final String GERMAN_NAME = "Enderite Brustpanzer";

    public static final ResourceKey<Item> ITEM_KEY = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, NAME_ID));

    public static final String TOOL_TIP_TRANSLATION_KEY = "item." + ITEM_KEY.identifier() + ".tool_tip";
    public static final String ENGLISH_TOOL_TIP = "Enderpearls: %s";
    public static final String GERMAN_TOOL_TIP = "Enderperlen: %s";

    public static final DataComponentType<Integer> ENDER_PEARL_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, "ender_pearls"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        var enderPearls = stack.get(ENDER_PEARL_COMPONENT);

        if (enderPearls == null) return;

        textConsumer.accept(Component.translatable(TOOL_TIP_TRANSLATION_KEY, enderPearls).withStyle(ChatFormatting.WHITE));
    }

    EnderiteChestplate() {
        super(new Properties()
                .setId(ITEM_KEY)
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON)
                .humanoidArmor(Enderite.ARMOR_MATERIAL, ArmorType.CHESTPLATE)
                .durability(ArmorType.CHESTPLATE.getDurability(Enderite.BASE_DURABILITY))
        );
    }

    @Override
    public ResourceKey<Item> getItemKey() {
        return ITEM_KEY;
    }
}

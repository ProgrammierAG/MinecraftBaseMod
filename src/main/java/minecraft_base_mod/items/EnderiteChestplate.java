package minecraft_base_mod.items;

import minecraft_base_mod.MinecraftBaseMod;
import minecraft_base_mod.Utils;
import minecraft_base_mod.components.EnderPearlComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        var enderPearls = EnderPearlComponent.getData(stack).get();
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

    public static void onDamage(ItemStack stack, Entity enemy){
        System.out.println("A");
        var enderPearlsData = EnderPearlComponent.getData(stack);
        System.out.println("B");
        if (enderPearlsData.get() > 0){
            System.out.println("C");
            var worked = Utils.teleportToNearestFreeSpot(enemy, 20,50);
            System.out.println(worked+"D");
            if (worked){
                enderPearlsData.modify(-1);
            }
        }
    }

    @Override
    public ResourceKey<Item> getItemKey() {
        return ITEM_KEY;
    }
}

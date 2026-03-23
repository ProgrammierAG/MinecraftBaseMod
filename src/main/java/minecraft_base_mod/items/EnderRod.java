package minecraft_base_mod.items;

import minecraft_base_mod.MinecraftBaseMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

public class EnderRod extends ModItem {
    public static final String NAME_ID = "ender_rod";
    public static final String ENGLISH_NAME = "Ender Rod";
    public static final String GERMAN_NAME = "End-Stab";

    public static final ResourceKey<Item> ITEM_KEY = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, NAME_ID));

    public static final String TOOL_TIP_TRANSLATION_KEY = "item." + ITEM_KEY.identifier() + ".tool_tip";
    public static final String TOOL_TIP = "x: %s | y:%s | z:%s";

    public static final DataComponentType<Vec3> POSITION_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, "position"),
            DataComponentType.<Vec3>builder().persistent(Vec3.CODEC).build()
    );

    EnderRod() {
        super(new Properties().setId(ITEM_KEY).stacksTo(1).rarity(Rarity.UNCOMMON));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(ModItems.ENDER_ROD));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        var position = stack.get(POSITION_COMPONENT);

        if (position == null) return;

        textConsumer.accept(Component.translatable(TOOL_TIP_TRANSLATION_KEY, Math.floor(position.x), Math.floor(position.y), Math.floor(position.z)).withStyle(ChatFormatting.WHITE));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (player.isShiftKeyDown()) {
            return shiftUse(player, hand);
        }

        return normalUse(player, hand);
    }

    public InteractionResult normalUse(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        var positionToTeleport = stack.get(POSITION_COMPONENT);
        if (positionToTeleport == null) {
            player.displayClientMessage(Component.literal("Save position first.."), true);
            return InteractionResult.FAIL;
        }

        player.fallDistance = 0;
        player.teleportTo(positionToTeleport.x, positionToTeleport.y, positionToTeleport.z);

        return InteractionResult.SUCCESS;
    }

    public InteractionResult shiftUse(Player player, InteractionHand hand) {
        var positionToSave = player.position();

        ItemStack stack = player.getItemInHand(hand);

        stack.set(POSITION_COMPONENT, positionToSave);

        player.displayClientMessage(Component.literal(positionToSave.toString()), true);

        return InteractionResult.SUCCESS;
    }

    @Override
    public ResourceKey<Item> getItemKey() {
        return ITEM_KEY;
    }
}

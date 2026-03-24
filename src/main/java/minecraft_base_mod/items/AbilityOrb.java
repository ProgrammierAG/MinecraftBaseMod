package minecraft_base_mod.items;

import com.mojang.serialization.Codec;
import minecraft_base_mod.AbilityPoints;
import minecraft_base_mod.MinecraftBaseMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class AbilityOrb extends ModItem {
    public static final String NAME_ID = "ability_orb";
    public static final String ENGLISH_NAME = "Ability Orb";
    public static final String GERMAN_NAME = "Fähigkeitskugel";

    public static final ResourceKey<Item> ITEM_KEY = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, NAME_ID));

    public static final String TOOL_TIP_TRANSLATION_KEY = "item." + ITEM_KEY.identifier() + ".tool_tip";
    public static final String ENGLISH_TOOL_TIP = "Ability Points: %s + ?";
    public static final String GERMAN_TOOL_TIP = "Fähigkeitspunkte: %s + ?";

    public static final int MIN_VALUE = 15;
    public static final int MAX_VALUE = 200;

    public static final DataComponentType<Integer> ABILITY_POINTS_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, "ability_orb_points"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    AbilityOrb(){
        super(
                new Properties()
                        .setId(ITEM_KEY)
                        .food(new FoodProperties(0,0,true))
                        .component(ABILITY_POINTS_COMPONENT, 0)
        );
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        var savedPoints = itemStack.get(ABILITY_POINTS_COMPONENT);
        itemStack.set(ABILITY_POINTS_COMPONENT, savedPoints + 1);

        return super.mineBlock(itemStack, level, blockState, blockPos, livingEntity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        var abilityPoints = stack.get(ABILITY_POINTS_COMPONENT);
        textConsumer.accept(Component.translatable(TOOL_TIP_TRANSLATION_KEY, abilityPoints).withStyle(ChatFormatting.DARK_PURPLE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        var savedPoints = itemStack.get(ABILITY_POINTS_COMPONENT);

        savedPoints += ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE);

        AbilityPoints.get(livingEntity).modifyAbilityPoints(savedPoints);
        return super.finishUsingItem(itemStack, level, livingEntity);
    }

    @Override
    public ResourceKey<Item> getItemKey() {
        return ITEM_KEY;
    }
}

package minecraft_base_mod.components;

import com.mojang.serialization.Codec;
import minecraft_base_mod.MinecraftBaseMod;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class EnderPearlComponent {
    public static final String NAME_ID = "ender_pearls";
    public static final Integer DEFAULT_VALUE = 50;

    public static final DataComponentType<Integer> ENDER_PEARL_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, NAME_ID),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static EnderPearlComponentData getData(ItemStack target) {
        return new EnderPearlComponentData(target);
    }

    public static void initialize() {}

    public record EnderPearlComponentData(ItemStack target) {
        public Integer get() {
            return target.getOrDefault(ENDER_PEARL_COMPONENT, DEFAULT_VALUE);
        }

        public Integer modify(Integer value) {
            return target.set(ENDER_PEARL_COMPONENT, get() + value);
        }
    }
}

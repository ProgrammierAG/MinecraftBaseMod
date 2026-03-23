package minecraft_base_mod.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;

public class ModItems {
    public static final EnderRod ENDER_ROD = register(EnderRod::new);

    public static <T extends ModItem> T register(Supplier<T> itemFactory) {
        T item = itemFactory.get();

        Registry.register(BuiltInRegistries.ITEM, item.getItemKey(), item);

        return item;
    }

    public static void initialize() {}
}

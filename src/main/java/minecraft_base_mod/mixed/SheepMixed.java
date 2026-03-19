package minecraft_base_mod.mixed;

import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SheepMixed {
    static public InteractionResult onSheared(Player player, Sheep sheep){
        sheep.setSheared(true);

        var r = RandomSource.create().nextInt(16);

        ItemStack stack = new ItemStack(Items.DIAMOND, r);
        ItemEntity itemEntity = new ItemEntity(
                player.level(),
                sheep.getX(),
                sheep.getY(),
                sheep.getZ(),
                stack
        );

        player.level().addFreshEntity(itemEntity);

        return InteractionResult.SUCCESS;
    }
}

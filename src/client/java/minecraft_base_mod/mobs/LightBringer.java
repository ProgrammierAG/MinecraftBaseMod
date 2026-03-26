package minecraft_base_mod.mobs;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class LightBringer extends Phantom {
    public static final String NAME_ID = "light_bringer";

    public LightBringer(Level world) {
        this(ModEntityTypes.LIGHT_BRINGER, world);
    }

    public LightBringer(EntityType<? extends LightBringer> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public boolean doHurtTarget(ServerLevel serverLevel, Entity entity) {
        if (entity instanceof Player player) {
            Inventory inventory = player.getInventory();
            inventory.add(new ItemStack(Items.POISONOUS_POTATO));
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 3));
        }

        return super.doHurtTarget(serverLevel, entity);
    }
}

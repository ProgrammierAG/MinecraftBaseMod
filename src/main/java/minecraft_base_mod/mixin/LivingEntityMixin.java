package minecraft_base_mod.mixin;

import minecraft_base_mod.items.EnderiteChestplate;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static minecraft_base_mod.items.EnderiteChestplate.onDamage;
import static minecraft_base_mod.items.ModItems.ENDERITE_CHESTPLATE;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "doHurtEquipment", at = @At("HEAD"))
    private void doHurtEquipment(DamageSource damageSource, float f, EquipmentSlot[] equipmentSlots, CallbackInfo ci) {
        coolStuff();

        LivingEntity livingEntity = (LivingEntity) (Object) this;
        var entity = damageSource.getEntity();

        if (entity != null && livingEntity instanceof Player) {
            var stack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);

            if (stack == null || !stack.is(ENDERITE_CHESTPLATE)){
                return;
            }

            onDamage(stack, entity);
        }
    }

    private void coolStuff() {
        System.out.println("My code runs before vanilla equipment damage.");
    }
}
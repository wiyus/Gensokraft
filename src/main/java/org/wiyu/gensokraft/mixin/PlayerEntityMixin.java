package org.wiyu.gensokraft.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.wiyu.gensokraft.listeners.PlayerDeath;

import static net.minecraft.text.Text.*;


//This Mixin is meant to be used for the friend system to work correctly.
//As such, when a Friend dies, the player won't, but they'll retransform.
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type,world);
    }

    @Inject(method="damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        PlayerEntity p = (PlayerEntity) (Object) this;
        World w = p.getWorld();

        if (this.getHealth() - amount <= 0.0F) {
            PlayerDeath.onPlayerDeath(p,w);

            cir.setReturnValue(false);
        }

    }
}

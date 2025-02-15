package org.wiyu.gensokraft.items.danmaku;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


//Individual spell items are to be deprecated for a much more organized
//"wand" item per friend
public class HomingAmulet extends Item {
    public HomingAmulet(Settings s) {
        super(s);
    }

    @Override
    public ActionResult use(World w, PlayerEntity u, Hand h) {
        boolean[] exploded = {false};
        u.sendMessage(Text.of("Homing Amulet was succesfully cast."), true);
        CowEntity cow = EntityType.COW.create(w, SpawnReason.MOB_SUMMONED); //placeholder projectile
        if (cow != null) {
            cow.refreshPositionAndAngles(u.getBlockPos(), 0.0F, 0.0F);

            cow.setVelocity(u.getRotationVector().x*3, u.getRotationVector().y, u.getRotationVector().z*3);

            cow.setCustomName(Text.of("Homing Cow Amulet"));
            cow.setCustomNameVisible(true);
            w.spawnEntity(cow);
            u.playSound(SoundEvents.ENTITY_BLAZE_SHOOT);

            ServerTickEvents.START_SERVER_TICK.register(server -> {
                if (!cow.isOnGround()) {
                    return;
                } else if (!exploded[0]){
                    w.createExplosion(null, cow.getBlockX(), cow.getBlockY(), cow.getBlockZ(), 4.0F, World.ExplosionSourceType.MOB);
                    cow.remove(Entity.RemovalReason.DISCARDED);
                    exploded[0]=true;
                }
            });
        }
        return ActionResult.SUCCESS;
    }
}

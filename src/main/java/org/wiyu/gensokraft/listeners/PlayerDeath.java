package org.wiyu.gensokraft.listeners;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.wiyu.gensokraft.Gensokraft;
import org.wiyu.gensokraft.SoundManager;

public class PlayerDeath {
    public static void onPlayerDeath(PlayerEntity player, World world) {
        if (!(world instanceof ServerWorld) && !(player instanceof PlayerEntity)) return;

        ServerWorld sw = (ServerWorld) world;

        sw.playSound(null, player.getX(), player.getY(), player.getZ(), SoundManager.DEATH_SOUND_EVENT, player.getSoundCategory(), 1.0f, 1.0f);

        player.setHealth(20.0f);
        player.clearStatusEffects();

        player.sendMessage(Text.of("Sorry, you can't die."), true);

    }


}

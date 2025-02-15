package org.wiyu.gensokraft;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundManager {
    private SoundManager() {

    }
    public static final Identifier DEATH_ID = Identifier.of("gensokraft:death");
    public static SoundEvent DEATH_SOUND_EVENT = SoundEvent.of(DEATH_ID);

    public static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(Gensokraft.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, DEATH_ID, DEATH_SOUND_EVENT);
    }

    public static void initialize() {
    }
}

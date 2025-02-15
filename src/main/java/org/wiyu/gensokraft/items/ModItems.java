package org.wiyu.gensokraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.wiyu.gensokraft.items.danmaku.*;

import java.util.function.Function;

public final class ModItems {
    private ModItems() {
    }

    public static final Item HOMING_AMULET = register("homing_amulet", HomingAmulet::new, new Item.Settings().maxCount(1).rarity(Rarity.EPIC));

    private static Item register(String p, Function<Item.Settings, Item> i, Item.Settings s) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("gensokraft", p));
        return Items.register(registryKey, i, s);
    }

    public static void initialize() {
    }
}

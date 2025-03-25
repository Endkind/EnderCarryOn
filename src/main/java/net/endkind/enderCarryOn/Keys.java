package net.endkind.enderCarryOn;

import org.bukkit.NamespacedKey;

public enum Keys {
    CARRY(new NamespacedKey("endercarryOn", "carry")),
    CHEST(new NamespacedKey("endercarryon", "chest"));

    public final NamespacedKey key;

    Keys(NamespacedKey key) {
        this.key = key;
    }
}

package net.endkind.enderCarryOn;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class Keys {
    public static final NamespacedKey CARRY = new NamespacedKey("endercarryon", "carry");
    public static final NamespacedKey CHEST = new NamespacedKey("endercarryon", "chest");
    public static final NamespacedKey TRAPPED_CHEST = new NamespacedKey("endercarryon", "trapped_chest");
    public static final NamespacedKey ENDER_CHEST = new NamespacedKey("endercarryon", "ender_chest");
    public static final NamespacedKey BARREL = new NamespacedKey("endercarryon", "barrel");

    private Keys() {}

    public static NamespacedKey getItemModelKey(Material material) {
        return switch (material) {
            case Material.CHEST -> Keys.CHEST;
            case Material.TRAPPED_CHEST -> Keys.TRAPPED_CHEST;
            case Material.ENDER_CHEST -> Keys.ENDER_CHEST;
            case Material.BARREL -> Keys.BARREL;
            default -> null;
        };
    }
}

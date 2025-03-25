package net.endkind.enderCarryOn;

import net.endkind.enderCarryOn.Listener.*;
import net.endkind.enderCore.platform.papermc.EnderPlugin;

public final class EnderCarryOn extends EnderPlugin {
    @Override
    public void onPluginEnable() {
        registerListener(new OnInventoryClickListener());
        registerListener(new OnInventoryDragListener());
        registerListener(new OnPlayerDropItemListener());
        registerListener(new OnPlayerInteractListener());
        registerListener(new OnPlayerItemHeldListener());

        if (config.getBoolean("reset_walk_speed_on_join") || config.getBoolean("resource_pack.use")) {
            registerListener(new OnPlayerJoinListener(this));
        }
    }

    @Override
    public void onPluginDisable() {}

    @Override
    public void reload() {}
}

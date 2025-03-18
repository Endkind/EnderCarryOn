package net.endkind.enderCarryOn;

import net.endkind.enderCarryOn.Listener.ChestListener;
import net.endkind.enderCarryOn.Listener.OnPlayerJoinListener;
import net.endkind.enderCarryOn.Listener.ResourcePackListener;
import net.endkind.enderCore.platform.papermc.EnderPlugin;

public final class EnderCarryOn extends EnderPlugin {
    @Override
    public void onPluginEnable() {
        registerListener(new ChestListener());
        registerListener(new ResourcePackListener(this));

        if (getConfig().getBoolean("reset_walk_speed_on_join")) {
            registerListener(new OnPlayerJoinListener());
        }
    }

    @Override
    public void onPluginDisable() {}

    @Override
    public void reload() {}
}

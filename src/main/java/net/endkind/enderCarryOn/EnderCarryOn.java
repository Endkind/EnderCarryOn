package net.endkind.enderCarryOn;

import net.endkind.enderCarryOn.Listener.ChestListener;
import net.endkind.enderCarryOn.Listener.ResourcePackListener;
import net.endkind.enderCore.platform.papermc.EnderPlugin;

public final class EnderCarryOn extends EnderPlugin {
    @Override
    public void onPluginEnable() {
        registerListener(new ChestListener());
        registerListener(new ResourcePackListener(this));
    }

    @Override
    public void onPluginDisable() {}

    @Override
    public void reload() {}
}

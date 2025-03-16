package net.endkind.enderCarryOn.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.endkind.enderCarryOn.EnderCarryOn;

public class ResourcePackListener implements Listener {

    private final EnderCarryOn plugin;

    public ResourcePackListener(EnderCarryOn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("resource_pack.use")) {
            String url = plugin.getConfig().getString("resource_pack.url");
            String hash = plugin.getConfig().getString("resource_pack.hash");
            event.getPlayer().setResourcePack(url, hash, true);
        }
    }
}

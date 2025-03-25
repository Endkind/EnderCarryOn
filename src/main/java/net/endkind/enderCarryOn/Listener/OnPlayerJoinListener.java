package net.endkind.enderCarryOn.Listener;

import net.endkind.enderCarryOn.EnderCarryOn;
import net.endkind.enderCore.core.EnderLogger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinListener implements Listener {
    private final boolean reset_walk_speed_on_join;
    private final boolean resource_pack_use;
    private final String resource_pack_url;
    private final String resource_pack_hash;

    public OnPlayerJoinListener(EnderCarryOn plugin) {
        EnderLogger logger = plugin.getEnderLogger();

        FileConfiguration config = plugin.getConfig();

        this.reset_walk_speed_on_join = config.getBoolean("reset_walk_speed_on_join");

        boolean resource_pack_use = config.getBoolean("resource_pack.use");
        String resource_pack_url = config.getString("resource_pack.url");
        String resource_pack_hash = config.getString("resource_pack.hash");

        if (resource_pack_use && resource_pack_url != null && resource_pack_hash != null) {
            this.resource_pack_use = true;
            this.resource_pack_url = resource_pack_url;
            this.resource_pack_hash = resource_pack_hash;
        } else {
            this.resource_pack_use = false;
            this.resource_pack_url = null;
            this.resource_pack_hash = null;

            if (resource_pack_use) {
                logger.error("ResourcePack URL/Hash is not set!");
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (this.reset_walk_speed_on_join) {
            event.getPlayer().setWalkSpeed(0.2f);
        }

        if (this.resource_pack_use) {
            event.getPlayer().setResourcePack(this.resource_pack_url, this.resource_pack_hash, true);
        }
    }
}

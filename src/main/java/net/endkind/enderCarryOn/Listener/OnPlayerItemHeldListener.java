package net.endkind.enderCarryOn.Listener;

import net.endkind.enderCarryOn.Keys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class OnPlayerItemHeldListener implements Listener {
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        ItemStack currentItem = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
        if (currentItem != null && currentItem.hasItemMeta() && currentItem.getItemMeta().getPersistentDataContainer().has(Keys.CARRY.key, PersistentDataType.BYTE)) {
            event.setCancelled(true);
        }
    }
}

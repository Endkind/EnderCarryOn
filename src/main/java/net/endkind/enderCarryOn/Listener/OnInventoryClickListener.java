package net.endkind.enderCarryOn.Listener;

import net.endkind.enderCarryOn.Keys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;

public class OnInventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null
                && event.getCurrentItem().hasItemMeta()
                && event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(Keys.CARRY, PersistentDataType.BYTE)) {
            event.setCancelled(true);
        }
    }
}

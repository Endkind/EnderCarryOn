package net.endkind.enderCarryOn.Listener;

import net.endkind.enderCarryOn.Keys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class OnInventoryDragListener implements Listener {
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getOldCursor() != null
                && event.getOldCursor().hasItemMeta()
                && event.getOldCursor().getItemMeta().getPersistentDataContainer().has(Keys.CARRY.key, PersistentDataType.BYTE)) {
            event.setCancelled(true);
            return;
        }
        for (ItemStack item : event.getNewItems().values()) {
            if (item != null && item.hasItemMeta()
                    && item.getItemMeta().getPersistentDataContainer().has(Keys.CARRY.key, PersistentDataType.BYTE)) {
                event.setCancelled(true);
                break;
            }
        }
    }
}

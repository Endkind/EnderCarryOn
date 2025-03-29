package net.endkind.enderCarryOn.Listener;

import net.endkind.enderCarryOn.Keys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class OnPlayerDropItemListener implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if (droppedItem.hasItemMeta() && droppedItem.getItemMeta().getPersistentDataContainer().has(Keys.CARRY, PersistentDataType.BYTE)) {
            event.setCancelled(true);
        }
    }
}

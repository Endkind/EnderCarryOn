package net.endkind.enderCarryOn.Listener;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ChestListener implements Listener {

    private static final NamespacedKey CHEST_KEY = new NamespacedKey("endercarryon", "chest");

    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND
                && event.getClickedBlock() != null
                && event.getClickedBlock().getType() == Material.CHEST
                && event.getPlayer().isSneaking()
                && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {


            Chest chest = (Chest) event.getClickedBlock().getState();
            ItemStack chestItem = new ItemStack(Material.CHEST);
            BlockStateMeta meta = (BlockStateMeta) chestItem.getItemMeta();
            meta.setBlockState(chest);
            chestItem.setItemMeta(meta);

            ItemMeta itemMeta = chestItem.getItemMeta();
            itemMeta.setItemModel(CHEST_KEY);
            itemMeta.getPersistentDataContainer().set(CHEST_KEY, PersistentDataType.BYTE, (byte) 1);
            chestItem.setItemMeta(itemMeta);

            BlockBreakEvent breakEvent = new BlockBreakEvent(event.getClickedBlock(), event.getPlayer());
            event.getPlayer().getServer().getPluginManager().callEvent(breakEvent);

            if (!breakEvent.isCancelled()) {
                event.getClickedBlock().setType(Material.AIR);
                int selectedSlot = event.getPlayer().getInventory().getHeldItemSlot();
                event.getPlayer().getInventory().setItem(selectedSlot, chestItem.clone());
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (itemInHand.hasItemMeta() && itemInHand.getItemMeta().getPersistentDataContainer().has(CHEST_KEY, PersistentDataType.BYTE)) {
            event.getPlayer().setWalkSpeed(0.1f);
        } else {
            event.getPlayer().setWalkSpeed(0.2f);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if (droppedItem.hasItemMeta() && droppedItem.getItemMeta().getPersistentDataContainer().has(CHEST_KEY, PersistentDataType.BYTE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        ItemStack currentItem = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
        if (currentItem != null && currentItem.hasItemMeta() && currentItem.getItemMeta().getPersistentDataContainer().has(CHEST_KEY, PersistentDataType.BYTE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null 
                && event.getCurrentItem().hasItemMeta() 
                && event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(CHEST_KEY, PersistentDataType.BYTE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getOldCursor() != null 
                && event.getOldCursor().hasItemMeta() 
                && event.getOldCursor().getItemMeta().getPersistentDataContainer().has(CHEST_KEY, PersistentDataType.BYTE)) {
            event.setCancelled(true);
            return;
        }
        for (ItemStack item : event.getNewItems().values()) {
            if (item != null && item.hasItemMeta() 
                    && item.getItemMeta().getPersistentDataContainer().has(CHEST_KEY, PersistentDataType.BYTE)) {
                event.setCancelled(true);
                break;
            }
        }
    }
}

package net.endkind.enderCarryOn.Listener;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.endkind.enderCarryOn.Keys;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.jetbrains.annotations.NotNull;

public class OnPlayerInteractListener implements Listener {

    private final NamespacedKey key = new NamespacedKey("minecraft", "movement_speed");
    private final AttributeModifier attributeModifier = new AttributeModifier(key, -0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1);


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

            itemMeta.setItemModel(Keys.CHEST.key);
            Multimap<Attribute, AttributeModifier> itemAttributeModifiers = itemMeta.getAttributeModifiers();
            itemMeta.getPersistentDataContainer().set(Keys.CARRY.key, PersistentDataType.BYTE, (byte) 1);

            if (itemAttributeModifiers != null && itemAttributeModifiers.containsKey(Attribute.MOVEMENT_SPEED)) {
                ArrayListMultimap<Attribute, AttributeModifier> mutableModifiers = ArrayListMultimap.create(itemAttributeModifiers);
                mutableModifiers.put(Attribute.MOVEMENT_SPEED, attributeModifier);
                itemMeta.setAttributeModifiers(mutableModifiers);
            } else {
                itemMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, attributeModifier);
            }

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
}

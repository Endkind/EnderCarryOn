package net.endkind.enderCarryOn.helpers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.endkind.enderCarryOn.EnderCarryOn;
import net.endkind.enderCarryOn.Keys;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CarryHelper {
    private static final NamespacedKey key = new NamespacedKey("minecraft", "movement_speed");
    private static final AttributeModifier attributeModifier = new AttributeModifier(key, -0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1);

    private static final List<Material> allowedCarryBlocks = new ArrayList<>(Arrays.asList(
            Material.CHEST,
            Material.TRAPPED_CHEST,
            Material.ENDER_CHEST,
            Material.BARREL
    ));

    public static boolean isValidCarryAttempt(PlayerInteractEvent event) {
        Block target = event.getClickedBlock();
        Player player = event.getPlayer();

        return target != null &&
                isMainHandEmpty(player) &&
                player.isSneaking() &&
                isAllowedCarryTarget(target) &&
                canBreakBlock(player, target);
    }

    public static ItemStack getCarryBlock(Block block) {
        ItemStack item = new ItemStack(block.getType());
        ItemMeta itemMeta = item.getItemMeta();
        BlockStateMeta blockStateMeta = (BlockStateMeta) itemMeta;

        NamespacedKey itemModel = Keys.getItemModelKey(block.getType());

        blockStateMeta.setItemModel(itemModel);
        blockStateMeta.getPersistentDataContainer().set(Keys.CARRY, PersistentDataType.BYTE, (byte) 1);
        blockStateMeta.setBlockState(block.getState());

        item.setItemMeta(blockStateMeta);

        return setAttributeModifier(item, Attribute.MOVEMENT_SPEED, attributeModifier);
    }

    private static boolean isAllowedCarryTarget(Block block) {
        return allowedCarryBlocks.contains(block.getType());
    }

    private static boolean isMainHandEmpty(Player player) {
        return player.getInventory().getItemInMainHand().getType() == Material.AIR;
    }

    private static boolean canBreakBlock(Player player, Block target) {
        BlockBreakEvent breakEvent = new BlockBreakEvent(target, player);
        EnderCarryOn plugin = EnderCarryOn.getInstance();

        plugin.getServer().getPluginManager().callEvent(breakEvent);

        return !breakEvent.isCancelled();
    }

    private static ItemStack setAttributeModifier(ItemStack item, Attribute attribute, AttributeModifier modifier) {
        ItemMeta itemMeta = item.getItemMeta();
        Multimap<Attribute, AttributeModifier> itemAttributeModifiers = itemMeta.getAttributeModifiers();

        if (itemAttributeModifiers != null && itemAttributeModifiers.containsKey(attribute)) {
            ArrayListMultimap<Attribute, AttributeModifier> mutableModifiers = ArrayListMultimap.create(itemAttributeModifiers);
            mutableModifiers.put(attribute, modifier);
            itemMeta.setAttributeModifiers(mutableModifiers);
        } else {
            itemMeta.addAttributeModifier(attribute, modifier);
        }

        item.setItemMeta(itemMeta);

        return item;
    }

    private CarryHelper() {}
}

package net.endkind.enderCarryOn.Listener;

import net.endkind.enderCarryOn.helpers.CarryHelper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OnPlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent event) {
        if (CarryHelper.isValidCarryAttempt(event)) {
            Block clickedBlock = event.getClickedBlock();

            ItemStack carryBlock = CarryHelper.getCarryBlock(clickedBlock);
            Player player = event.getPlayer();

            int selectedSlot = player.getInventory().getHeldItemSlot();
            player.getInventory().setItem(selectedSlot, carryBlock);
            clickedBlock.setType(Material.AIR);
        }
    }
}

package uk.co.nikodem.dFIsopods.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFIsopods.Items.ItemUtils;

public class OnCraft implements Listener {
    @EventHandler
    public void OnCraft(CraftItemEvent event) {
        if (event.isCancelled()) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;
        ItemUtils.addUUIDIfMarked(item);
    }
}

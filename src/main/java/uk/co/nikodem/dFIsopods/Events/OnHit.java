package uk.co.nikodem.dFIsopods.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFIsopods.Items.DFMaterial;

public class OnHit implements Listener {
    @EventHandler
    public void OnHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player victim) {
            if (e.getDamager() instanceof Player attacker) {
                ItemStack weaponUsed = attacker.getInventory().getItemInMainHand();
                if (DFMaterial.Sniper.isSimilar(weaponUsed)) {
                    e.setDamage(e.getDamage() * 6942069);
                }
            }
        }
    }
}

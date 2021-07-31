package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.inventories.InitAH;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Inventories  implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof InitAH) {
            // Inventory initAh Inventory
            e.setCancelled(true);
        }
    }


}

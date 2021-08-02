package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.inventories.BuyAuctionHouse;
import indrocraft.spigot.ecnomyranks.inventories.InitAH;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Inventories  implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof InitAH) {
            // Inventory initAh Inventory
            e.setCancelled(true);
            
            if (e.getCurrentItem() == null){

            }else if (e.getCurrentItem().getType() == Material.DIAMOND){
                BuyAuctionHouse gui = new BuyAuctionHouse();
                player.openInventory(gui.getInventory());
            }else if (e.getCurrentItem().getType() == Material.HOPPER){

            }




        }
        if (e.getClickedInventory().getHolder() instanceof BuyAuctionHouse){
            e.setCancelled(true);
        }
    }


}

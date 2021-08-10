package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.inventories.InvTemplate;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class AuctionHouse implements CommandExecutor, Listener {
    public int currentInv = 0;

    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            currentInv = 0;

            InvTemplate gui = new InvTemplate(currentInv);
            player.openInventory(gui.getInventory());
        }
        return true;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getClickedInventory().getHolder() instanceof InvTemplate) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            } else if (e.getCurrentItem().getType() == Material.BARRIER){
                if (currentInv == 0) {
                    player.closeInventory();
                } else {
                    currentInv = currentInv - 1;
                    InvTemplate gui = new InvTemplate(currentInv);
                    player.openInventory(gui.getInventory());
                }
            } else if (e.getCurrentItem().getType() == Material.DIAMOND){
                InvTemplate gui = new InvTemplate(1);
                player.openInventory(gui.getInventory());
                currentInv++;
            } else if (e.getCurrentItem().getType() == Material.LECTERN){
                InvTemplate gui = new InvTemplate(2);
                player.openInventory(gui.getInventory());
                currentInv++;
            }
        }

    }
}

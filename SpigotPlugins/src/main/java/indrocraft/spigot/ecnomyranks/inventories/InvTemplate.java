package indrocraft.spigot.ecnomyranks.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InvTemplate implements InventoryHolder {
    private Inventory inv;

    public InvTemplate(int inventoryNum) {
        if (inventoryNum == 0) {
            inv = Bukkit.createInventory(this, 27, "Auction House");
            mainInv();
        } else if (inventoryNum == 1) {
            inv = Bukkit.createInventory(this, 27, "Sell an item");
            buyMenu();
        } else if (inventoryNum == 2) {
            inv = Bukkit.createInventory(this, 27, "buy an item");
            sellMenu();
        }
    }

    private void mainInv() {
        //row 1
        for (int i = 0; i < 9; i++) {
            inv.setItem(i,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        }
        inv.setItem(0, GuiUtils.guiItem(Material.BARRIER, "Exit", 1));
        //row 2
        inv.setItem(9, GuiUtils.guiItem(Material.LANTERN, "Previous", 1));
        inv.setItem(10,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        inv.setItem(11,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        inv.setItem(12, GuiUtils.guiItem(Material.DIAMOND, "Buy", 1, "Buy an item"));
        inv.setItem(13,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        inv.setItem(14, GuiUtils.guiItem(Material.LECTERN, "Sell", 1, "Sell an item"));
        inv.setItem(15,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        inv.setItem(16,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        inv.setItem(17, GuiUtils.guiItem(Material.SOUL_LANTERN, "Next", 1));
        //row 3
        for (int i = 18; i < 27; i++) {
            inv.setItem(i,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        }
        inv.setItem(21, GuiUtils.guiItem(Material.LIME_STAINED_GLASS_PANE," ",1));
        inv.setItem(23, GuiUtils.guiItem(Material.RED_STAINED_GLASS_PANE," ",1));
    }

    private void sellMenu() {
        for (int i = 0; i < 27; i++) {
            inv.setItem(i,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        }
        inv.setItem(0, GuiUtils.guiItem(Material.BARRIER, "Exit", 1));
        inv.setItem(12, GuiUtils.guiItem(Material.DIAMOND, "Buy", 1, "Buy an item"));
        inv.setItem(9, GuiUtils.guiItem(Material.LANTERN, "Previous", 1));
        inv.setItem(14, GuiUtils.guiItem(Material.LECTERN, "Sell", 1, "Sell an item"));
        inv.setItem(17, GuiUtils.guiItem(Material.SOUL_LANTERN, "Next", 1));
        inv.setItem(21, GuiUtils.guiItem(Material.LIME_STAINED_GLASS_PANE,"Confirm",1));
        inv.setItem(23, GuiUtils.guiItem(Material.RED_STAINED_GLASS_PANE,"Reject",1));
    }

    private void buyMenu() {
        for (int i = 0; i < 27; i++) {
            inv.setItem(i,GuiUtils.guiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Hover over an item",1));
        }
        inv.setItem(0, GuiUtils.guiItem(Material.BARRIER, "Exit", 1));
        inv.setItem(9, GuiUtils.guiItem(Material.LANTERN, "Previous", 1));
        inv.setItem(17, GuiUtils.guiItem(Material.SOUL_LANTERN, "Next", 1));
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}

package indrocraft.spigot.ecnomyranks.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


public class InitAH implements InventoryHolder {
    private Inventory inv;

    public InitAH() {
        inv = Bukkit.createInventory(this, 36, "Shop");//Size Multiple Of 9s
        init();
    }
    private void init() {
        ItemStack background = GuiUtils.createGuiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"Do something",1);


        for (int i = 0; i < 36; i++) {
            inv.setItem(i,background);
        }
        inv.setItem(12,GuiUtils.createGuiItem(Material.DIAMOND,"Buy",1));
        inv.setItem(12,GuiUtils.createGuiItem(Material.HOPPER,"Sell",1));



    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}

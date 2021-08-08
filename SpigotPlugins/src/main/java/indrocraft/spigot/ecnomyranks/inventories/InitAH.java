package indrocraft.spigot.ecnomyranks.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


public class InitAH implements InventoryHolder {
    private Inventory inv;

    public InitAH() {
        inv = Bukkit.createInventory(this, 36, "Auction House");//Size Multiple Of 9s
        init();
    }
    private void init() {



        //row 1
        inv.setItem(0,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(1,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(2,GuiUtils.createGuiItem(Material.BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(3,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(4,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(5,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(6,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(7,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(8,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        //row 2
        inv.setItem(9,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(10,GuiUtils.createGuiItem(Material.BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(11,GuiUtils.createGuiItem(Material.BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(12,GuiUtils.createGuiItem(Material.DIAMOND,"Buy",1));
        inv.setItem(13,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(14,GuiUtils.createGuiItem(Material.HOPPER,"Sell",1));
        inv.setItem(15,GuiUtils.createGuiItem(Material.BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(16,GuiUtils.createGuiItem(Material.BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(17,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        //row 3
        for (int i = 18; i < 27; i++) {
            inv.setItem(i,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        }
        //row 4
        inv.setItem(27,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(28,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(29,GuiUtils.createGuiItem(Material.BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(30,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(31,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(32,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(33,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));
        inv.setItem(34,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(35,GuiUtils.createGuiItem(Material.CYAN_STAINED_GLASS_PANE,"",1));



    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

}

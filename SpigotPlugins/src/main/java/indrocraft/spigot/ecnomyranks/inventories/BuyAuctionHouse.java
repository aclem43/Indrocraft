package indrocraft.spigot.ecnomyranks.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class BuyAuctionHouse implements InventoryHolder {

    private Inventory inv;

    public BuyAuctionHouse() {
        inv = Bukkit.createInventory(this, 36, "Buy - Auction House");//Size Multiple Of 9s
        init();
    }
    private void init() {


        //row 1
        inv.setItem(0,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));

        for (int i = 1; i < 8; i++) {
            inv.setItem(i,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        }

        inv.setItem(8,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        //row 2

        inv.setItem(9,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
            //Sell Items

        inv.setItem(17,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        //row 3
        inv.setItem(18,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
            //Sell Items
        inv.setItem(26,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));

        //row 4
        inv.setItem(27,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));
        inv.setItem(28,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(29,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(30,GuiUtils.createGuiItem(Material.PAPER,"Previous",1));
        inv.setItem(31,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(32,GuiUtils.createGuiItem(Material.END_CRYSTAL,"Next",1));
        inv.setItem(33,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(34,GuiUtils.createGuiItem(Material.RED_STAINED_GLASS_PANE,"",1));
        inv.setItem(35,GuiUtils.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"",1));



    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}

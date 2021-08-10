package indrocraft.spigot.ecnomyranks.inventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiUtils {
	public static ItemStack guiItem(Material material, String name, Integer num, String lore) {
		ItemStack item = new ItemStack(material, num);

		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		ArrayList<String> itemLore = new ArrayList<>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);

		item.setItemMeta(itemMeta);

		return item;
	}

	public static ItemStack guiItem(Material material, String name, Integer num) {
		ItemStack item = new ItemStack(material, num);

		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		ArrayList<String> itemLore = new ArrayList<>();
		itemMeta.setLore(itemLore);

		item.setItemMeta(itemMeta);

		return item;
	}

	public static Integer getCost() {

		return null;

	}
}

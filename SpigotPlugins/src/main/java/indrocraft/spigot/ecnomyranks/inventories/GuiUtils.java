package indrocraft.spigot.ecnomyranks.inventories;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiUtils {
	public static ItemStack createGuiItem(final Material material, final String name, Integer num,
			final String... lore) {
		final ItemStack item = new ItemStack(material, num);
		final ItemMeta meta = item.getItemMeta();

		// Set the name of the item
		meta.setDisplayName(name);

		// Set the lore of the item
		meta.setLore(Arrays.asList(lore));

		item.setItemMeta(meta);

		return item;

	}

	public static Integer getCost() {

		return null;

	}
}

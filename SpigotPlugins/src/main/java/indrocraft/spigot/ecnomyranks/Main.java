package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.Complaints;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("SpigotPlugin.Ranks-Economy Enabled");

        getServer().getPluginCommand("Complaints").setExecutor(new Complaints(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

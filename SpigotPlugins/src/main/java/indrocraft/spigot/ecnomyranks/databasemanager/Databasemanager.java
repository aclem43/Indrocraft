package indrocraft.spigot.ecnomyranks.databasemanager;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;

public class Databasemanager{

    public static FileConfiguration getFileConfig(String fileName) {
        File configFile = new File(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/EconomyRanks/"+fileName); // First we
        // will load
        // the file.
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile); // Now we will load the file into a
        // FileConfiguration.
        return config;
    }
}
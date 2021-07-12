package indrocraft.spigot.ecnomyranks.databasemanager;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.sql.DataSource;

public class Databasemanager{

    public static FileConfiguration getFileConfig() {
        File configFile = new File(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/aclem43/config.yml"); // First we
        // will load
        // the file.
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile); // Now we will load the file into a
        // FileConfiguration.
        return config;
    }

    public DataSource connectDatabase() {

        FileConfiguration configFile = getFileConfig();//loads config file

        Database database = configFile.getDatabase(); // gets database info

        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource(); //mysql databases

        dataSource.setServerName(database.getHost());
        dataSource.setPortNumber(database.getPort());
        dataSource.setDatabaseName(database.getDatabase());
        dataSource.setUser(database.getUser());
        dataSource.setPassword(database.getPassword());

        dataSource.setMaxPoolSize(8); // Default value is 8. 8 connections should be more then enough for most plugins.


        return dataSource;

    }
    private void testDataSource(DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            if (!conn.isValid(1000)) {
                throw new SQLException("Could not establish database connection.");
            }

        }


    }

    public Jdbi connectGetJDbi(){
        DataSource ds = connectDatabase();
        Jdbi jdbi = Jdbi.create(ds);
        return jdbi;
    }

    

}
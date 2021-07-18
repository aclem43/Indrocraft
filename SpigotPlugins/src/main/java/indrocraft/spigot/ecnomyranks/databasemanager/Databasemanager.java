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




        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource(); //mysql databases

        dataSource.setServerName(configFile.getString("database.host"));
        dataSource.setPortNumber(Integer.parseInt(configFile.getString("database.port")));
        dataSource.setDatabaseName(configFile.getString("database.database"));
        dataSource.setUser(configFile.getString("database.user"));
        dataSource.setPassword(configFile.getString("database.database"));

        // Default value is 8. 8 connections should be more then enough for most plugins.


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

    public void createTablesDb(Jbdi jdbi){

        jdbi.withHandle(handle -> {
            handle.execute("CREATE TABLE user (id INTEGER PRIMARY KEY, name VARCHAR)");

            // Inline positional parameters
            handle.execute("INSERT INTO user(id, name) VALUES (?, ?)", 0, "Alice");

            // Positional parameters
            handle.createUpdate("INSERT INTO user(id, name) VALUES (?, ?)")
                    .bind(0, 1) // 0-based parameter indexes
                    .bind(1, "Bob")
                    .execute();

            // Named parameters
            handle.createUpdate("INSERT INTO user(id, name) VALUES (:id, :name)")
                    .bind("id", 2)
                    .bind("name", "Clarice")
                    .execute();

            // Named parameters from bean properties
            handle.createUpdate("INSERT INTO user(id, name) VALUES (:id, :name)")
                    .bindBean(new User(3, "David"))
                    .execute();

            // Easy mapping to any type
            return handle.createQuery("SELECT * FROM user ORDER BY name")
                    .mapToBean(User.class)
                    .list();
        }
    }

}
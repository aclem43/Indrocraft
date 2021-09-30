package indrocraft.spigot.ecnomyranks.databasemanager;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLgetter {

    private Main plugin;
    public SQLgetter(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable(String name) {
        PreparedStatement ps;
        try {

            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + name + " (NAME VARCHAR(100),UUID VARCHAR(100),WARNS INT(100),RANKS VARCHAR(100),PRIMARY KEY (NAME))");

            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + name + " (NAME VARCHAR(100),UUID VARCHAR(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addcolumn(String columnName, String dataType, String tableName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("ALTER TABLE " + tableName + " ADD IF NOT EXISTS " + columnName + " " + dataType);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player, String tableName){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + tableName + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();
            if (!exists(uuid, tableName)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO " + tableName + " (NAME,UUID) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();

                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createRow(Player player, String rowName, String tableName){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + tableName + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO " + tableName + " (NAME,UUID) VALUES (?,?)");
            ps2.setString(1, rowName);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();

            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void customRow(int rowName, String tableName){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + tableName);
            ResultSet results = ps.executeQuery();
            results.next();
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO " + tableName + " (NAME) VALUE (?)");
            ps2.setInt(1, rowName);
            ps2.executeUpdate();

            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid, String tableName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + tableName + " WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if (results.next()) {
                //player is found
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setString(UUID uuid, String string, String columnName, String tableName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE UUID=?");
            ps.setString(1, string);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setString(UUID uuid, String string, String columnName, String tableName, String target) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE UUID=? AND NAME=?");
            ps.setString(1, string);
            ps.setString(2, uuid.toString());
            ps.setString(3, target);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAnyString(String string, String columnName, String tableName, String targetColumn, int targetIs) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE " + targetColumn + "=?");
            ps.setString(1, string);
            ps.setInt(2, targetIs);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAnyInt(int number, String columnName, String tableName, String targetColumn, int targetIs) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE " + targetColumn + "=?");
            ps.setInt(1, number);
            ps.setInt(2, targetIs);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInt(UUID uuid, int amount, String columnName, String tableName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE UUID=?");
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInt(UUID uuid, int amount, String columnName, String tableName, String target) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE UUID=? AND NAME=?");
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.setString(3, target);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setdouble(UUID uuid, double amount, String columnName, String tableName, String target) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE UUID=? AND NAME=?");
            ps.setDouble(1, amount);
            ps.setString(2, uuid.toString());
            ps.setString(3, target);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFloat(UUID uuid, float amount, String columnName, String tableName, String target) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + tableName + " SET " + columnName +"=? WHERE UUID=? AND NAME=?");
            ps.setFloat(1, amount);
            ps.setString(2, uuid.toString());
            ps.setString(3, target);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getInt(UUID uuid, String column, String tableName) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int info = 0;
            if (rs.next()) {
                info = rs.getInt(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getDouble(UUID uuid, String column, String tableName, String target) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE UUID=? AND NAME=?");
            ps.setString(1, uuid.toString());
            ps.setString(2, target);
            ResultSet rs = ps.executeQuery();
            double info = 0;
            if (rs.next()) {
                info = rs.getDouble(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public float getFloat(UUID uuid, String column, String tableName, String target) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE UUID=? AND NAME=?");
            ps.setString(1, uuid.toString());
            ps.setString(2, target);
            ResultSet rs = ps.executeQuery();
            float info = 0;
            if (rs.next()) {
                info = rs.getFloat(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getRecent(String column, String tableName, String idFeild) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " ORDER BY " + idFeild + " DESC LIMIT 0,1");
            ResultSet rs = ps.executeQuery();
            int info = 0;
            if (rs.next()) {
                info = rs.getInt(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getString(UUID uuid, String column, String tableName) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String info = "";
            if (rs.next()) {
                info = rs.getString(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getString(Player player, String column, String tableName, String nameIs) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE UUID=? AND NAME=?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, nameIs);
            ResultSet rs = ps.executeQuery();
            String info = "";
            if (rs.next()) {
                info = rs.getString(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getAnyString(String column, String tableName, String targetColumn, int targetIs) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE " + targetColumn + "=?");
            ps.setInt(1, targetIs);
            ResultSet rs = ps.executeQuery();
            String info = "";
            if (rs.next()) {
                info = rs.getString(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getAnyInt(String column, String tableName, String targetColumn, int targetIs) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE " + targetColumn + "=?");
            ps.setInt(1, targetIs);
            ResultSet rs = ps.executeQuery();
            int info = 0;
            if (rs.next()) {
                info = rs.getInt(column);
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // DELETE STUFF
    public void emptyTable(String tableName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE " + tableName);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String tableName, String nameEquals) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM " + tableName + " WHERE NAME=?");
            ps.setString(1, nameEquals);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //alter data type
    public void setDataType(String column, String dataType, String tableName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("ALTER TABLE " + tableName + " MODIFY " + column + " " + dataType);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

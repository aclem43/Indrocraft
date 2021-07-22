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

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS PlayerInfo " + "(NAME VARCHAR(100),UUID VARCHAR(100),WARNS INT(100),COMPLAINT VARCHAR(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM PlayerInfo WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();
            if (!exists(uuid)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO PlayerInfo (NAME,UUID) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();

                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM PlayerInfo WHERE UUID=?");
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

    public void addInt(UUID uuid, int amount, String columnName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PlayerInfo SET " + columnName +"=? WHERE UUID=?");
            ps.setInt(1, (getInt(uuid, "WARNS") + amount));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setString(UUID uuid, String string, String columnName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PlayerInfo SET " + columnName +"=? WHERE UUID=?");
            ps.setString(1, string);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInt(UUID uuid, int amount, String columnName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PlayerInfo SET " + columnName +"=? WHERE UUID=?");
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getInt(UUID uuid, String column) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM PlayerInfo WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int info = 0;
            if (rs.next()) {
                info = rs.getInt("WARNS");
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getString(UUID uuid, String column) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT " + column + " FROM PlayerInfo WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String info = "";
            if (rs.next()) {
                info = rs.getString("WARNS");
                return info;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // DELETE STUFF
    public void emptyTable() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE PlayerInfo");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM PlayerInfo WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

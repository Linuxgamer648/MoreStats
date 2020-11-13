package de.linuxgamer.morestats.mysql;

import de.linuxgamer.morestats.Stats;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLManager {
    public Stats plugin;
    public SQLManager(Stats plugin) {
        this.plugin = plugin;
    }
    public void creatTable() {
        PreparedStatement  ps;
        try {
            ps = Stats.getPlugin().sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS morestats" +
                    "(UUID VARCHAR(100),JOINS INT(100),DAMAGE DOUBLE(100),MESSAGES INT(100),DEATHS INT(100),PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void creatPlayer(Player player) {
        try {
            String string = player.getUniqueId().toString();
            /**PreparedStatement ps = Main.getPlugin().sql.getConnection().prepareStatement("SELECT * FROM basicapi WHERE UUID=?");
            ps.setString(1,string);
            ResultSet  result = ps.executeQuery();
            result.next();**/
            if(!exists(player)) {
                PreparedStatement ps1 = Stats.getPlugin().sql.getConnection().prepareStatement("INSERT IGNORE INTO morestats" +
                        " (UUID,DAMAGE,JOINS,MESSAGES,DEATHS) VALUES (?,?,?,?)");
                ps1.setString(1,string);
                ps1.setDouble(2,0);
                ps1.setInt(3,0);
                ps1.setInt(4,0);
                ps1.setInt(5,0);
                ps1.executeUpdate();
                return;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean exists(Player player) {
        try {
            PreparedStatement ps = Stats.getPlugin().sql.getConnection().prepareStatement("SELECT * FROM morestats WHERE UUID=?");
            ps.setString(1,player.getUniqueId().toString());
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
                return true;
            }else return false;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Integer getJoins(Player player) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("SELECT JOINS FROM morestats WHERE UUID=?");
            ps.setString(1,player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            Integer i;
            if (rs.next()) {
                i = rs.getInt("JOINS");
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public void setJoins(Player player) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("UPDATE morestats SET JOINS=? WHERE UUID=?");
            int joins = getJoins(player) + 1;
            ps.setInt(1,joins);
            ps.setString(2,player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Double getDamage(Player player) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("SELECT DAMAGE FROM morestats WHERE UUID=?");
            ps.setString(1,player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            Double i;
            if (rs.next()) {
                i = rs.getDouble("DAMAGE");
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public void setDamage(Player player,Double damge) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("UPDATE morestats SET DAMAGE=? WHERE UUID=?");
            Double joins = getDamage(player) + damge;
            ps.setDouble(1,joins);
            ps.setString(2,player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getMessages(Player player) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("SELECT MESSAGES FROM morestats WHERE UUID=?");
            ps.setString(1,player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            Integer i;
            if (rs.next()) {
                i = rs.getInt("MESSAGES");
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setMessages(Player player) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("UPDATE morestats SET MESSAGES=? WHERE UUID=?");
            ps.setInt(1,getMessages(player)+1);
            ps.setString(2,player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getDeaths(Player player) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("SELECT DEATHS FROM morestats WHERE UUID=?");
            ps.setString(1,player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            Integer i;
            if (rs.next()) {
                i = rs.getInt("MESSAGES");
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setDeaths(Player player) {
        try {
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("UPDATE morestats SET DEATHS=? WHERE UUID=?");
            ps.setInt(1,getMessages(player)+1);
            ps.setString(2,player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

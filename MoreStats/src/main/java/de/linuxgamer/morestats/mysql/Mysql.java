package de.linuxgamer.morestats.mysql;

import de.linuxgamer.morestats.Stats;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    public static String host = "";
    public static String port = "";
    public static String database = "";
    public static String username = "";
    public static String password ="";
    public Connection connection;
    public boolean isConnected() {
        return (connection == null ? false : true);
    }
    public void connect() throws ClassCastException, SQLException {
        if (!isConnected()) {
            FileConfiguration fl = Stats.getPlugin().getConfig();
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false" +  "              " + username + password;
            Bukkit.getLogger().info(url);
        }
    }
    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Connection getConnection() {
        return connection;
    }

}

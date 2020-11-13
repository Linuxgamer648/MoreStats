package de.linuxgamer.morestats;

import de.linuxgamer.morestats.mysql.Mysql;
import de.linuxgamer.morestats.mysql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

public class Stats extends JavaPlugin {
    public static Stats plugin;
    public Mysql sql;
    public SQLManager data;

    @Override
    public void onEnable() {
        plugin = this;
        this.sql = new Mysql();
        this.data = new SQLManager(this);
        listeners();
        commands();
        FileConfiguration fl = Stats.getPlugin().getConfig();
        if (fl.contains("host"))  {
            String host = fl.getString("host","host");
            String port =fl.getString("port","3306");
            String database =fl.getString("database","database");
            String password =fl.getString("password","password");
            String username =fl.getString("username","username");
            Mysql.host = host;
            Mysql.port = port;
            Mysql.database = database;
            Mysql.password = password;
            Mysql.username = username;
            try {
                sql.connect();
            } catch (SQLException throwables) {
                Bukkit.getLogger().info("Not Connected");
            }
            if (sql.isConnected()) {
                Bukkit.getLogger().info("Connected");
                data.creatTable();
            }
        }else {
            fl.set("host","host");
            fl.set("port","3306");
            fl.set("database","database");
            fl.set("password","password");
            fl.set("username","username");
            fl.set("text.joins","[username] has [joins] joins");
            fl.set("text.damage","[username] got [damage] damage");
            fl.set("text.messages","[username] sended [messages] messages");
            fl.set("text.deaths","[username] has [deaths] deaths");
            Bukkit.getLogger().info("");

            Stats.getPlugin().saveConfig();
            Bukkit.getLogger().info("Config seted");
        }

        
        
    }

    @Override
    public void onDisable() {
        sql.disconnect();
    }

    public static Stats getPlugin() {
        return plugin;
    }
    public void listeners() {
        PluginManager pl = Bukkit.getPluginManager();
    }
    public void commands() {


    }
    public static SQLManager getStats() {
        Stats.getPlugin().data = new SQLManager(Stats.getPlugin());
        return Stats.getPlugin().data;
    }
}

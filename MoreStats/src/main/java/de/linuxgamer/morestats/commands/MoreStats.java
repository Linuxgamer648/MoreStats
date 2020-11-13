package de.linuxgamer.morestats.commands;

import de.linuxgamer.morestats.Stats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MoreStats implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);
            FileConfiguration fl = Stats.getPlugin().getConfig();
            if (args[0].equalsIgnoreCase("joins")) {
                String joins = fl.getString("text.joins");
                if (joins.contains("[username]") && joins.contains("[joins]")) {
                    joins.replace("[username]",target.getName());
                    joins.replace("[joins]",Stats.getStats().getJoins(target).toString());
                    player.sendMessage(ChatColor.GREEN + joins);
                }
            }else if (args[0].equalsIgnoreCase("damage")) {
                String joins = fl.getString("text.damage");
                if (joins.contains("[username]") && joins.contains("[damage]")) {
                    String messages = "";
                    messages = joins.replace("[username]", target.getName());
                    joins = messages.replace("[damage]", Stats.getStats().getDamage(target).toString());
                    player.sendMessage(ChatColor.GREEN + joins);
                }
            }else if (args[0].equalsIgnoreCase("messages")) {
                String joins = fl.getString("text.messages");
                if (joins.contains("[username]") && joins.contains("[messages]")) {
                    String messages = "";
                    messages = joins.replace("[username]", target.getName());
                    joins = messages.replace("[messages]", Stats.getStats().getMessages(target).toString());
                    player.sendMessage(ChatColor.GREEN +joins);
                }
            }else if (args[0].equalsIgnoreCase("deaths")) {
                String joins = fl.getString("text.deaths");
                if (joins.contains("[username]") && joins.contains("[deaths]")) {
                    String messages = "";
                    messages = joins.replace("[username]", target.getName());
                    joins = messages.replace("[deaths]", Stats.getStats().getMessages(target).toString());
                    player.sendMessage(ChatColor.GREEN + joins);
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> lol = new ArrayList<>();
            lol.add("joins");
            lol.add("damage");
            lol.add("messages");
            lol.add("deaths");
            return lol;
        }else if (args.length ==2) {
            return null;
        }else {
            return null;
        }
    }
}

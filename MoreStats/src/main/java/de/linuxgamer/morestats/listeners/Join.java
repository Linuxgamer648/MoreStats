package de.linuxgamer.morestats.listeners;

import de.linuxgamer.morestats.Stats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Stats.getStats().creatPlayer(event.getPlayer());
        Stats.getStats().setJoins(event.getPlayer());
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Stats.getStats().setDamage((Player) event.getEntity(),event.getDamage());
        }
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Stats.getStats().setMessages(event.getPlayer());
    }
}

package ASPlugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoin) { // A player joined.
        Player player = playerJoin.getPlayer();
        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&fWelcome to the &2Usual World&r, " + player.getName()));
        playerJoin.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&5[&a+&5] " + player.getName()));
    }
}

package ASPlugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuit) { // A player quit.
        Player player = playerQuit.getPlayer();
        playerQuit.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&5[&c-&5] " + player.getName()));
    }
}

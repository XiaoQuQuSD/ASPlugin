package ASPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Flight implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command com, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isOp()) {
                player.sendMessage(ChatColor.RED + "Sorry, You don't have OP.");
                return false;
            }
            player.setAllowFlight(!player.getAllowFlight());
            if (player.getAllowFlight()) {
                player.sendMessage(ChatColor.GREEN + "You can fly now");
            } else {
                player.sendMessage(ChatColor.GREEN + "You can't fly now");
            }
        }
        return false;
    }
}

package ASPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddWayPoint implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command com, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;
        
        return false;
    }
}

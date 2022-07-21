package ASPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CopyItem implements CommandExecutor {
    private void logInfo(String msg) {
        System.out.println("[ASPlugin] -> [CopyItem]: " + msg);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command com, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player p = (Player) sender;
        if (!(p.isOp())) {
            p.sendMessage(ChatColor.RED + "Sorry, you don't have OP.");
            logInfo(p.getName() + " wants to copy item, but no OP(blocked).");
            return false;
        }
        PlayerInventory inv = p.getInventory();
        ItemStack it = inv.getItemInMainHand();
        if (it.getType() == Material.AIR) {
            p.sendMessage(ChatColor.RED + "Your Main Hand is empty now.");
            logInfo(p.getName() + " wants to copy item, but main hand is empty.");
            return false;
        }
        logInfo(p.getName() + " copied item " + it.getType() + "(*" + it.getAmount() + ")");
        inv.addItem(it);
        return false;
    }
}

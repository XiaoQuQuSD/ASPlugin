package ASPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class WayPointA {
    private Double x, y, z;
    private String name;

    public WayPointA(Double __x, Double __y, Double __z, String __name) {
        x = __x;
        y = __y;
        z = __z;
        name = __name;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
}

class UsrWayPoint {
    private WayPointA[] waypoints = new WayPointA[55];

    public UsrWayPoint(WayPointA wp) {
        waypoints[waypoints.length - 1] = wp;
    }
}

public class WayPoint implements CommandExecutor {
    Plugin plugin = ASPlugin.Main.getPlugin(ASPlugin.Main.class);

    private void logInfo(String msg) {
        System.out.println("[ASPlugin] -> [WayPoint]: " + msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command com, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(ChatColor.WHITE + "==========================");
            p.sendMessage(ChatColor.WHITE + " WayPoint Plugin(AS VER.) ");
            p.sendMessage(ChatColor.WHITE + "==========================");
            return false;
        }
        if (Objects.equals(args[0], "set")) {
            boolean newWaypoint = false;
            if (args.length <= 1) {
                p.sendMessage(ChatColor.RED + "You must provide waypoint's name.");
                return false;
            }
            String waypointName = args[1];
            String waypointPath = "WayPoints." + p.getName() + "." + waypointName;
            if (!plugin.getConfig().contains(waypointPath)) {
                newWaypoint = true;
                plugin.getConfig().createSection(waypointPath);
                List<String> wps = plugin.getConfig().getStringList("WayPoints." + p.getName() + ".plist");
                if (wps.isEmpty()) {
                    List<String> l = new ArrayList<String>(0);
                    l.add(waypointName);
                    plugin.getConfig().set("WayPoints." + p.getName() + ".plist", l);
                } else {
                    wps.add(waypointName);
                    plugin.getConfig().set("WayPoints." + p.getName() + ".plist", wps);
                }
            }
            if (newWaypoint || plugin.getConfig().getBoolean(waypointPath + ".show") == false)
                p.sendMessage(ChatColor.GREEN + "Added WayPoint (" + p.getLocation().getX() + ","
                        + p.getLocation().getY() + "," + p.getLocation().getZ() + ")");
            else
                p.sendMessage(ChatColor.GREEN + "Edited WayPoint to (" + p.getLocation().getX() + ","
                        + p.getLocation().getY() + "," + p.getLocation().getZ() + ")");
            plugin.getConfig().set(waypointPath + ".x", p.getLocation().getX());
            plugin.getConfig().set(waypointPath + ".y", p.getLocation().getY());
            plugin.getConfig().set(waypointPath + ".z", p.getLocation().getZ());
            plugin.getConfig().set(waypointPath + ".show", true);
            plugin.getConfig().set(waypointPath + ".name", waypointName);
            // plugin.getConfig().save("config.yml");
            // create a new a point.
            logInfo("Created a new waypoint for " + p.getName());
        } else if (Objects.equals(args[0], "rm") || Objects.equals(args[0], "remove")) {
            if (args.length <= 1) {
                p.sendMessage(ChatColor.RED + "You must provide waypoint's name.");
                return false;
            }
            String waypointName = args[1];
            String waypointPath = "WayPoints." + p.getName() + "." + waypointName;
            plugin.getConfig().set(waypointPath + ".show", false);
        } else if (Objects.equals(args[0], "go")) {
            if (args.length <= 1) {
                p.sendMessage(ChatColor.RED + "You must provide waypoint's name.");
                return false;
            }
            String waypointName = args[1];
            String waypointPath = "WayPoints." + p.getName() + "." + waypointName;
            if (!plugin.getConfig().contains(waypointPath)) {
                p.sendMessage(ChatColor.RED + "Waypoint " + waypointName + " doesn't exist.");
                return false;
            }
            Double x = plugin.getConfig().getDouble(waypointPath + ".x");
            Double y = plugin.getConfig().getDouble(waypointPath + ".y");
            Double z = plugin.getConfig().getDouble(waypointPath + ".z");
            Location tarLoc = new Location(p.getWorld(), x, y, z);
            p.teleport(tarLoc);
            p.sendMessage(ChatColor.GREEN + "Teleported! BYE!");
        } else if (Objects.equals(args[0], "list")) {
            List<String> l = plugin.getConfig().getStringList("Waypoints." + p.getName() + ".plist");
            if (l.isEmpty()) {
                p.sendMessage(ChatColor.RED + "Sorry, you don't have any waypoints now.");
                return false;
            }
            p.sendMessage("You have " + l.size() + " waypoint(s)"); 
            Integer i = 1;
            for (String wp : l) {
                String waypointName = wp;
                String waypointPath = "WayPoints." + p.getName() + "." + waypointName;
                Double x = plugin.getConfig().getDouble(waypointPath + ".x");
                Double y = plugin.getConfig().getDouble(waypointPath + ".y");
                Double z = plugin.getConfig().getDouble(waypointPath + ".z");
                p.sendMessage(i + ". " + waypointName + ", at (" + x + "," + y + "," + z + ").");
            }
        }
        return false;
    }
}

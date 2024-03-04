package com.ashkiano.homesplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    private final HomesPlugin plugin;

    public HomeCommand(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        FileConfiguration config = plugin.getConfig();
        String homeName = args.length > 0 ? args[0] : "default";
        String basePath = "homes." + player.getName() + "." + homeName;

        if (!config.contains(basePath)) {
            player.sendMessage(ChatColor.RED + "Home not found.");
            return true;
        }

        World world = plugin.getServer().getWorld(config.getString(basePath + ".world"));
        double x = config.getDouble(basePath + ".x");
        double y = config.getDouble(basePath + ".y");
        double z = config.getDouble(basePath + ".z");
        float yaw = (float) config.getDouble(basePath + ".yaw");
        float pitch = (float) config.getDouble(basePath + ".pitch");

        Location homeLocation = new Location(world, x, y, z, yaw, pitch);
        player.teleport(homeLocation);

        player.sendMessage(ChatColor.GREEN + "Welcome home!");
        return true;
    }
}

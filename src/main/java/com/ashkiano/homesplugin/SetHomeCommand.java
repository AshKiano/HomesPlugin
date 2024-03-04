package com.ashkiano.homesplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private final HomesPlugin plugin;

    public SetHomeCommand(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        int maxHomes = HomeUtils.getMaxHomes(player);
        FileConfiguration config = plugin.getConfig();

        // Path in the config where the player's homes are stored
        String basePath = "homes." + player.getName();

        // Check if the player already has homes set
        if (config.getConfigurationSection(basePath) != null && config.getConfigurationSection(basePath).getKeys(false).size() >= maxHomes) {
            player.sendMessage(ChatColor.RED + "You have reached your maximum number of homes.");
            return true;
        }

        Location location = player.getLocation();
        String homeName = args.length > 0 ? args[0] : "default";

        // Save the home details in the config
        config.set(basePath + "." + homeName + ".world", location.getWorld().getName());
        config.set(basePath + "." + homeName + ".x", location.getX());
        config.set(basePath + "." + homeName + ".y", location.getY());
        config.set(basePath + "." + homeName + ".z", location.getZ());
        config.set(basePath + "." + homeName + ".yaw", location.getYaw());
        config.set(basePath + "." + homeName + ".pitch", location.getPitch());
        plugin.saveConfig();

        player.sendMessage(ChatColor.GREEN + "Home set successfully.");
        return true;
    }
}

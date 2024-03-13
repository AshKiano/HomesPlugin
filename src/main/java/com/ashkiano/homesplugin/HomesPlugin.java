package com.ashkiano.homesplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class HomesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Command registration
        this.getCommand("sethome").setExecutor(new SetHomeCommand(this));
        this.getCommand("home").setExecutor(new HomeCommand(this));

        // Listener registration if needed
        // getServer().getPluginManager().registerEvents(new SomeListener(), this);

        // Configuration loading
        saveDefaultConfig();

        Metrics metrics = new Metrics(this, 21220);
        this.getLogger().info("Thank you for using the HomesPlugin plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    @Override
    public void onDisable() {
        // Saving data before plugin shutdown if needed
    }
}

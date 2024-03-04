package com.ashkiano.homesplugin;

import org.bukkit.entity.Player;

public class HomeUtils {

    public static int getMaxHomes(Player player) {
        for (int i = 10; i > 0; i--) {
            if (player.hasPermission("homes.limit." + i)) {
                return i;
            }
        }
        return 0; // Default number of homes if the player has no specific permissions
    }
}
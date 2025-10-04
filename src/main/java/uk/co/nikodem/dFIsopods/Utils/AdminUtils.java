package uk.co.nikodem.dFIsopods.Utils;

import org.bukkit.entity.Player;

public class AdminUtils {
    public static boolean isAdmin(Player plr) {
        return plr.isOp();
    }
}

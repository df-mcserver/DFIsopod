package uk.co.nikodem.dFIsopods.Commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFIsopods.Items.DFMaterial;
import uk.co.nikodem.dFIsopods.Utils.AdminUtils;

public class AdminTools implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player plr) {
            if (AdminUtils.isAdmin(plr)) {
                // give tools
                giveItems(plr,
                        DFMaterial.MagicMirror,
                        DFMaterial.KnockbackStick,
                        DFMaterial.Sniper);
                return true;
            }
        }
        commandSender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Improper permissions!"));
        return true;
    }

    public void giveItems(Player plr, DFMaterial... items) {
        for (DFMaterial item : items) {
            plr.getInventory().addItem(item.toItemStack());
        }
    }
}

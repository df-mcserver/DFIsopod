package uk.co.nikodem.dFIsopods.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFIsopods.Items.DFMaterial;
import uk.co.nikodem.dFIsopods.Items.ItemUtils;

import java.util.Objects;

public class GiveDF implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player plr) {
            if (strings.length == 1) {
                DFMaterial mat = null;
                for (DFMaterial dfMaterial : DFMaterial.allDFMaterials) {
                    if (Objects.equals(dfMaterial.getNamedId(), strings[0])) {
                        mat = dfMaterial;
                        break;
                    }
                }

                if (mat == null) {
                    commandSender.sendMessage("Invalid DFMaterial!");
                } else {
                    ItemStack item = mat.toItemStack();
                    ItemUtils.addUUIDIfMarked(item);
                    plr.getInventory().addItem(item);
                }
            } else {
                commandSender.sendMessage("Invalid argument size! Required 1.");
            }
        } else {
            commandSender.sendMessage("You are not a player!");
        }
        return true;
    }
}

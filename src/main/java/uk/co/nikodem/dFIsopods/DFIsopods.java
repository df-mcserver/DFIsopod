package uk.co.nikodem.dFIsopods;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.nikodem.dFIsopods.Commands.AdminTools;
import uk.co.nikodem.dFIsopods.Commands.GiveDF;
import uk.co.nikodem.dFIsopods.Events.OnCraft;
import uk.co.nikodem.dFIsopods.Events.OnHit;

import java.util.Objects;

public final class DFIsopods extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("givedf")).setExecutor(new GiveDF());
        Objects.requireNonNull(getCommand("admintools")).setExecutor(new AdminTools());

        getServer().getPluginManager().registerEvents(new OnHit(), this);
        getServer().getPluginManager().registerEvents(new OnCraft(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

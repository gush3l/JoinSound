package me.gush3l.joinsound;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

@SuppressWarnings({"all"})
public final class JoinSound extends JavaPlugin {

    private static JoinSound instance;

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            getConfig().set("Config.Compatibility.PlaceholderAPI",true);
            getLogger().log(Level.INFO,"PlaceholderAPI detected! You will be able to parse PAPI placeholders for messages!");
            saveConfig();
            reloadConfig();
        }
        else{
            getConfig().set("Config.Compatibility.PlaceholderAPI",false);
            getLogger().log(Level.INFO,"PlaceholderAPI not detected! You will be able to only use %player% in messages to get the player's name!");
            saveConfig();
            reloadConfig();
        }
        this.getCommand("joinsound").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {

    }

    public static JoinSound getInstance(){
        return instance;
    }

}

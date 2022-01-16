package me.gush3l.joinsound;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@SuppressWarnings({"all"})
public class Util {

    public static String color(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    public static String placeholders(String text, Player player){
        FileConfiguration config = JoinSound.getInstance().getConfig();
        if (config.getBoolean("Config.Compatibility.PlaceholderAPI")){
            return PlaceholderAPI.setPlaceholders(player,text);
        }
        else{
            return text.replace("%player%",player.getName());
        }
    }

    public static String msg(String text, Player player){
        return placeholders(color(text),player);
    }

}

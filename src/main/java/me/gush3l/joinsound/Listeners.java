package me.gush3l.joinsound;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

@SuppressWarnings({"all"})
public class Listeners implements Listener {

    @EventHandler
    public void onResourcePack(PlayerResourcePackStatusEvent event){
        FileConfiguration config = JoinSound.getInstance().getConfig();
        if (!config.getBoolean("Config.Require-Server-Resource-Pack")) return;
        if (event.getStatus().equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)){
            Bukkit.getScheduler().scheduleSyncDelayedTask(JoinSound.getInstance(), () -> {
                event.getPlayer().playSound(event.getPlayer().getLocation(),
                        config.getString("Config.Join-Sound.Sound-Name"),
                        config.getInt("Config.Join-Sound.Volume"),
                        config.getInt("Config.Join-Sound.Pitch"));
            }, config.getInt("Config.Delay-To-Send-Sound"));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        FileConfiguration config = JoinSound.getInstance().getConfig();
        if (config.getBoolean("Config.Join-Message.Enable")){
            if (config.getBoolean("Config.Join-Message.Require-Permission")){
                if (event.getPlayer().hasPermission("joinsound.joinmessage")){
                    event.setJoinMessage(Util.msg(config.getString("Messages.Join-Message"), event.getPlayer()));
                }
            }
            else{
                event.setJoinMessage(Util.msg(config.getString("Messages.Join-Message"), event.getPlayer()));
            }
        }
        if (config.getBoolean("Config.Require-Server-Resource-Pack")) return;
        Bukkit.getScheduler().scheduleSyncDelayedTask(JoinSound.getInstance(), () -> {
            event.getPlayer().playSound(event.getPlayer().getLocation(),
                    config.getString("Config.Join-Sound.Sound-Name"),
                    config.getInt("Config.Join-Sound.Volume"),
                    config.getInt("Config.Join-Sound.Pitch"));
        }, config.getInt("Config.Delay-To-Send-Sound"));
    }

}

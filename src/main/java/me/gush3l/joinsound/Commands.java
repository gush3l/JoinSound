package me.gush3l.joinsound;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@SuppressWarnings({"all"})
public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = JoinSound.getInstance().getConfig();
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("reload")){
                if (!sender.hasPermission("joinsound.reload")){
                    if (sender instanceof Player) sender.sendMessage(Util.msg(config.getString("Messages.No-Permission"), (Player) sender));
                    else sender.sendMessage(Util.color(config.getString("Messages.No-Permission")));
                    return true;
                }
                JoinSound.getInstance().reloadConfig();
                if (sender instanceof Player) sender.sendMessage(Util.msg(config.getString("Messages.Reload"), (Player) sender));
                else sender.sendMessage(Util.color(config.getString("Messages.Reload")));
                return true;
            }
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("send")){
                if (!sender.hasPermission("joinsound.send")){
                    if (sender instanceof Player) sender.sendMessage(Util.msg(config.getString("Messages.No-Permission"), (Player) sender));
                    else sender.sendMessage(Util.color(config.getString("Messages.No-Permission")));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target == null){
                    if (sender instanceof Player) sender.sendMessage(Util.msg(config.getString("Messages.Messages.Send-Sound.Invalid-Target"), (Player) sender));
                    else sender.sendMessage(Util.color(config.getString("Messages.Send-Sound.Invalid-Target")));
                    return true;
                }
                target.playSound(target.getLocation(),
                        config.getString("Config.Join-Sound.Sound-Name"),
                        config.getInt("Config.Join-Sound.Volume"),
                        config.getInt("Config.Join-Sound.Pitch"));
                if (sender instanceof Player) sender.sendMessage(Util.msg(config.getString("Messages.Send-Sound.Sender"), (Player) sender));
                else sender.sendMessage(Util.color(config.getString("Messages.Send-Sound.Sender")));
                target.sendMessage(Util.msg(config.getString("Messages.Send-Sound.Target.Message"),target));
                return true;
            }
        }
        if (sender instanceof Player) sender.sendMessage(Util.msg(config.getString("Messages.Unknown-Command"), (Player) sender));
        else sender.sendMessage(Util.color(config.getString("Messages.Unknown-Command")));
        return true;
    }

}

package com.github.shin_ideal.worldresetplugin.CommandExecutors;

import com.github.shin_ideal.worldresetplugin.WorldResetPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetWorldCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/" + label + " [WorldName]");
            return true;
        } else if (args.length == 1) {
            if (Bukkit.getWorld(args[0]) != null) {
                boolean worldResetFlag = WorldResetPlugin.getInstance().getWorldManage().resetWorld(args[0]);
                if (worldResetFlag) {
                    sender.sendMessage("World Reset Succeeded");
                    return true;
                } else {
                    sender.sendMessage("World Reset Failed");
                    return false;
                }
            } else {
                sender.sendMessage("Not Found World Error");
                return false;
            }
        } else {
            sender.sendMessage("Syntax Error");
            sender.sendMessage("/" + label + " [WorldName]");
            return false;
        }
    }
}

package com.falkon.guardians.commands;

import com.falkon.guardians.Guardians;
import com.falkon.guardians.entity.GuardianEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Giant;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class GameCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("test")) {
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                Guardians.getGuardians().sendScoreboard(player1,1);
            }
            GuardianEntity.spawn();
        }

        return false;

    }
}

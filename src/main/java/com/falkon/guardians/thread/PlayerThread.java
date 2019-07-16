package com.falkon.guardians.thread;

import com.falkon.guardians.Guardians;
import com.falkon.guardians.entity.GuardianEntity;
import com.falkon.guardians.misc.BlockData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerThread extends BukkitRunnable {

    public int level = 1;
    public int bre = 0;
    public boolean changing=false;

    @Override
    public void run() {
        if (GuardianEntity.getIronGolem() != null) {
            Location location = GuardianEntity.getIronGolem().getLocation().subtract(0,1,0);
            String health = GuardianEntity.getIronGolem().getHealth() + "";
            String h = health.substring(0,1);
            String suffix = ChatColor.GRAY + " - " + ChatColor.GOLD + "Level: " + ChatColor.RED + level;

            if (!health.substring(1,2).equalsIgnoreCase("."))h=h+health.substring(1,2);
            if (!health.substring(2,3).equalsIgnoreCase("."))h=h+health.substring(2,3);

            GuardianEntity.getIronGolem().setCustomName(ChatColor.DARK_RED + "♥ " + ChatColor.RED + h);
            GuardianEntity.getIronGolem().setCustomNameVisible(true);

            if (location.clone().subtract(0, 1, 0).getBlock().getType() == Material.GOLD_BLOCK) {
                if (level == 2) {
                    level=3;
                    GuardianEntity.move(GuardianEntity.getIronGolem(),new Location(Bukkit.getWorld("arena"),3380, 6, 1091),1);
                }
            }
            if (location.clone().subtract(0, 1, 0).getBlock().getType() == Material.DIAMOND_BLOCK) {
                if (level == 3) {
                    level=4;
                    GuardianEntity.move(GuardianEntity.getIronGolem(),new Location(Bukkit.getWorld("arena"),3381, 6, 1025),1);
                }
            }

            if (location.getBlock().getType() == Material.SLIME_BLOCK) {
                if (!changing) {
                    changing=true;
                }
                if (location.clone().subtract(0, 1, 0).getBlock().getType() == Material.WALL_SIGN) {
                    Sign sign = (Sign) location.clone().subtract(0,1,0).getBlock().getState();

                    if (level == 1 && sign.getLine(0).contains("1")) {
                        if (bre < 210) {
                            bre=bre+1;
                        }

                        if (bre == 200) {
                            level=level+1;
                            bre=0;
                            GuardianEntity.move(GuardianEntity.getIronGolem(),new Location(Bukkit.getWorld("arena"),3340, 6, 1151),1);
                        }
                    }
                }


            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "Guardian Health: " + ChatColor.DARK_RED + "♥ " + ChatColor.RED + h + suffix));
            }
        }
    }
}

package com.falkon.guardians.event;

import com.falkon.guardians.Guardians;
import com.falkon.guardians.misc.CItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Guardians.getGuardians().sendScoreboard(player,0);
        player.teleport(new Location(Bukkit.getWorld("arena"),3278.5, 6, 1136.5));
        player.sendMessage(ChatColor.GREEN + "Welcome to Guardians!");
        player.getInventory().clear();
        CItem kit = new CItem(ChatColor.GREEN + "Kits", Material.PAPER);
        kit.addLore(ChatColor.GRAY + "Click to open");
        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(20);
        player.setHealth(player.getMaxHealth());
        player.getInventory().setItem(0,kit.getItemStack());
    }
}

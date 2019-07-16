package com.falkon.guardians;

import com.falkon.guardians.commands.GameCommands;
import com.falkon.guardians.entity.GuardianEntity;
import com.falkon.guardians.event.ArrowEvent;
import com.falkon.guardians.event.JoinEvent;
import com.falkon.guardians.game.GameState;
import com.falkon.guardians.misc.BlockData;
import com.falkon.guardians.thread.PlayerThread;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class Guardians extends JavaPlugin {

    private static Guardians guardians;
    private static GameState gameState;
    private static Map<Location,BlockData> blockDataMap;

    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        guardians=this;
        pluginManager=Bukkit.getPluginManager();

        gameState=GameState.LOBBY;

        blockDataMap=new HashMap<>();

        getCommand("test").setExecutor(new GameCommands());

        pluginManager.registerEvents(new JoinEvent(),this);
        pluginManager.registerEvents(new ArrowEvent(),this);

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this,new PlayerThread(),2L,2L);
    }

    @Override
    public void onDisable() {

        for (Location location : getBlockDataMap().keySet()) {
            location.getBlock().setTypeIdAndData(getBlockDataMap().get(location).getMaterial().getId(),getBlockDataMap().get(location).getData(),false);
        }

        if (GuardianEntity.getIronGolem() != null) {
            GuardianEntity.getIronGolem().remove();
        }
    }

    public static Map<Location, BlockData> getBlockDataMap() {
        return blockDataMap;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        Guardians.gameState = gameState;
    }

    public static Guardians getGuardians() {
        return guardians;
    }

    public void sendScoreboard(Player player, int state) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sidebar","dummy");
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Guardians");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (state == 0) {
            Score st = objective.getScore(ChatColor.GREEN + "State: " + ChatColor.WHITE + "Lobby");
            st.setScore(10);
            Score map = objective.getScore(ChatColor.GREEN + "Map: " + ChatColor.WHITE + "Cartagena");
            map.setScore(9);
            Score players = objective.getScore(ChatColor.GREEN + "Players: " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size());
            players.setScore(8);
        } else if (state == 1) {
            Score gScore = objective.getScore(ChatColor.GREEN + "Protector Score: " + ChatColor.WHITE + "0");
            gScore.setScore(10);
            Score aScore = objective.getScore(ChatColor.GREEN + "Attacker Score: " + ChatColor.WHITE + "0");
            aScore.setScore(9);
        }

        player.setScoreboard(scoreboard);
    }
}

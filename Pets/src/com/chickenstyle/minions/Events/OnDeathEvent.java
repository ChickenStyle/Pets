package com.chickenstyle.minions.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Utils;

public class OnDeathEvent implements Listener{
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		if (Main.spawnedPet.containsKey(player.getUniqueId())) {
			player.sendMessage(ChatColor.RED +
			Utils.Color(Main.spawnedPet.get(player.getUniqueId()).getName()) +
			ChatColor.RED + " pet despawned!");
			Bukkit.getScheduler().cancelTask(Main.taskID.get(player.getUniqueId()));
			Main.spawnedPet.remove(player.getUniqueId());
			Main.stands.get(player.getUniqueId()).remove();
			Main.stands.remove(player.getUniqueId());
		}
	}
}

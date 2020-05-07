package com.chickenstyle.minions.Abilities;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Enums.PetType;



public class OnMove implements Listener{
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (Main.spawnedPet.containsKey(e.getPlayer().getUniqueId())) {
			if (Main.spawnedPet.get(e.getPlayer().getUniqueId()).getType().equals(PetType.MR_PENGUIN)) {
				if (e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType().equals(Material.ICE)
			|| e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType().equals(Material.PACKED_ICE)) {
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,40,1));
				}
				
			}
		}
	}
}

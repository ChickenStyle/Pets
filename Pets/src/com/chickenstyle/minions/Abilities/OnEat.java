package com.chickenstyle.minions.Abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.PetType;

public class OnEat implements Listener{
	@EventHandler
	public void onEat(PlayerItemConsumeEvent e) {
		if (e.getItem().getType().isEdible()) {
			Player player = e.getPlayer();
			if (Main.spawnedPet.containsKey(player.getUniqueId())) {
				ValidPet pet = Main.spawnedPet.get(player.getUniqueId());
				if (pet.getType().equals(PetType.GARFIELD)) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,0));
				}
			}
		}
	}

}

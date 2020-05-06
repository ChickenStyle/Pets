package com.chickenstyle.minions.Abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.chickenstyle.minions.CustomEvents.SpawnPetEvent;

public class OnPetSpawn implements Listener {
	@EventHandler
	public void onInventoryClick(SpawnPetEvent e) {
		Player player = e.getPlayer();
		switch (e.getPetType()) {
		case RABBIT:
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
		break;
		
		case SQUID:
			player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
		break;
		
		case PUFFERFISH:
			player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
		break;
		
		case SILVERFISH:
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
		break;
		
		case GODZILLA:
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
		break;
		
		case MYSTERY_MAGMA_SLIME:
			player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
		break;
		
		case FIRE_DEMON:
			player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
		break;
		default:
		}
	}

}

package com.chickenstyle.minions.Abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

import com.chickenstyle.minions.CustomEvents.DespawnPetEvent;

public class OnPetDespawn implements Listener{
	@EventHandler
	public void onPetDespawn(DespawnPetEvent e) {
		Player player = e.getPlayer();
		switch (e.getPetType()) {
		case RABBIT:
			player.removePotionEffect(PotionEffectType.SPEED);
			player.removePotionEffect(PotionEffectType.FAST_DIGGING);
		break;
		
		case SQUID:
			player.removePotionEffect(PotionEffectType.WATER_BREATHING);
		break;
		
		case PUFFERFISH:
			player.removePotionEffect(PotionEffectType.WATER_BREATHING);
		break;
		
		case SILVERFISH:
			player.removePotionEffect(PotionEffectType.FAST_DIGGING);
		break;
		
		case GODZILLA:
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		break;
		
		case MYSTERY_MAGMA_SLIME:
			player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		break;
		
		case FIRE_DEMON:
			player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		break;
		default:
		}
	}
}

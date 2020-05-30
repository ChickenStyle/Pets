package com.chickenstyle.minions.Abilities;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Enums.PetType;

public class OnAgro implements Listener {
	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		if (e.getEntity().getType().equals(EntityType.ENDERMAN)) {
			if (e.getTarget() instanceof Player) {
				Player player = (Player) e.getTarget();
				if (Main.spawnedPet.containsKey(player.getUniqueId())) {
					if (Main.spawnedPet.get(player.getUniqueId()).getType().equals(PetType.ENDER_KNIGHT)) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

}

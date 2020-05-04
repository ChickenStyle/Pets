package com.chickenstyle.minions.PetUtils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.chickenstyle.minions.Main;

public class PetsAnimate extends BukkitRunnable {

	ArmorStand stand;
	Player player;

	
	public PetsAnimate(Player player,ItemStack head) {
		stand = player.getWorld().spawn(player.getLocation().clone(), ArmorStand.class);
		stand.setGravity(false);
		stand.setSmall(true);
		stand.setInvulnerable(true);
		stand.setVisible(false);
		stand.setHelmet(head);
		this.player = player;
		Main.Stands.put(player.getUniqueId(), stand);
	}
	
	@Override
	public void run() {
		
		Location loc = player.getLocation().clone();

		final float newX = (float)(loc.getX() + (-0.9 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 0))));
		final float newY = (float) (loc.getY() + 1);
		final float newZ = (float)(loc.getZ() + (-0.9 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 0))));
		loc.setX(newX);
		loc.setY(newY);
		loc.setZ(newZ);
		stand.teleport(loc);
		//stand.getLocation().setDirection(player.getLocation().getDirection());
	}

	

	
}

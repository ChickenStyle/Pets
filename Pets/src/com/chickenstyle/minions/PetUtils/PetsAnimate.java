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
		Location Standloc = player.getLocation().clone();

		final float newX = (float)(Standloc.getX() + (-0.9 * Math.cos(Math.toRadians(Standloc.getYaw() + 90 * 0))));
		final float newY = (float) (Standloc.getY() + 1);
		final float newZ = (float)(Standloc.getZ() + (-0.9 * Math.sin(Math.toRadians(Standloc.getYaw() + 90 * 0))));
		Standloc.setX(newX);
		Standloc.setY(newY);
		Standloc.setZ(newZ);
		stand = player.getWorld().spawn(Standloc, ArmorStand.class);
		stand.setGravity(false);
		stand.setSmall(true);
		stand.setInvulnerable(true);
		stand.setVisible(false);
		stand.setHelmet(head);
		this.player = player;
		Main.stands.put(player.getUniqueId(), stand);
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

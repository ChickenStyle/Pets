package com.chickenstyle.minions.PetUtils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Utils;

public class PetsAnimate extends BukkitRunnable {

	ArmorStand stand;
	Player player;
	boolean up = true;
	
	public PetsAnimate(Player player,ItemStack head) {
		//ArmorStand Stuff
		Location standLoc = player.getLocation().clone();
		final float newX = (float)(standLoc.getX() + (-0.9 * Math.cos(Math.toRadians(standLoc.getYaw() + 90 * 0))));
		final float newY = (float) (standLoc.getY() + 1);
		final float newZ = (float)(standLoc.getZ() + (-0.9 * Math.sin(Math.toRadians(standLoc.getYaw() + 90 * 0))));
		standLoc.setX(newX);
		standLoc.setY(newY);
		standLoc.setZ(newZ);
		stand = player.getWorld().spawn(standLoc, ArmorStand.class);
		stand.setGravity(false);
		stand.setSmall(true);
		stand.setInvulnerable(true);
		stand.setVisible(false);
		stand.setHelmet(head);
		stand.setCustomName(Utils.Color(head.getItemMeta().getDisplayName()));
		stand.setCustomNameVisible(true);
		standLoc.getWorld().spawnParticle(Particle.TOTEM,standLoc.clone().add(0,1,0),250,0,0,0,0.2);
		
		
		//Other cool Stuff
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
		
	}

	

	
}

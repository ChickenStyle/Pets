package com.chickenstyle.minions.Abilities;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.ValidPet;


public class OnRightClick implements Listener {
	HashMap<UUID,Long> cooldown = new HashMap<>();
	
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (Main.spawnedPet.containsKey(player.getUniqueId())) {
			if (player.getInventory().getItemInMainHand().getType().toString().endsWith("SWORD")) {
				ValidPet pet = Main.spawnedPet.get(player.getUniqueId());
				if (cooldown.containsKey(player.getUniqueId())) {
					if (System.currentTimeMillis() < cooldown.get(player.getUniqueId())) {
						long whenItEnds = cooldown.get(player.getUniqueId());
						long now2 = System.currentTimeMillis();
						long difference = whenItEnds - now2;
						
						int differenceInSeconds = (int) (difference / 1000);
						player.sendMessage(ChatColor.RED + "You must wait " + differenceInSeconds + " seconds before using this ability again!");
					return;
					}
				}
				long now = System.currentTimeMillis();
				long tenMillis = 10 * 1000;
				long nowPlusTen = now + tenMillis;
				
				cooldown.put(player.getUniqueId(), nowPlusTen);
				switch (pet.getType()) {
				case ENDER_KNIGHT:
						if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
							Location loc = player.getLocation();
							Vector dir = loc.getDirection();
							dir.normalize();
							dir.multiply(5);
							loc.add(dir);
							player.teleport(loc.add(0,1,0));	
							player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
						}
					
				break;
				
				case VAMPIRE:
						if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 140, 0));
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 140, 1));
							player.playSound(player.getLocation(), Sound.ENTITY_BAT_AMBIENT, 1f, 1f);
						}
					
				break;
				
				case FIRE_DEMON:

						if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
						    Location eye = player.getEyeLocation();
						    Location loc = eye.add(eye.getDirection().multiply(1.2));
						    Fireball fireball = (Fireball) loc.getWorld().spawnEntity(loc, EntityType.FIREBALL);
						    fireball.setVelocity(loc.getDirection().normalize().multiply(2));
						    fireball.setShooter(player);
						    fireball.setIsIncendiary(false);

						}
					
				break;
				
				case WITHER_SKELETON_KING:
						if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
						    Location eye = player.getEyeLocation();
						    Location loc = eye.add(eye.getDirection().multiply(1.2));
						    WitherSkull fireball = (WitherSkull) loc.getWorld().spawnEntity(loc, EntityType.WITHER_SKULL);
						    fireball.setVelocity(loc.getDirection().normalize().multiply(2));
						    fireball.setShooter(player);
						}
				break;
				
				default:
				}
			}
		}
	}
}

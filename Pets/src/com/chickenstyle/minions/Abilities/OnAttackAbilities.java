package com.chickenstyle.minions.Abilities;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.Tier;

public class OnAttackAbilities implements Listener{
	@EventHandler
	public void onDamageEvent(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player player = (Player) e.getDamager();
			if (Main.spawnedPet.containsKey(player.getUniqueId())) {
				ValidPet pet = Main.spawnedPet.get(player.getUniqueId());
				double damage = e.getDamage();
				switch (Main.spawnedPet.get(player.getUniqueId()).getType()) {
				case ZOMBIE:
					if (player.getInventory().getItemInMainHand().getType().toString().endsWith("Sword")) {
						e.setDamage(damage + (Utils.formula(5, pet.getLevel(), Tier.COMMON)/100) * damage);
					}
				break;
				
				case SKELETON:
					if (player.getInventory().getItemInMainHand().getType().toString().endsWith("Bow")) {
						e.setDamage(damage + (Utils.formula(5, pet.getLevel(), Tier.COMMON)/100) * damage);
					}
				break;
				
				case WOLF:
					if (e.getEntity() instanceof Monster || e.getEntity() instanceof Slime) {
						e.setDamage(damage + (Utils.formula(7, pet.getLevel(), Tier.COMMON)/100) * damage);
					}
				break;
				
				case GODZILLA:
					float random = ThreadLocalRandom.current().nextFloat();
					if (random <= (Utils.formula(7, pet.getLevel(), Tier.RARE)/100)) {
						e.getEntity().setVelocity(new Vector(0, 2, 0));
						e.getEntity().setFallDistance(-100.0F);
					}
				break;
				
				case PUFFERFISH:
					float Prandom = ThreadLocalRandom.current().nextFloat();
					if (Prandom <= Utils.formula(10, pet.getLevel(), Tier.RARE)/100) {
						if (e.getEntity() instanceof LivingEntity) {
							LivingEntity entity = (LivingEntity) e.getEntity();
							entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 140, 0));
						}
					}
				break;
				case WITHER_SKELETON:
					float wRandom = ThreadLocalRandom.current().nextFloat();
					if (wRandom <= (Utils.formula(15, pet.getLevel(), Tier.EPIC)/100)) {
						if (e.getEntity() instanceof LivingEntity) {
							LivingEntity entity = (LivingEntity) e.getEntity();
							entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0));
						}
					}
				break;
				
				case PIKACHU:
					float pRandom = ThreadLocalRandom.current().nextFloat();	
					if (pRandom <= (Utils.formula(10, pet.getLevel(), Tier.EPIC)/100)) {
						e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
					}
				break;
				
				case REAPER:
					e.setDamage(damage + (Utils.formula(10, pet.getLevel(), Tier.LEGENDARY)/100) * damage);
				break;
				
				case VAMPIRE:
					if (player.getHealth() <= 20 - (Utils.formula(0, pet.getLevel(), Tier.LEGENDARY)/100) * 20) {
						player.setHealth(player.getHealth() + (Utils.formula(0, pet.getLevel(), Tier.LEGENDARY)/100) * 20);
					} else {
						player.setHealth(20);
					}
				break;
				
				case FIRE_DEMON:
					e.setDamage(damage + (Utils.formula(10, pet.getLevel(), Tier.LEGENDARY)/100) * damage);
				break;
				
				case WITHER_SKELETON_KING:
					if (e.getEntity() instanceof LivingEntity) {
						LivingEntity entity = (LivingEntity) e.getEntity();
						entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 0));
					}
				break;
				
				default:
										
				}
				
				
			}
		}
	}
}

package com.chickenstyle.minions.PetUtils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.PetType;
import com.chickenstyle.minions.Enums.Tier;

public class CrateAnimation extends BukkitRunnable {

	ArmorStand stand;
	Player player;
	Location loc;
	Tier tier;
	int ticks = 0;
	int newTicks = 0;
	boolean runOnce = false;
	PetType pet;
	
	public CrateAnimation(Tier tier,Location loc,Player player) {
		loc.add(0.5,0,0.5);
		ItemStack head = Utils.createCustomSkull("123", tier.getTexture());
		stand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setGliding(false);
		stand.setSmall(true);
		stand.setInvulnerable(true);
		stand.setHelmet(head);
		this.loc = loc;
		this.player = player;
		this.tier = tier;
	}

	@Override
	public void run() {
		if (ticks <= 100) {
			EulerAngle oldrot = stand.getHeadPose();
			EulerAngle newrot = oldrot.add(0, 0.2f, 0);
			stand.setHeadPose(newrot);
			stand.teleport(stand.getLocation().add(0,0.01,0));
			ticks = ticks + 2;
		} else {
			if (runOnce == false) {
				player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
				stand.getWorld().spawnParticle(Particle.TOTEM,stand.getLocation().clone().add(0,1,0),250,0,0,0,0.2);
				ArrayList<PetType> petsList = Tier.getPetsByTier(tier);
				Random ran = new Random();
				pet = petsList.get(ran.nextInt(petsList.size()));
				ItemStack head = Utils.createCustomSkull("123", pet.getSkin());
				stand.setHelmet(head);
				stand.setCustomName(Utils.Color(pet.getName()));
				stand.setCustomNameVisible(true);
				runOnce = true;
			}
			
			if (newTicks <= 100) {
				EulerAngle oldrot = stand.getHeadPose();
				EulerAngle newrot = oldrot.add(0, 0.2f, 0);
				stand.setHeadPose(newrot);
				newTicks = newTicks + 2;
			} else {
				stand.remove();
				player.getInventory().addItem(Utils.createPetItem(new ValidPet(pet,1)));
				stand.getWorld().spawnParticle(Particle.TOTEM,stand.getLocation().clone().add(0,1,0),250,0,0,0,0.2);
				player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1f, 1f);
				cancel();
				
				return;
			}
		}
	}
	
}

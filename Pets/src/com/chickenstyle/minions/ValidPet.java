package com.chickenstyle.minions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.chickenstyle.minions.Enums.Pets;
import com.chickenstyle.minions.Enums.Tier;
import com.chickenstyle.minions.PetUtils.PetsAnimate;

public class ValidPet {
	private Pets pet;
	private int level;
	
	public ValidPet(Pets pet,int level){
		this.pet = pet;
		this.level = level;
	}
	public ValidPet(Pets pet){
		this.pet = pet;
		this.level = 1;
	}

	public Pets getPet() {
		return this.pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override 
	public String toString() {
		return this.pet.toString() + "-" + level;
	}
	
	public String getName() {
		return this.pet.getName();
	}
	
	public String getSkin() {
		return this.pet.getSkin();
	}
	
	public Tier getTier() {
		return this.pet.getTier();
	}
	
	public boolean isValid(ItemStack skull) {
		if (skull.hasItemMeta() && skull.getItemMeta().hasLore()) {
			String[] petData = skull.getItemMeta().getLore().get(1).split("-");
			try {
				if (Pets.valueOf(petData[0]) != null) {
					return true;
				}
			} catch (Exception ex) {
				return false;
			}
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public void spawnPet(Player player,ItemStack head) {
		int animate = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(),
		new PetsAnimate(player,head), 0, 2);
		
		Main.taskID.put(player.getUniqueId(), animate);
	}
}
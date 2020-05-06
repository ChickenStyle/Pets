package com.chickenstyle.minions.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.PetType;
import com.chickenstyle.minions.Enums.Tier;

public class DespawnPetEvent extends Event{
	
	Player player;
	ValidPet pet;
	public static final HandlerList HANDLERS = new HandlerList();
	
	public DespawnPetEvent(Player player,ValidPet pet) {
		this.player = player;
		this.pet = pet;
	}
	

	public Player getPlayer() {
		return player;
	}
	public ValidPet getPet() {
		return pet;
	}
	
	public int getPetLevel() {
		return pet.getLevel();
	}
	
	public PetType getPetType() {
		return pet.getType();
	}
	
	public Tier getPetTier() {
		return pet.getTier();
	}
	
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

}

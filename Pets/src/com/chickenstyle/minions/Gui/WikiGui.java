package com.chickenstyle.minions.Gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.PetType;
import com.chickenstyle.minions.Enums.Tier;

public class WikiGui {
	public WikiGui(Player player) {
		//Black Glass
		ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName(" ");
		glass.setItemMeta(glassMeta);
		
		
		Inventory gui = Bukkit.createInventory(null, 54 , Utils.Color("&7Available Max Level Pets"));
		for (int i = 0; i < 10;i++) {
			gui.setItem(i, glass);
		}
		for (int i = 44; i < 54; i++) {
			gui.setItem(i, glass);
		}

		ArrayList<Integer> glassSlot = new ArrayList<Integer>();
		glassSlot.add(17);
		glassSlot.add(26);
		glassSlot.add(35);
		glassSlot.add(36);
		glassSlot.add(27);
		glassSlot.add(18);
		for (int slot:glassSlot) {
			gui.setItem(slot, glass);
		}
		
		for (PetType petType:Tier.getLegendaries()) {
			gui.addItem(Utils.createPetItem(new ValidPet(petType,15)));
		}
		
		for (PetType petType:Tier.getEpics()) {
			gui.addItem(Utils.createPetItem(new ValidPet(petType,20)));
		}
		
		for (PetType petType:Tier.getRares()) {
			gui.addItem(Utils.createPetItem(new ValidPet(petType,25)));
		}
		
		for (PetType petType:Tier.getCommons()) {
			gui.addItem(Utils.createPetItem(new ValidPet(petType,30)));
		}
		
		
		player.openInventory(gui);
	}
}

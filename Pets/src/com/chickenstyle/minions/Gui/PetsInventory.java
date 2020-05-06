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

public class PetsInventory {
	public PetsInventory(Player player,ArrayList<ValidPet> list) {
		//Black Glass
		ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName(" ");
		glass.setItemMeta(glassMeta);
		
		//DeSpawn Pet
		ItemStack despawn = new ItemStack(Material.BARRIER);
		ItemMeta dMeta = despawn.getItemMeta();
		dMeta.setDisplayName(Utils.Color("&4&lDespawn active pet!"));
		ArrayList<String> dLore = new ArrayList<String>();
		dLore.add(Utils.Color("&4Click on this item to despawn"));
		dLore.add(Utils.Color("&4your active pet!"));
		dMeta.setLore(dLore);
		despawn.setItemMeta(dMeta);
		
		//Pet to item
		ItemStack petItem = new ItemStack(Material.GRAY_DYE);
		ItemMeta pMeta = petItem.getItemMeta();
		pMeta.setDisplayName(Utils.Color("&aConvert pet to an item"));
		ArrayList<String> pLore = new ArrayList<String>();
		pLore.add(Utils.Color("&7Enable this setting and"));
		pLore.add(Utils.Color("&7click any pet to convert it"));
		pLore.add(Utils.Color("&7to an item"));
		pLore.add(Utils.Color(""));
		pLore.add(Utils.Color("&cDisabled"));
		pMeta.setLore(pLore);
		petItem.setItemMeta(pMeta);
		
		Inventory gui = Bukkit.createInventory(null, 54 , Utils.Color("&7Your Pets"));
		for (int i = 0; i < 10;i++) {
			gui.setItem(i, glass);
		}
		for (int i = 44; i < 54; i++) {
		
			if (i == 48) {
				gui.setItem(i, despawn);
			} else if (i == 50) {
				gui.setItem(i, petItem);
			} else {
				gui.setItem(i, glass);
			}
			
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
		
		if (!list.isEmpty() && list != null) {
			for (ValidPet pet:list) {
				gui.addItem(Utils.createPetItem(pet));
			}
		}
		
		
		player.openInventory(gui);
	}
}

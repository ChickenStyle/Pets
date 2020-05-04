package com.chickenstyle.minions.Gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.chickenstyle.minions.HiddenStringUtils;
import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;

public class PetsInventory {
	public PetsInventory(Player player,ArrayList<ValidPet> list) {
		//Black Glass
		ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName(" ");
		glass.setItemMeta(glassMeta);
		
		Inventory gui = Bukkit.createInventory(null, 54 , Utils.Color("&7Your Pets"));
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
		
		if (!list.isEmpty() && list != null) {
			for (ValidPet pet:list) {
				ItemStack skull = Utils.createCustomSkull(pet.getName(), pet.getPet().getSkin());
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta.setDisplayName(Utils.Color(pet.getName()));
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(Utils.Color("&7&lRarity: ") + pet.getTier().getName());
				lore.add(HiddenStringUtils.encodeString(pet.toString()));
				lore.add(Utils.Color("&7&lLevel: " + pet.getLevel()));
				meta.setLore(lore);
				skull.setItemMeta(meta);
				gui.addItem(skull);
			}
		}
		
		
		player.openInventory(gui);
	}
}

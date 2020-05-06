package com.chickenstyle.minions.Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.chickenstyle.minions.HiddenStringUtils;
import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.CustomEvents.DespawnPetEvent;
import com.chickenstyle.minions.CustomEvents.SpawnPetEvent;
import com.chickenstyle.minions.Enums.PetType;
import com.chickenstyle.minions.Gui.PetsInventory;

public class GuiClickEvent implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory clickedInv = e.getClickedInventory();
		int slot = e.getSlot();
		if (e.getView().getTitle().equals(Utils.Color("&7Your Pets"))) {
			e.setCancelled(true);
			if (clickedInv == null) {return;}
			if (clickedInv.getItem(slot) == null ) {return;}
			if (e.getClickedInventory().getType().toString() == "CHEST") {
				if (clickedInv.getItem(slot) != null 
					&& !clickedInv.getItem(slot).getType().equals(Material.BLACK_STAINED_GLASS_PANE)
					&& !clickedInv.getItem(slot).getType().equals(Material.BARRIER)
					&& !clickedInv.getItem(slot).getType().equals(Material.LIME_DYE)
					&& !clickedInv.getItem(slot).getType().equals(Material.GRAY_DYE)) {	
						if (clickedInv.getItem(50).getType().equals(Material.GRAY_DYE)) {
							ItemStack petItem = clickedInv.getItem(slot);
							HiddenStringUtils.extractHiddenString(petItem.getItemMeta().getLore().get(1));
							String[] data = HiddenStringUtils.extractHiddenString(petItem.getItemMeta().getLore().get(1)).split("-");
							ValidPet pet = new ValidPet(PetType.valueOf(data[0]),Integer.valueOf(data[1]));
							player.closeInventory();
							
							if (Main.spawnedPet.containsKey(player.getUniqueId())) {
								player.sendMessage(ChatColor.RED + "You despawned a " +
								Utils.Color(Main.spawnedPet.get(player.getUniqueId()).getName()) +
								ChatColor.RED + " pet!");
								DespawnPetEvent despawnEvent = new DespawnPetEvent(player, Main.spawnedPet.get(player.getUniqueId()));
								Bukkit.getPluginManager().callEvent(despawnEvent);
								Bukkit.getScheduler().cancelTask(Main.taskID.get(player.getUniqueId()));
								Main.spawnedPet.remove(player.getUniqueId());
								Main.stands.get(player.getUniqueId()).remove();
								Main.stands.remove(player.getUniqueId());
							}
							pet.spawnPet(player,petItem);
							Main.spawnedPet.put(player.getUniqueId(), pet);
							player.sendMessage(ChatColor.GREEN + "You spawned a " +
									Utils.Color(pet.getName()) + ChatColor.GREEN + " pet!");
							player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
							SpawnPetEvent spawnEvent = new SpawnPetEvent(player, pet);
							Bukkit.getPluginManager().callEvent(spawnEvent);
							
							
						} else {
							if (Utils.hasAvaliableSlot(player)) {
								player.getInventory().addItem(clickedInv.getItem(slot));
								clickedInv.setItem(50, Utils.disabledDye());
								player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
								ArrayList<ValidPet> list = Main.petsInv.get(player.getUniqueId());
								list.remove(Utils.convertSlotToNumber(slot));
								Main.petsInv.put(player.getUniqueId(), list);
								
								new PetsInventory(player, list);
							} else {
								player.sendMessage(Utils.Color("&4&lYour inventory is full, you cannot take the pet!"));
								player.closeInventory();
							}
						}
					
						
				} else if (clickedInv.getItem(slot).getType().equals(Material.BARRIER)) {
					if (Main.spawnedPet.containsKey(player.getUniqueId())) {
						player.closeInventory();
						player.sendMessage(ChatColor.RED + "You despawned a " +
						Utils.Color(Main.spawnedPet.get(player.getUniqueId()).getName()) +
						ChatColor.RED + " pet!");
						DespawnPetEvent despawnEvent = new DespawnPetEvent(player, Main.spawnedPet.get(player.getUniqueId()));
						Bukkit.getPluginManager().callEvent(despawnEvent);
						Bukkit.getScheduler().cancelTask(Main.taskID.get(player.getUniqueId()));
						Main.spawnedPet.remove(player.getUniqueId());
						Main.stands.get(player.getUniqueId()).remove();
						Main.stands.remove(player.getUniqueId());
						player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1f, 1f);
					} else {
						player.sendMessage(Utils.Color("&4&lYou dont have a pet to despawn!"));
					}
				} else if (clickedInv.getItem(slot).getType().equals(Material.GRAY_DYE)) {
					if (!Main.spawnedPet.containsKey(player.getUniqueId())) {
						clickedInv.setItem(slot, Utils.enabledDye());
					} else {
						player.closeInventory();
						player.sendMessage(Utils.Color("&4&lRemove your active pet before converting!"));
					}
				} else if (clickedInv.getItem(slot).getType().equals(Material.LIME_DYE)) {
					clickedInv.setItem(slot, Utils.disabledDye());
				}
			}
		}
	}
	
}

package com.chickenstyle.minions.Events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.chickenstyle.minions.HiddenStringUtils;
import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.PetType;


public class RightClickEvent implements Listener{
	@EventHandler
	public static void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player player = e.getPlayer();
			ItemStack item = player.getInventory().getItemInMainHand();
			if (item != null && item.getType().equals(Material.PLAYER_HEAD) && item.getItemMeta().hasLore()) {
				if (item.getItemMeta().getLore().size() == 4) {
					if (Main.petsInv.get(player.getUniqueId()).size() < 28) {
						String[] petData = HiddenStringUtils.extractHiddenString(item.getItemMeta().getLore().get(1)).split("-");
						ValidPet pet = new ValidPet(PetType.valueOf(petData[0]), Integer.valueOf(petData[1]));
						if(player.getInventory().getItemInMainHand() != null){
						    player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						    e.setCancelled(true);
						}
						ArrayList<ValidPet> list = Main.petsInv.get(player.getUniqueId());
						list.add(pet);
						Main.petsInv.put(player.getUniqueId(), list);
						player.sendMessage(Utils.Color(pet.getName() + " &a&lhas been added to your pets bag!"));
						player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
					} else {
						player.sendMessage(Utils.Color("&4&lYour pets bag is full, you cannot add more pets!"));
						player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1f, 1f);
					}
 				}
			}
		}
	}
}

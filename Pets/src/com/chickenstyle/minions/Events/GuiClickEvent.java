package com.chickenstyle.minions.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.chickenstyle.minions.HiddenStringUtils;
import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.Pets;

public class GuiClickEvent implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory clickedInv = e.getClickedInventory();
		int slot = e.getSlot();
		if (e.getView().getTitle().equals(Utils.Color("&7Your Pets"))) {
			e.setCancelled(true);
			if (clickedInv == null) {return;}
			if (e.getClickedInventory().getType().toString() == "CHEST") {
				if (clickedInv.getItem(slot) != null 
					&& !clickedInv.getItem(slot).getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
					player.sendMessage("1");
						ItemStack petItem = clickedInv.getItem(slot);
						HiddenStringUtils.extractHiddenString(petItem.getItemMeta().getLore().get(1));
						String[] data = HiddenStringUtils.extractHiddenString(petItem.getItemMeta().getLore().get(1)).split("-");
						ValidPet pet = new ValidPet(Pets.valueOf(data[0]),Integer.valueOf(data[1]));
						pet.spawnPet(player,petItem);
				}
			}
		}
	}
	
}

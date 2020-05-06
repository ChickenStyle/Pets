package com.chickenstyle.minions.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.Enums.Tier;
import com.chickenstyle.minions.PetUtils.CrateAnimation;

public class OpenCrateEvent implements Listener{
	@EventHandler
	public void onInteractEvent(BlockPlaceEvent e) {
		String legendary = Main.getInstance().getConfig().getString("legendaryCrateName");
		String epic = Main.getInstance().getConfig().getString("epicCrateName");
		String rare = Main.getInstance().getConfig().getString("rareCrateName");
		String common = Main.getInstance().getConfig().getString("commonCrateName");
			Player player = e.getPlayer();
			if (player.getInventory().getItemInMainHand() == null) {return;}
			if (player.getInventory().getItemInMainHand().getType().equals(Material.PLAYER_HEAD)) {
				if (player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
					ItemStack crate = player.getInventory().getItemInMainHand();
					if (Utils.hasAvaliableSlot(player)) {
						Location loc = e.getBlock().getLocation();
						if (crate.getItemMeta().getDisplayName().equals(Utils.Color(legendary))) {
							
							if (loc.clone().add(0,1,0).getBlock().getType() == null || loc.clone().add(0,1,0).getBlock().getType() == Material.AIR) {
								new CrateAnimation(Tier.LEGENDARY, loc, player).runTaskTimer(Main.getInstance(), 0, 2);
							} else {
								player.sendMessage(Utils.Color("&4&lYou cannot place the crate here!"));
								e.setCancelled(true);
								return;
							}
						} else if (crate.getItemMeta().getDisplayName().equals(Utils.Color(epic))) {
							if (loc.clone().add(0,1,0).getBlock().getType() == null || loc.clone().add(0,1,0).getBlock().getType() == Material.AIR) {
								new CrateAnimation(Tier.EPIC, loc, player).runTaskTimer(Main.getInstance(), 0, 2);
							} else {
								player.sendMessage(Utils.Color("&4&lYou cannot place the crate here!"));
								e.setCancelled(true);
								return;
							}
						} else if (crate.getItemMeta().getDisplayName().equals(Utils.Color(rare))) {
							if (loc.clone().add(0,1,0).getBlock().getType() == null || loc.clone().add(0,1,0).getBlock().getType() == Material.AIR) {
								new CrateAnimation(Tier.RARE, loc, player).runTaskTimer(Main.getInstance(), 0, 2);
							} else {
								player.sendMessage(Utils.Color("&4&lYou cannot place the crate here!"));
								e.setCancelled(true);
								return;
							}
						} else if (crate.getItemMeta().getDisplayName().equals(Utils.Color(common))) {
							if (loc.clone().add(0,1,0).getBlock().getType() == null || loc.clone().add(0,1,0).getBlock().getType() == Material.AIR) {
								new CrateAnimation(Tier.COMMON, loc, player).runTaskTimer(Main.getInstance(), 0, 2);
							} else {
								player.sendMessage(Utils.Color("&4&lYou cannot place the crate here!"));
								e.setCancelled(true);
								return;
							}
							
						}
						if (crate != null) {
							if (crate.getAmount() == 1) {
								player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
							} else {
								player.getInventory().getItemInMainHand().setAmount(crate.getAmount() - 1);
							}
						}
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						player.sendMessage(Utils.Color("&4&lYou dont have a place in you inventory!"));
					}

				}
			}
	}
}

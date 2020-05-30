package com.chickenstyle.minions.Abilities;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.chickenstyle.minions.Main;
import com.chickenstyle.minions.Utils;
import com.chickenstyle.minions.ValidPet;
import com.chickenstyle.minions.Enums.PetType;
import com.chickenstyle.minions.Enums.Tier;

public class OnBlockBreak implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (Main.spawnedPet.containsKey(player.getUniqueId())) {
			if (Main.spawnedPet.get(player.getUniqueId()).getType().equals(PetType.SILVERFISH)) {
				if (e.getBlock().getType().toString().endsWith("ORE")) {
					ValidPet pet = Main.spawnedPet.get(player.getUniqueId());
					float random = ThreadLocalRandom.current().nextFloat();
					if (random <= Utils.formula(10, pet.getLevel(), Tier.RARE)/100) {
						if (!e.getBlock().getDrops(player.getInventory().getItemInMainHand()).isEmpty()) {
							ArrayList<ItemStack> drops = new ArrayList<>();
							for (ItemStack item:e.getBlock().getDrops(player.getInventory().getItemInMainHand())) {
								ItemStack drop = new ItemStack(item.getType(),item.getAmount() * 2);
								drops.add(drop);
							}
							e.setDropItems(false);
							for (ItemStack drop:drops) {
								e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), drop);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		ItemStack item = Utils.createItemStack(Material.ENCHANTING_TABLE, "&6Pet Upgrader!", 1);
		if (player.getInventory().getItemInMainHand().getType().equals(item.getType()) &&
			player.getInventory().getItemInMainHand().getItemMeta().equals(item.getItemMeta())) {
			Location loc = e.getBlockPlaced().getLocation();
		}
	}
}

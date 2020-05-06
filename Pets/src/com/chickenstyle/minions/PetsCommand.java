package com.chickenstyle.minions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.chickenstyle.minions.Enums.PetType;
import com.chickenstyle.minions.Enums.Tier;
import com.chickenstyle.minions.Gui.PetsInventory;

public class PetsCommand implements CommandExecutor {
	// /pets givepet [player] [pet] {level}
	// /pets givecrate [player] [tier] {amount}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			switch (args.length) {
			case 0:
				new PetsInventory(player, Main.petsInv.get(player.getUniqueId()));
			break;
			
			case 1:
			case 2:
				player.sendMessage(Utils.Color("&cInvalid Usage!"));
			break;
			
			case 3:
				if (args[0].equalsIgnoreCase("givepet")) {
					if (player.hasPermission("pets.admin")) {
						boolean playerOnline = false;
						for (Player onlinePlayer: Bukkit.getServer().getOnlinePlayers()) {
							if (onlinePlayer.getName().equalsIgnoreCase(args[1])) {
								playerOnline = true;
							}
						}
						
						if (playerOnline) {
							Player target = Bukkit.getPlayer(args[1]);
							try {
								if (PetType.valueOf(args[2].toUpperCase()) != null) {
									if (Utils.hasAvaliableSlot(target)) {
										PetType pet = PetType.valueOf(args[2].toUpperCase());
										target.getInventory().addItem(Utils.createPetItem(new ValidPet(pet)));
										player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
										target.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
										player.sendMessage(Utils.Color("&aYou gave " + pet.getName() + " &apet to " + target.getName() + "&a."));
										target.sendMessage(Utils.Color("&a" + player.getName() + " gave you " + pet.getName() + "&a pet."));
										
									} else {
										player.sendMessage(ChatColor.RED + target.getName() + "'s inventory is full!");
									}
								}
							} catch (Exception ex) {
								player.sendMessage(ChatColor.RED + "Invalid Pet or Invalid Number!");
							}

						} else {
							player.sendMessage(ChatColor.RED + "The player is offline");
						}
					} else {
						player.sendMessage(ChatColor.RED + "You cannot use this command!");
					}
					
				} else if (args[0].equalsIgnoreCase("givecrate")) {
					if (player.hasPermission("pets.admin")) {
						boolean playerOnline = false;
						for (Player onlinePlayer: Bukkit.getServer().getOnlinePlayers()) {
							if (onlinePlayer.getName().equalsIgnoreCase(args[1])) {
								playerOnline = true;
							}
						}
						
						if (playerOnline) {
							if (args[2].equalsIgnoreCase("legendary") ||args[2].equalsIgnoreCase("epic") ||
								args[2].equalsIgnoreCase("rare") || args[2].equalsIgnoreCase("common")) {
								Tier tier = Tier.valueOf(args[2].toUpperCase());
								Player target = Bukkit.getPlayer(args[1]);
								if (Utils.hasAvaliableSlot(target)) {
									target.getInventory().addItem(Utils.createCustomSkull(Utils.Color(Main.getInstance().getConfig().getString(args[2].toLowerCase() + "CrateName")),
											tier.getTexture()));
									player.sendMessage(ChatColor.GREEN + "You gave " + target.getName() + " " + 1 + " " + tier.getName() + " crates!");
									target.sendMessage(ChatColor.GREEN + target.getName() +  " game you " + " " + 1 + " " + tier.getName() + " crates!");
									player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
									target.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
								} else {
									player.sendMessage(ChatColor.RED + target.getName() + "'s inventory is full!");
								}
							} else {
								player.sendMessage(ChatColor.RED + "Invalid rarity!");
							}
						}
					}
				}
			break;
			
			case 4:
				if (args[0].equalsIgnoreCase("givepet")) {
					if (player.hasPermission("pets.admin")) {
						boolean playerOnline = false;
						for (Player onlinePlayer: Bukkit.getServer().getOnlinePlayers()) {
							if (onlinePlayer.getName().equalsIgnoreCase(args[1])) {
								playerOnline = true;
							}
						}
						
						if (playerOnline) {
							Player target = Bukkit.getPlayer(args[1]);
							try {
								if (PetType.valueOf(args[2].toUpperCase()) != null) {
									if (Utils.hasAvaliableSlot(target)) {
										if (Integer.valueOf(args[3]) != null) {
											PetType pet = PetType.valueOf(args[2].toUpperCase());
											int level = Integer.valueOf(args[3]);
											target.getInventory().addItem(Utils.createPetItem(new ValidPet(pet,level)));
											player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
											target.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
											player.sendMessage(Utils.Color("&aYou gave " + pet.getName() + " &apet,&6&lLevel " + level + " &ato " + target.getName() + "&a."));
											target.sendMessage(Utils.Color("&a" + player.getName() + " gave you " + pet.getName() + " &apet, &6&lLevel " + level + "&a."));
										}

									} else {
										player.sendMessage(ChatColor.RED + target.getName() + "'s inventory is full!");
									}
								}
							} catch (Exception ex) {
								player.sendMessage(ChatColor.RED + "Invalid Pet or Invalid Number!");
							}
							
						} else {
							player.sendMessage(ChatColor.RED + "The player is offline");
						}
					} else {
						player.sendMessage(ChatColor.RED + "You cannot use this command!");
					}
					
				}   else if (args[0].equalsIgnoreCase("givecrate")) {
					if (player.hasPermission("pets.admin")) {
						boolean playerOnline = false;
						for (Player onlinePlayer: Bukkit.getServer().getOnlinePlayers()) {
							if (onlinePlayer.getName().equalsIgnoreCase(args[1])) {
								playerOnline = true;
							}
						}
						
						if (playerOnline) {
							if (args[2].equalsIgnoreCase("legendary") ||args[2].equalsIgnoreCase("epic") ||
								args[2].equalsIgnoreCase("rare") || args[2].equalsIgnoreCase("common")) {
								Tier tier = Tier.valueOf(args[2].toUpperCase());
								Player target = Bukkit.getPlayer(args[1]);
								try {
									if (Integer.valueOf(args[3]) != null) {
										if (Utils.hasAvaliableSlot(target)) {
											ItemStack head = Utils.createCustomSkull(Utils.Color(Main.getInstance().getConfig().getString(args[2].toLowerCase() + "CrateName")),
													tier.getTexture());
											head.setAmount(Integer.valueOf(args[3]));
											target.getInventory().addItem(head);
											
											player.sendMessage(ChatColor.GREEN + "You gave " + target.getName() + " " + args[3] + " " + tier.getName() + " crates!");
											target.sendMessage(ChatColor.GREEN + target.getName() +  " game you " + " " + args[3] + " " + tier.getName() + " crates!");
											player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
											target.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
										} else {
											player.sendMessage(ChatColor.RED + target.getName() + "'s inventory is full!");
										}
									}
								} catch (Exception ex) {
									player.sendMessage(ChatColor.RED + "Invaid number!");
								}
								

							} else {
								player.sendMessage(ChatColor.RED + "Invalid rarity!");
							}
						}
					}
				}
			break;
			}
			
		}
		return false;
	}

}

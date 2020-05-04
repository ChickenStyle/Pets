package com.chickenstyle.minions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.chickenstyle.minions.Gui.PetsInventory;

public class PetsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			new PetsInventory(player, Main.petsInv.get(player.getUniqueId()));
		}
		return false;
	}

}

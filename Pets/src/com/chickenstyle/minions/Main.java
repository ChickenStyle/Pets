package com.chickenstyle.minions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.chickenstyle.minions.Abilities.OnAttackAbilities;
import com.chickenstyle.minions.Abilities.OnPetSpawn;
import com.chickenstyle.minions.Enums.Pets;
import com.chickenstyle.minions.Events.GuiClickEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main extends JavaPlugin implements Listener {
	public static HashMap<UUID, ValidPet> spawnedPet = new HashMap<>();
	public static HashMap<UUID,Integer> taskID = new HashMap<>();
	public static HashMap<UUID,ArmorStand> Stands = new HashMap<>();
	public static HashMap<UUID, ArrayList<ValidPet>> petsInv;

	@Override
	public void onEnable() {
		// Listeners
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new OnAttackAbilities(), this);
		Bukkit.getPluginManager().registerEvents(new OnPetSpawn(), this);
		Bukkit.getPluginManager().registerEvents(new GuiClickEvent(), this);
		// Default Config
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();

		// Loading Hashmap

		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(getDataFolder() + "/UserData.json"));
			petsInv = gson.fromJson(reader, new TypeToken<HashMap<UUID, ArrayList<ValidPet>>>() {
			}.getType());
			reader.close();
			System.out.println("Data has been loaded!");
		} catch (IOException e) {
			petsInv = new HashMap<>();
		}

		// Pet command
		getCommand("pets").setExecutor(new PetsCommand());

		// Log message
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Pets plugin has been enabled!");
	}

	@Override
	public void onDisable() {
		//Saving hashmap
		try {
			Writer writer = new FileWriter(getDataFolder() + "/UserData.json");
			new Gson().toJson(petsInv, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Main getInstance() {
		return Main.getPlugin(Main.class);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		UUID player = e.getPlayer().getUniqueId();
		if (!petsInv.containsKey(e.getPlayer().getUniqueId())) {
			petsInv.put(player, new ArrayList<ValidPet>());
		} else {
			ArrayList<ValidPet> list = new ArrayList<ValidPet>();
			list.add(new ValidPet(Pets.FIRE_DEMON,69));
			petsInv.put(player, list);
		}
		

	}
}

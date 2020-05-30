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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import com.chickenstyle.minions.Abilities.OnAttackAbilities;
import com.chickenstyle.minions.Abilities.OnBlockBreak;
import com.chickenstyle.minions.Abilities.OnEat;
import com.chickenstyle.minions.Abilities.OnMove;
import com.chickenstyle.minions.Abilities.OnPetDespawn;
import com.chickenstyle.minions.Abilities.OnPetSpawn;
import com.chickenstyle.minions.Abilities.OnRightClick;
import com.chickenstyle.minions.Events.GuiClickEvent;
import com.chickenstyle.minions.Events.OnDeathEvent;
import com.chickenstyle.minions.Events.OpenCrateEvent;
import com.chickenstyle.minions.Events.RightClickEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main extends JavaPlugin implements Listener {
	public static HashMap<UUID, ValidPet> spawnedPet = new HashMap<>();
	public static HashMap<UUID,Integer> taskID = new HashMap<>();
	public static HashMap<UUID,ArmorStand> stands = new HashMap<>();
	public static HashMap<UUID, ArrayList<ValidPet>> petsInv;

	@Override
	public void onEnable() {
		// Listeners
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new GuiClickEvent(), this);
		Bukkit.getPluginManager().registerEvents(new RightClickEvent(),this);
		Bukkit.getPluginManager().registerEvents(new OpenCrateEvent(),this);
		Bukkit.getPluginManager().registerEvents(new OnDeathEvent(), this);
		Bukkit.getPluginManager().registerEvents(new OnAttackAbilities(), this);
		Bukkit.getPluginManager().registerEvents(new OnPetSpawn(), this);
		Bukkit.getPluginManager().registerEvents(new OnPetDespawn(), this);
		Bukkit.getPluginManager().registerEvents(new OnMove(), this);
		Bukkit.getPluginManager().registerEvents(new OnRightClick(), this);
		Bukkit.getPluginManager().registerEvents(new OnEat(), this);
		Bukkit.getPluginManager().registerEvents(new OnBlockBreak(), this);
		
		
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
		for (Player player:getServer().getOnlinePlayers()) {
			if (Main.spawnedPet.containsKey(player.getUniqueId())) {
				switch (Main.spawnedPet.get(player.getUniqueId()).getType()) {
				case RABBIT:
					player.removePotionEffect(PotionEffectType.SPEED);
					player.removePotionEffect(PotionEffectType.FAST_DIGGING);
				break;
				
				case SQUID:
					player.removePotionEffect(PotionEffectType.WATER_BREATHING);
				break;
				
				case PUFFERFISH:
					player.removePotionEffect(PotionEffectType.WATER_BREATHING);
				break;
				
				case SILVERFISH:
					player.removePotionEffect(PotionEffectType.FAST_DIGGING);
				break;
				
				case MR_PENGUIN:
					player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				break;
				
				case GODZILLA:
					player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				break;
				
				case MYSTERY_MAGMA_SLIME:
					player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
					player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
				break;
				
				case FIRE_DEMON:
					player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				break;
				default:
				}
			}
		}
		
		//Saving hashmap
		try {
			Writer writer = new FileWriter(getDataFolder() + "/UserData.json");
			new Gson().toJson(petsInv, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!taskID.isEmpty()) {
			for (int id:taskID.values()) {
				Bukkit.getScheduler().cancelTask(id);
			}
		}
		if (!stands.isEmpty()) {
			for (ArmorStand stand:stands.values()) {
				stand.remove();
			}
		}
	}

	public static Main getInstance() {
		return Main.getPlugin(Main.class);
	}

	// Adding empty list
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		UUID player = e.getPlayer().getUniqueId();
		if (!petsInv.containsKey(e.getPlayer().getUniqueId())) {
			petsInv.put(player, new ArrayList<ValidPet>());
		}
	}
	
	
	//Removing pet on quit
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		UUID player = e.getPlayer().getUniqueId();
			if (spawnedPet.containsKey(player)) {
				spawnedPet.remove(player);
				stands.get(player).remove();
				Bukkit.getScheduler().cancelTask(taskID.get(player));
				stands.remove(player);
				taskID.remove(player);
					if (Main.spawnedPet.containsKey(player)) {
						switch (Main.spawnedPet.get(player).getType()) {
						case RABBIT:
							e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
							e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
						break;
						
						case SQUID:
							e.getPlayer().removePotionEffect(PotionEffectType.WATER_BREATHING);
						break;
						
						case PUFFERFISH:
							e.getPlayer().removePotionEffect(PotionEffectType.WATER_BREATHING);
						break;
						
						case SILVERFISH:
							e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
						break;
						
						case MR_PENGUIN:
							e.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
						break;
						
						case GODZILLA:
							e.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
						break;
						
						case MYSTERY_MAGMA_SLIME:
							e.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
							e.getPlayer().removePotionEffect(PotionEffectType.HEALTH_BOOST);
						break;
						
						case FIRE_DEMON:
							e.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
						break;
						default:
						}
					}
			
			}
	}

}

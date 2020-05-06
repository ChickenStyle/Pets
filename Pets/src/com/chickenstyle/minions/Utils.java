package com.chickenstyle.minions;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.chickenstyle.minions.Enums.Tier;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;


public class Utils {
	public static String Color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static ItemStack disabledDye() {
		ItemStack petItem = new ItemStack(Material.GRAY_DYE);
		ItemMeta pMeta = petItem.getItemMeta();
		pMeta.setDisplayName(Utils.Color("&aConvert pet to an item"));
		ArrayList<String> pLore = new ArrayList<String>();
		pLore.add(Utils.Color("&7Enable this setting and"));
		pLore.add(Utils.Color("&7click any pet to convert it"));
		pLore.add(Utils.Color("&7to an item"));
		pLore.add(Utils.Color(""));
		pLore.add(Utils.Color("&cDisabled"));
		pMeta.setLore(pLore);
		petItem.setItemMeta(pMeta);
		return petItem;
	}
	
	public static ItemStack enabledDye() {
		ItemStack petItem = new ItemStack(Material.LIME_DYE);
		ItemMeta pMeta = petItem.getItemMeta();
		pMeta.setDisplayName(Utils.Color("&aConvert pet to an item"));
		ArrayList<String> pLore = new ArrayList<String>();
		pLore.add(Utils.Color("&7Enable this setting and"));
		pLore.add(Utils.Color("&7click any pet to convert it"));
		pLore.add(Utils.Color("&7to an item"));
		pLore.add(Utils.Color(""));
		pLore.add(Utils.Color("&aEnabled"));
		pMeta.setLore(pLore);
		petItem.setItemMeta(pMeta);
		return petItem;
	}
	
	public static boolean hasAvaliableSlot(Player player){
	    Inventory inv = player.getInventory();
	    for (ItemStack item: inv.getContents()) {
	         if(item == null) {
	                 return true;
	         }
	     }
	return false;
	}
	
    public static ItemStack createCustomSkull(String displayName, ArrayList<String> lore, String texture) {
        texture = "http://textures.minecraft.net/texture/" + texture;
        
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        if (texture.isEmpty()) {
            return skull;
        }
       
        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        skullMeta.setDisplayName(Utils.Color(displayName));
        skullMeta.setLore(lore);
       
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", texture).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        }
        catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        assert profileField != null;
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    
    public static String formula(int chance,int level,Tier tier) {
    	double number = 0;
    	switch (tier) {
    	case COMMON:
    		number =  chance + (level * 0.1);
    	case RARE:
    		number = chance + (level * 0.28);
    	case EPIC:
    		number = chance + (level * 0.5);
    	case LEGENDARY:
    		number = chance + (level * 1);
    	}
    	number = Double.parseDouble(new DecimalFormat("##.##").format(number));
    	return number + "%";
    }
    
    public static ItemStack createCustomSkull(String displayName, String texture) {
        texture = "http://textures.minecraft.net/texture/" + texture;
        
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        if (texture.isEmpty()) {
            return skull;
        }
       
        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        skullMeta.setDisplayName(Utils.Color(displayName));
       
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", texture).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        }
        catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        assert profileField != null;
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    
    public static int convertSlotToNumber(int slot) {
    	switch (slot) {
    	case 10: return 0;
    	case 11: return 1;
    	case 12: return 2;
    	case 13: return 3;
    	case 14: return 4;
    	case 15: return 5;
    	case 16: return 6;
    	case 19: return 7;
    	case 20: return 8;
    	case 21: return 9;
    	case 22: return 10;
    	case 23: return 11;
    	case 24: return 12;
    	case 25: return 13;
    	case 28: return 14;
    	case 29: return 15;
    	case 30: return 16;
    	case 31: return 17;
    	case 32: return 18;
    	case 33: return 19;
    	case 34: return 20;
    	case 37: return 21;
    	case 38: return 22;
    	case 39: return 23;
    	case 40: return 24;
    	case 41: return 25;
    	case 42: return 26;
    	case 43: return 27;
    	default: return -99;
    	}
    }
    
    public static ArrayList<String> getDescription(ValidPet pet){
    	ArrayList<String> lore = new ArrayList<String>();
    	int level = pet.getLevel();
    	switch (pet.getType()) {    	
    	case ZOMBIE:
    		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&6Increases your damage with sword by &6" + formula(5, level, Tier.COMMON)));
    		lore.add(Color("&6"));
    		lore.add(Color("&6"));
    	break;
    	
    	case RABBIT:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&6Gives you speed and haste effects!"));
    		lore.add(Color("&6"));
    		lore.add(Color("&6"));
    	break;
    	
    	case SQUID:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&6Gives you breath under water effect!"));
    		lore.add(Color("&6"));
    		lore.add(Color("&6"));
    	break;
    	
    	case SKELETON:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&6Increases your damage with bow by &6" + formula(5, level, Tier.COMMON)));
    		lore.add(Color("&6"));
    		lore.add(Color("&6"));
    	break;
    	
    	case WOLF:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&aIncreases your damage again mobs (not players) by " + formula(7, level, Tier.RARE)));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case MR_PENGUIN:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&aGives you speed 1 when standing on ice!"));
    		lore.add(Color("&aGives defence effect!"));
    		lore.add(Color("&a"));
    	break;
    	
    	case GARFIELD:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&aGives regen 1 for 5 seconds when"));
    		lore.add(Color("&ayou eat something"));
    		lore.add(Color("&a"));
    	break;
    	
    	case PUFFERFISH:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case SILVERFISH:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case GODZILLA:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case MYSTERY_MAGMA_SLIME:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case WITHER_SKELETON:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case PIKACHU:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case REAPER:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case RAINBOW_PEPE:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case VAMPIRE:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case FIRE_DEMON:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	case WITHER_SKELETON_KING:
       		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	break;
    	
    	default:
    		lore.add(Color("&7Abilities:"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    		lore.add(Color("&a"));
    	}
    	return lore;
    }
    
}

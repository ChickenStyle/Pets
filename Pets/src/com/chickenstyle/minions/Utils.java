package com.chickenstyle.minions;

import java.lang.reflect.Field;
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
import com.chickenstyle.minions.PetUtils.RandomString;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;


public class Utils {
	public static String Color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static ItemStack createItemStack(Material mat,String name,int amount) {
		ItemStack item = new ItemStack(mat,amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Color(name));
		item.setItemMeta(meta);
		return item;
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
    
    public static double formula(int chance,int level,Tier tier) {
    	double number = 0;
    	switch (tier) {
    	case COMMON:
    		number =  chance + (level * 0.1);
    	break;
    	
    	case RARE:
    		number = chance + (level * 0.28);
    	break;
    	
    	case EPIC:
    		number = chance + (level * 0.5);
    	break;
    	
    	case LEGENDARY:
    		number = chance + (level * 1);
    	break;
    	
    	}
    	number = (Math.round(number * 100))/100.0;

    	return number;
    }
    
    public static ItemStack createPetItem(ValidPet pet) {
		ItemStack skull = createCustomSkull(pet.getName(), pet.getType().getSkin());
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(Utils.Color(pet.getName()));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Utils.Color("&7&lRarity: ") + pet.getTier().getName());
		lore.add(HiddenStringUtils.encodeString(pet.toString()));
		lore.add(Utils.Color("&7&lLevel: &6&l" + pet.getLevel()));
		lore.add(HiddenStringUtils.encodeString(new RandomString(10).nextString()));
		for (String str:getDescription(pet)) {
			lore.add(str);
		}
		meta.setLore(lore);
		skull.setItemMeta(meta);
		return skull;
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
    		lore.add(Color("&7&l&lAbilities:"));
    		lore.add(Color("&7Increases your damage with sword by &6" + formula(5, level, Tier.COMMON) + "%!"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    	break;
    	
    	case RABBIT:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives you speed and haste effects!"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    	break;
    	
    	case SQUID:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives you breath under water effect!"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    	break;
    	
    	case SKELETON:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Increases your damage with bow by &6" + formula(5, level, Tier.COMMON) + "%!"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    	break;
    	
    	case WOLF:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Increases your damage against Monsters by &6" + formula(7, level, Tier.COMMON) + "%!"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    	break;
    	
    	case MR_PENGUIN:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives you speed 1 when standing on ice!"));
    		lore.add(Color("&7Gives defence 1 effect!"));
    		lore.add(Color("&7"));
    	break;
    	
    	case GARFIELD:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives regen 1 for 5 seconds when you eat something!"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    	break;
    	
    	case PUFFERFISH:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives under water breath effect!"));
    		lore.add(Color("&6" + formula(10, level, Tier.RARE) + "% &7to apply poisen effect for 7 seconds on attacked enemy!" ));
    		lore.add(Color("&7"));
    	break;
    	
    	case SILVERFISH:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives haste 2 effect!"));
    		lore.add(Color("&6" + formula(10, level, Tier.RARE) + "% &7chance to double drop from ores!"));
    		lore.add(Color("&7"));
    	break;
    	
    	case GODZILLA:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives defence 1 effect!"));
    		lore.add(Color("&6" + formula(10, level, Tier.RARE) + "% &7chance to launch attacked enetity into air!"));
    		lore.add(Color("&7"));
    	break;
    	
    	case MYSTERY_MAGMA_SLIME:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives fire resistance effect!"));
    		lore.add(Color("&7Gives jump boost 1!"));
    		lore.add(Color("&7Gives health boost 2!"));
    	break;
    	
    	case WITHER_SKELETON:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives immune to wither effect!"));
    		lore.add(Color("&6" + formula(15, level, Tier.EPIC) + "% &7to apply wither effect for 5 seconds on attacked enemy!"));
    		lore.add(Color("&7"));
    	break;
    	
    	case PIKACHU:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives speed 1 effect!"));
    		lore.add(Color("&6" + formula(10, level, Tier.EPIC) + "% &7to strike a lighting on the attacked enemy!"));
    		lore.add(Color("&7"));
    	break;
    	
    	case REAPER:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Increases your damage by &6" + formula(10, level, Tier.LEGENDARY) + "%!"));
    		lore.add(Color("&7Have &6" +formula(5, level, Tier.LEGENDARY) + "% &7to cancel death and give you 10 hearts!"));
    		lore.add(Color("&7"));
    	break;
    	
    	case RAINBOW_PEPE:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7USELESS"));
    		lore.add(Color("&7BUT"));
    		lore.add(Color("&7It looks cool"));
    	break;
    	
    	case VAMPIRE:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Right click to get invisibility and speed 1 for 7 seconds while holding a sword!"));
    		lore.add(Color("&7Regen &6" + formula(0, level, Tier.LEGENDARY) + "% &7of your health on every attack!"));
    		lore.add(Color("&7"));
    	break;
    	
    	case FIRE_DEMON:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Gives fire resistance effect!"));
    		lore.add(Color("&7Increases your damage by &6" + formula(10, level, Tier.LEGENDARY) + "%!"));
    		lore.add(Color("&7Right click to launch a fireball while holding a sword!"));
    	break;
    	
    	case WITHER_SKELETON_KING:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Immune to wither effect!"));
    		lore.add(Color("&7Places wither effect on attacked enemy for 10 seconds!"));
    		lore.add(Color("&7Right click to launch a wither skeleton head while holding a sword!"));
    	break;
    	
    	case ENDER_KNIGHT:
       		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7Enderman wont agro on you!"));
    		lore.add(Color("&7Right click to teleport 8 blocks forward while holding a sword!"));
    		lore.add(Color("&7Increases your damage by &6" + formula(10, level, Tier.LEGENDARY) + "%!"));
    	break;
    	
    	default:
    		lore.add(Color("&7&lAbilities:"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    		lore.add(Color("&7"));
    	}
    	return lore;
    }
    
}

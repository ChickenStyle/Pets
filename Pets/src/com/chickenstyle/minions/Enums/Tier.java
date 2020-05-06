package com.chickenstyle.minions.Enums;

import java.util.ArrayList;

import com.chickenstyle.minions.Utils;

public enum Tier {
	LEGENDARY("&6&lLegendary","9c96be7886eb7df75525a363e5f549626c21388f0fda988a6e8bf487a53"),
	EPIC("&d&lEpic","25807cc4c3b6958aea6156e84518d91a49c5f32971e6eb269a23a25a27145"),
	RARE("&e&lRare","f37cae5c51eb1558ea828f58e0dff8e6b7b0b1a183d737eecf714661761"),
	COMMON("&a&lCommon","d5c6dc2bbf51c36cfc7714585a6a5683ef2b14d47d8ff714654a893f5da622");
	
	private String name;
	private String texture;
	
	Tier(String name,String texture) {
		this.name = name;
		this.texture = texture;
	}
	
	public String getName() {
		return Utils.Color(this.name);
	}
	
	public String getTexture() {
		return this.texture;
	}
	
	static public ArrayList<PetType> getCommons(){
		ArrayList<PetType> list = new ArrayList<PetType>();
		list.add(PetType.ZOMBIE);
		list.add(PetType.RABBIT);
		list.add(PetType.SQUID);
		list.add(PetType.SKELETON);
		list.add(PetType.WOLF);
		return list;
	}
	
	static public ArrayList<PetType> getRares(){
		ArrayList<PetType> list = new ArrayList<PetType>();
		list.add(PetType.PUFFERFISH);
		list.add(PetType.MR_PENGUIN);
		list.add(PetType.GARFIELD);
		list.add(PetType.SILVERFISH);
		list.add(PetType.GODZILLA);
		return list;
	}
	
	static public ArrayList<PetType> getEpics(){
		ArrayList<PetType> list = new ArrayList<PetType>();
		list.add(PetType.MYSTERY_MAGMA_SLIME);
		list.add(PetType.PIKACHU);
		list.add(PetType.WITHER_SKELETON);
		return list;
	}
	
	static public ArrayList<PetType> getLegendaries(){
		ArrayList<PetType> list = new ArrayList<PetType>();
		list.add(PetType.REAPER);
		list.add(PetType.RAINBOW_PEPE);
		list.add(PetType.WITHER_SKELETON_KING);
		list.add(PetType.VAMPIRE);
		list.add(PetType.FIRE_DEMON);
		return list;
	}
	
	static public ArrayList<PetType> getPetsByTier(Tier tier){
		if (tier.equals(Tier.COMMON)) return getCommons();
		if (tier.equals(Tier.RARE)) return getRares();
		if (tier.equals(Tier.EPIC)) return getEpics();
		if (tier.equals(Tier.LEGENDARY)) return getLegendaries();
		return null;
	}
	
}

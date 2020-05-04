package com.chickenstyle.minions.Enums;

import com.chickenstyle.minions.Utils;

public enum Tier {
	LEGENDARY("&6&lLegendary"),
	EPIC("&d&lEpic"),
	RARE("&e&lRare"),
	COMMON("&a&lCommon");
	
	private String name;
	
	Tier(String name) {
		this.name = name;
	}
	
	public String getName() {
		return Utils.Color(this.name);
	}
}

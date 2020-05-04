package com.chickenstyle.minions.Enums;

public enum Pets {
	WITHER_SKELETON_KING(false,"&8&lWither Skeleton King",Tier.LEGENDARY, "68c0165e9b2dbd78dac91277e97d9a02648f3059e126a5941a84d05429ce"),
	FIRE_DEMON(true,"&4&lFire Demon",Tier.LEGENDARY, "4903221124e1a27402276c3e0176bc9f63238d7ea7713e9b579a878daca24819"),
	VAMPIRE(false,"&4&lVampire",Tier.LEGENDARY, "8d44756e0b4ece8d746296a3d5e297e1415f4ba17647ffe228385383d161a9"),
	RAINBOW_PEPE(false,"&c&lR&6&la&a&li&b&ln&9&lb&d&lo&c&lw &6&lP&a&le&b&lp&9&le",Tier.LEGENDARY, "1e42ce5a8033247572b7603a7989455006c807f29a58233a0d9afdb5a46365a2"),
	REAPER(true,"&7&lReaper",Tier.LEGENDARY, "9998a226568d45faf77785890564a897aa58d151a5303989732d9f5ea0aae6c3"),
	PIKACHU(true,"&e&lPikachu",Tier.EPIC, "dfa695b59618b3616b6aaa910c5a10146195edd6367d25e9399a74ceef966"),
	WITHER(true,"&8&lWither",Tier.EPIC, "7953b6c68448e7e6b6bf8fb273d7203acd8e1be19e81481ead51f45de59a8"),
	MYSTERY_MAGMA_SLIME(true,"&c&lMystery Magma Slime",Tier.EPIC, "61bcde8f0edc02dd77e2cead48780026bd11c1db02f07af967956d468ee5d0e8"),
	GODZILLA(true,"&8&lGodzilla",Tier.RARE, "e3a5565ecf1097ffd3533165729a07f903a3e8b6d4a3021cbfda14c1aed67a50"),
	SILVERFISH(true,"&7&lSilverfish",Tier.RARE, "92ec2c3cb95ab77f7a60fb4d160bced4b879329b62663d7a9860642e588ab210"),
	GARFIELD(false,"&6&lGarfield",Tier.RARE, "ebc1a83eca37249a28e427c541556e78d40b928ea9e1569d4e6d5fac844888"),
	MR_PENGUIN(false,"&6&lMr.Penguin",Tier.RARE, "84da09279307027a4a57cb49784ba634b155d51531fba9ed334461e5de140766"),
	PUFFERFISH(true,"&e&lPufferfish",Tier.RARE, "fca298093963ca18f9b6c264196817919068bd4928f3fef1f4583cdfa7a06758"),
	WOLF(true,"&8&lWolf",Tier.COMMON, "dc3dd984bb659849bd52994046964c22725f717e986b12d548fd169367d494"),
	SKELETON(true,"&7&lSkeleton",Tier.COMMON, "d1e7778093d9451456f562a3a578eb9fd8b2be1ee3014ce269e725529b47a8e"),
	SQUID(true,"&9&lSquid",Tier.COMMON, "2fa92a834d5d10e479a22a89ce7597c67099363d9b791055b15cb3ff8b28a0f"),
	RABBIT(true,"&f&lRabbit",Tier.COMMON, "e8a4fb25afbc6b7aec15abe872fcead1e5b33c1ab12525141d77bfd298fc32d9"),
	ZOMBIE(true,"&2&lZombie",Tier.COMMON, "c3fb4e5db97f479c66a42bbd8a7d781daf201a8ddaf77afcf4aef87779aa8b4");
	
	private boolean upgradable;
	private String name;
	private Tier tier;
	private String skin;
	
	private Pets(boolean upgradable, String name,Tier tier,String skin) {
		this.upgradable = upgradable;
		this.name = name;
		this.tier = tier;
		this.skin = skin;
	}
	
	public String getSkin() {
		return skin;
	}
	
	public Tier getTier() {
		return tier;
	}
	
	public boolean isUpgradable() {
		return upgradable;
	}

	public String getName() {
		return name;
	}
	

	
}
	

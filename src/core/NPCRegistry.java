package core;

import java.util.HashMap;
import java.util.Map;

import character.Human;
import character.ICharacter;

public class NPCRegistry {
	private static Map<String, ICharacter> npcs;
	//Eventually make this a Map<String, CharacterTemplate>. Will not do now because ICharacter is subject to change.
	private static Map<String, ICharacter> npcTemplates; 
	
	public static void init() {
		npcs = new HashMap<String, ICharacter>();
		npcTemplates = new HashMap<String, ICharacter>();
		
		// npcs.put(name, name, weight, strength, dexterity, wisdom, intelligence, charisma, constitution, faction);
		npcs.put("Guts", new Human("Guts", 60.0d, 90, 45, 30, 25, 21, 65, "Bank of the Hawk"));
		npcs.put("Griffith", new Human("Griffith", 55.0d, 45, 80, 40, 40, 45, 30, "Bank of the Hawk"));
		npcs.put("Killua", new Human("Killua", 40.0d, 55, 75, 60, 28, 23, 35, "Hunter Association"));
		npcs.put("Gon", new Human("Gon", 40.0d, 50, 80, 70, 15, 32, 80, "Hunter Association"));
		
		//Should add to templates later
		//npcs.put("Bandit", new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		
	}
	
	public static ICharacter getNPC(String name) {
		return npcs.get(name);
	
	}
	/*
	 * Will eventually produce character from template. Will add XP then.
	 */
	public static ICharacter getGenericNPC(String name) {
		return npcTemplates.get(name);
	}
}

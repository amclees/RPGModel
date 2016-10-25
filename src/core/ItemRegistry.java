package core;

import java.util.HashMap;
import java.util.Map;

import character.Equipable;
import inventory.IItem;
import inventory.Item;

public class ItemRegistry {
	private static Map<String, IItem> items;
	
	public static void init() {
		items = new HashMap<String, IItem>();
		
		items.put("Nickel", new Item("Nickel", 0.05d, "A single nickel"));
	}
	
	public static IItem getItem(String name) {
		return items.get(name);
	}
	
	protected static void putEquipment(Equipable e) {
		items.put(e.getName(), e);
	}
}

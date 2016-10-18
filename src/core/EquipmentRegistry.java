package core;

import java.util.HashMap;
import java.util.Map;

import character.Equipable;
import character.EquipmentSlot;
import character.Size;

public class EquipmentRegistry {
	private static Map<String, Equipable> equipment;
	
	public static void init() {
		equipment = new HashMap<String, Equipable>();
		equipment.put("fist", new Equipable("fist", EquipmentSlot.NONE, Size.ANY, 0.0d, 0, 0, 1, 4, 1, "A fist"));
	}
	
	public static Equipable getEquipment(String name) {
		return equipment.get(name);
	}
}

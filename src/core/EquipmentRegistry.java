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
    equipment.put("Gut's Sword", new Equipable("Gut's Sword", EquipmentSlot.RIGHTHAND, Size.MEDIUM, 40.0d, 4, 2, 4, 10,
        1, "A broadsword approximately 2 meters long."));
    equipment.put("Cutlass",
        new Equipable("Cutlass", EquipmentSlot.RIGHTHAND, Size.MEDIUM, 1.8d, 4, 2, 1, 7, 1, "A pirate's cutlass."));
    equipment.put("Bow",
        new Equipable("Bow", EquipmentSlot.RIGHTHAND, Size.MEDIUM, 1.2d, 0, 3, 1, 4, 8, "A roughly crafted bow."));

    for (String name : equipment.keySet()) {
      ItemRegistry.putEquipment(equipment.get(name));
    }
  }

  public static Equipable getEquipment(String name) {
    return equipment.get(name);
  }
}

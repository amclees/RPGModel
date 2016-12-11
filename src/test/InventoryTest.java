package test;

import java.util.List;

import character.ICharacter;
import core.EquipmentRegistry;
import core.ItemRegistry;
import core.MaterialRegistry;
import core.NPCRegistry;
import inventory.IItem;
import inventory.Item;

public class InventoryTest {
	public static void main(String[] args) {
		MaterialRegistry.init();
		ItemRegistry.init();
		EquipmentRegistry.init();
		NPCRegistry.init();
		
		ICharacter h1 = NPCRegistry.getNPC("Guts");
		h1.getInventory().addItem(ItemRegistry.getItem("Nickel"));
		h1.getInventory().addItem(ItemRegistry.getItem("Nickel"));
		h1.getInventory().addItem(ItemRegistry.getItem("Nickel"));
		h1.getInventory().addItem(ItemRegistry.getItem("Nickel"));
		List<IItem> items = h1.getInventory().getItems();
		if(items.get(0) == items.get(1)) {
			System.out.println("Registry copy failed");
		} else {
			System.out.println("Registry copy succeeded");
		}
	}
	
	
}

package test;

import core.ItemRegistry;
import core.MainRegistry;
import inventory.IItem;
import inventory.Inventory;

public class InventoryTest {
	public static void main(String[] args) {
		MainRegistry.init();
		
		Inventory inv = new Inventory();
		IItem nickel = ItemRegistry.getItem("Nickel");
		
		System.out.println("Empty inventory weight: " + inv.getWeight());
		
		inv.addItem(nickel, 2);
		
		System.out.println("Inventory weight with two nickels: " + inv.getWeight());
		System.out.println("Nickels contained with two nickels: " + inv.getQuantity(nickel));
		
		inv.removeItem(nickel, 1);
		
		System.out.println("Inventory weight with 1 nickel: " + inv.getWeight());
		System.out.println("Nickels contained after removing 1 nickel: " + inv.getQuantity(nickel));
		
	}
	
	
}

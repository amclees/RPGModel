package core;

import character.EquipmentSlot;
import character.Human;
import character.ICharacter;
import environment.CombatManager;
import environment.Grid;
import environment.Layer;

public class GridGenerator {
	public static Grid makeGrid() {
		MainRegistry.init();
		Grid grid = new Grid(20, 20);
		ICharacter h1 = NPCRegistry.getNPC("Guts");
		h1.equip(EquipmentRegistry.getEquipment("Gut's Sword"), EquipmentSlot.RIGHTHAND);
		ICharacter h2 = NPCRegistry.getNPC("Griffith");
		ICharacter h3 = NPCRegistry.getNPC("Killua");
		ICharacter h4 = NPCRegistry.getNPC("Gon");
		h4.getInventory().addItem(ItemRegistry.getItem("Nickel"), 7);
		h1.addXP(5000);
		h3.addXP(50000);
		h4.addXP(200);
		grid.setElement(0, 0, Layer.CHARACTER, h1);
		grid.setElement(1, 0, Layer.CHARACTER, h2);
		grid.setElement(3, 4, Layer.CHARACTER, h3);
		grid.setElement(3, 7, Layer.CHARACTER, h4);
		grid.setElement(7, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		int bandits = 20;
		int pirates = 12;
		while(bandits > 0) {
			bandits--;
			boolean empty = false;
			int i, j;
			i = j = 0;
			while(!empty) {
				i = (int)(Math.random() * grid.getWidth());
				j = (int)(Math.random() * grid.getHeight());
				if(grid.getElement(i, j, Layer.CHARACTER) == null) empty = true;
			}
			grid.setElement(i, j, Layer.CHARACTER, new Human("Bandit", 50.0d, 25 + (int)Math.round(Math.random() * 5), 25 + (int)Math.round(Math.random() * 5), 25 + (int)Math.round(Math.random() * 5), 25, 25, 25, "Bandits"));
			if(Math.random() < 0.5) ((ICharacter)grid.getElement(i, j, Layer.CHARACTER)).equip(EquipmentRegistry.getEquipment("Bow"), EquipmentSlot.RIGHTHAND);
		}
		while(pirates > 0) {
			pirates--;
			boolean empty = false;
			int i, j;
			i = j = 0;
			while(!empty) {
				i = (int)(Math.random() * grid.getWidth());
				j = (int)(Math.random() * grid.getHeight());
				if(grid.getElement(i, j, Layer.CHARACTER) == null) empty = true;
			}
			grid.setElement(i, j, Layer.CHARACTER, new Human("Pirate", 50.0d, 25, 25, 25, 25, 25, 25, "Pirates"));
			((ICharacter)grid.getElement(i, j, Layer.CHARACTER)).equip(EquipmentRegistry.getEquipment("Cutlass"), EquipmentSlot.RIGHTHAND);
		}
		return grid;
	}
}

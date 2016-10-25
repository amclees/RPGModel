package test;

import java.util.Scanner;

import character.EquipmentSlot;
import character.Human;
import character.ICharacter;
import core.EquipmentRegistry;
import core.ItemRegistry;
import core.MaterialRegistry;
import core.NPCRegistry;
import environment.CombatManager;
import environment.Grid;
import environment.Layer;

public class SimpleCombatTest {
	public static void main(String[] args) throws InterruptedException {
		MaterialRegistry.init();
		ItemRegistry.init();
		EquipmentRegistry.init();
		NPCRegistry.init();
		
		Grid grid = new Grid(50, 50);
		ICharacter h1 = NPCRegistry.getNPC("Guts");
		h1.equip(EquipmentRegistry.getEquipment("Gut's Sword"), EquipmentSlot.RIGHTHAND);
		ICharacter h2 = NPCRegistry.getNPC("Griffith");
		ICharacter h3 = NPCRegistry.getNPC("Killua");
		ICharacter h4 = NPCRegistry.getNPC("Gon");
		h1.addXP(5000);
		h3.addXP(5000);
		h4.addXP(200);
		grid.setElement(0, 0, Layer.CHARACTER, h1);
		grid.setElement(1, 0, Layer.CHARACTER, h2);
		grid.setElement(3, 4, Layer.CHARACTER, h3);
		grid.setElement(3, 7, Layer.CHARACTER, h4);
		
		
		
		grid.setElement(6, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
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
				j = (int)(Math.random() * grid.getWidth());
				if(grid.getElement(i, j, Layer.CHARACTER) == null) empty = true;
			}
			grid.setElement(i, j, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		}
		while(pirates > 0) {
			pirates--;
			boolean empty = false;
			int i, j;
			i = j = 0;
			while(!empty) {
				i = (int)(Math.random() * grid.getWidth());
				j = (int)(Math.random() * grid.getWidth());
				if(grid.getElement(i, j, Layer.CHARACTER) == null) empty = true;
			}
			grid.setElement(i, j, Layer.CHARACTER, new Human("Pirate", 50.0d, 25, 25, 25, 25, 25, 25, "Pirates"));
			((ICharacter)grid.getElement(i, j, Layer.CHARACTER)).equip(EquipmentRegistry.getEquipment("Cutlass"), EquipmentSlot.RIGHTHAND);;
		}
		
		Human hiro = new Human("Hiro", 45.0d, 70, 40, 30, 25, 25, 25, "Hiro");
		hiro.addXP(5000);
		grid.setElement(14, 17, Layer.CHARACTER, hiro);
		
		
		CombatManager combat = new CombatManager(grid);
		
		Utils.charStatus(grid);
		Utils.gridStatus(grid, Layer.CHARACTER);
		int dur = 5; //Duration in minutes
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < dur * 10; i++) {
			combat.round();
			Utils.charStatus(grid);
			Utils.gridStatus(grid, Layer.CHARACTER);
			//sc.nextLine();
			//if(i % 10 == 0) grid.setElement(6, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		}
		System.out.printf("%n%n");
		Utils.gridStatus(grid, Layer.CORPSE);
		
		
	}
	
}

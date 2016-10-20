package test;

import java.util.Scanner;

import character.Human;
import character.ICharacter;
import core.EquipmentRegistry;
import core.MaterialRegistry;
import core.NPCRegistry;
import environment.CombatManager;
import environment.Grid;
import environment.IGridItem;
import environment.IGridObject;
import environment.Layer;

public class SimpleCombatTest {
	public static void main(String[] args) throws InterruptedException {
		MaterialRegistry.init();
		EquipmentRegistry.init();
		NPCRegistry.init();
		
		Grid grid = new Grid(21, 21);
		ICharacter h1 = NPCRegistry.getNPC("Guts");
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
		
		
		for(int i = 10; i < grid.getWidth(); i++) {
			for(int j = 10; j < grid.getHeight(); j++) {
				if(i % 5 == 0 && j % 5 == 0) grid.setElement(i, j, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
			}
		}
		
		Human hiro = new Human("Hiro", 45.0d, 70, 40, 30, 25, 25, 25, "Hiro");
		hiro.addXP(5000);
		grid.setElement(14, 17, Layer.CHARACTER, hiro);
		
		
		CombatManager combat = new CombatManager(grid);
		
		charStatus(grid);
		gridStatus(grid, Layer.CHARACTER);
		int dur = 30; //Duration in minutes
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < dur * 10; i++) {
			combat.round();
			charStatus(grid);
			gridStatus(grid, Layer.CHARACTER);
			//sc.nextLine();
			//if(i % 10 == 0) grid.setElement(6, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		}
		System.out.printf("%n%n");
		gridStatus(grid, Layer.CORPSE);
		
		
	}
	public static void gridStatus(Grid grid, Layer layer) {
		for(int i = 0; i < grid.getHeight(); i++) {
			for(int j = 0; j < grid.getWidth(); j++) {
				IGridItem item = grid.getElement(j, i, layer);
				System.out.print(" ");
				if(item != null) System.out.print(item.getName().charAt(0));
				else System.out.print("-");
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	public static void charStatus(Grid grid) {
		for(ICharacter c : grid.getCharacters()) {
			System.out.printf("Level " + c.getLevel() + " " + c.getName() + " (GUID " + c.getGUID() + ") is at (" + c.getX() + ", " + c.getY() + ").%n"
					+ "    They have STR " + c.getStrength() + " DEX " + c.getDexterity() + " WIS " + c.getWisdom() + " INT " + c.getIntelligence() + " CHA " + c.getCharisma() + " CON " + c.getConstitution() + ".%n" 
					+ "    They have " + c.getHP() + "/" + c.getMaxHP() + " HP.%n"
					+ "    They have " + c.getXP() + "/" + c.getXPToNextLevel() + " XP to next level.%n"
					+ "    The grid " + (c.equals(grid.getElement(c.getX(), c.getY(), Layer.CHARACTER)) ? "" : "dis") + "agrees with their position.%n"
					+ "    They are made of " + c.getMaterial().getName() + ".%n"
					+ "    Their faction is: " + c.getFaction() + ".%n"
					+ "    Their challenge rating is: " + c.getChallengeRating() + ".%n");
		}
	}
}

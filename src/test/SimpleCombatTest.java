package test;

import character.Human;
import character.ICharacter;
import core.EquipmentRegistry;
import core.MaterialRegistry;
import environment.CombatManager;
import environment.Grid;
import environment.GridItem;
import environment.Layer;

public class SimpleCombatTest {
	public static void main(String[] args) throws InterruptedException {
		MaterialRegistry.init();
		EquipmentRegistry.init();
		Grid grid = new Grid(21, 21);
		Human h1 = new Human("Guts", 60.0d, 90, 40, 30, 25, 21, 65, "Bank of the Hawk");
		Human h2 = new Human("Griffith", 55.0d, 45, 80, 40, 40, 45, 30, "Bank of the Hawk");
		Human h3 = new Human("Killua", 40.0d, 55, 75, 60, 28, 23, 35, "Hunter Association");
		grid.setElement(0, 0, Layer.CHARACTER, h1);
		grid.setElement(1, 0, Layer.CHARACTER, h2);
		grid.setElement(3, 4, Layer.CHARACTER, h3);
		for(ICharacter c : grid.getCharacters()) {
			System.out.println(c.getName() + " is with the " + c.getFaction());
		}
		
		
		
		grid.setElement(6, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		grid.setElement(7, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		grid.setElement(8, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		grid.setElement(6, 7, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		grid.setElement(7, 7, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		grid.setElement(7, 8, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		grid.setElement(7, 9, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		grid.setElement(7, 4, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
		
		for(int i = 10; i < grid.getWidth(); i++) {
			for(int j = 10; j < grid.getHeight(); j++) {
				if(i % 5 == 0 && j % 5 == 0) grid.setElement(i, j, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
			}
		}
		
		grid.setElement(14, 17, Layer.CHARACTER, new Human("Hiro", 45.0d, 70, 40, 30, 25, 25, 25, "Hiro"));
		
		CombatManager combat = new CombatManager(grid);
		
		charStatus(grid);
		gridStatus(grid);
		int dur = 2; //Duration in minutes
		for(int i = 0; i < dur * 10; i++) {
			combat.round();
			charStatus(grid);
			gridStatus(grid);
			//Thread.sleep(1500);
		}
		
	}
	public static void gridStatus(Grid grid) {
		for(int i = 0; i < grid.getHeight(); i++) {
			for(int j = 0; j < grid.getWidth(); j++) {
				GridItem item = grid.getElement(j, i, Layer.CHARACTER);
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
			System.out.printf(c.getName() + " is at (" + c.getX() + ", " + c.getY() + ").%n"
					+ "    They have " + c.getHP() + "/" + c.getMaxHP() + " HP.%n"
					+ "    The grid " + (c.equals(grid.getElement(c.getX(), c.getY(), Layer.CHARACTER)) ? "" : "dis") + "agrees with their position.%n"
					+ "    They are made of " + c.getMaterial().getName() + "%n");
		}
	}
}

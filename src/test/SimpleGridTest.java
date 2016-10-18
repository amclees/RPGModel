package test;

import character.Human;
import character.ICharacter;
import core.MaterialRegistry;
import environment.Grid;
import environment.Layer;

public class SimpleGridTest {
	public static void main(String[] args) {
		MaterialRegistry.init();
		Grid grid = new Grid(10, 10);
		Human h1 = new Human("Guts", 60.0d, 90, 40, 30, 25, 21, 65, "Bank of the Hawk");
		Human h2 = new Human("Griffith", 55.0d, 45, 80, 40, 40, 45, 30, "Band of the Hawk");
		Human h3 = new Human("Killua", 40.0d, 55, 75, 60, 28, 23, 35, "Hunter Association");
		grid.setElement(0, 0, Layer.CHARACTER, h1);
		grid.setElement(1, 0, Layer.CHARACTER, h2);
		grid.setElement(3, 4, Layer.CHARACTER, h3);
		
		grid.moveElement(0, 0, 0, 1, Layer.CHARACTER);
		
		grid.removeElement(1, 0, Layer.CHARACTER);
		
		for(ICharacter c : grid.getCharacters()) {
			System.out.printf(c.getName() + " is present in the grid at " + c.getX() + "-" + c.getY() + ".%n"
					+ "  Their GUID is " + c.getGUID() + "%n"
					+ "  They are made of " + c.getMaterial().getName() + "%n"
					+ "  Their HP is " + c.getHP() + "%n");
			
		}
		
		ICharacter strongest = null;
		for(ICharacter c : grid.getCharacters()) {
			if(strongest == null) {
				strongest = c;
			} else if(strongest.getStrength() < c.getStrength()) {
				strongest = c;
			}
		}
		System.out.println(strongest.getName() + " is the strongest. They have " + strongest.getStrength() + " STR.");
	}
}

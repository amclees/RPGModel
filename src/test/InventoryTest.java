package test;

import character.ICharacter;
import core.EquipmentRegistry;
import core.ItemRegistry;
import core.MaterialRegistry;
import core.NPCRegistry;
import environment.Grid;
import environment.IGridItem;
import environment.Layer;

public class InventoryTest {
	public static void main(String[] args) {
		MaterialRegistry.init();
		ItemRegistry.init();
		EquipmentRegistry.init();
		NPCRegistry.init();
		
		Grid grid = new Grid(21, 21);
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

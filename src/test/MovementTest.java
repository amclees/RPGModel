package test;

import character.Human;
import character.ICharacter;
import core.EquipmentRegistry;
import core.RegistryManager;
import core.MaterialRegistry;
import environment.CombatManager;
import environment.Grid;
import environment.Layer;
import gui.TextDisplay;
import javafx.scene.text.Text;

public class MovementTest {

	public static void main(String[] args) {
		RegistryManager.init();
		Grid grid = new Grid(10, 10);
		Human h1 = new Human("Guts", 60.0d, 90, 40, 30, 25, 21, 65, "Bank of the Hawk");
		Human h2 = new Human("Griffith", 55.0d, 45, 80, 40, 40, 45, 30, "Band of the Hawk");
		Human h3 = new Human("Killua", 40.0d, 55, 75, 60, 28, 23, 35, "Hunter Association");
		grid.setElement(0, 0, Layer.CHARACTER, h1);
		grid.setElement(1, 0, Layer.CHARACTER, h2);
		grid.setElement(3, 4, Layer.CHARACTER, h3);
		
		CombatManager combat = new CombatManager(grid, new TextDisplay(new Text()));
		
		//combat.round();
		
		for(ICharacter c : grid.getCharacters()) {
			System.out.printf("Character " + c.getName() + " is at x=" + c.getX() + " and y=" + c.getY() + "%n"
					+ "  The character at their XY has the name " + grid.getElement(c.getX(), c.getY(), Layer.CHARACTER).getName() + "%n");
		}
		
		combat.round();
		combat.round();
		combat.round();
		combat.round();
		
		for(ICharacter c : grid.getCharacters()) {
			System.out.printf("Character " + c.getName() + " is at x=" + c.getX() + " and y=" + c.getY() + "%n"
					+ "  The character at their XY has the name " + grid.getElement(c.getX(), c.getY(), Layer.CHARACTER).getName() + "%n");
		}
	}

}

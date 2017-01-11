package test;

import character.Human;
import core.RegistryManager;
import core.MaterialRegistry;
import environment.CombatManager;
import environment.Grid;
import environment.Layer;
import gui.TextDisplay;
import javafx.scene.text.Text;

public class TurnOrderTest {

	public static void main(String[] args) {
		RegistryManager.init();
		Grid grid = new Grid(100, 100);
		Human h1 = new Human("Guts", 60.0d, 90, 40, 30, 25, 21, 65, "Bank of the Hawk");
		Human h2 = new Human("Griffith", 55.0d, 45, 80, 40, 40, 45, 30, "Band of the Hawk");
		Human h3 = new Human("Killua", 40.0d, 55, 75, 60, 28, 23, 35, "Hunter Association");
		grid.setElement(0, 0, Layer.CHARACTER, h1);
		grid.setElement(1, 0, Layer.CHARACTER, h2);
		grid.setElement(3, 4, Layer.CHARACTER, h3);
		for(int i = 0; i < grid.getWidth(); i++) {
			for(int j = 0; j < grid.getHeight(); j++) {
				grid.setElement(i, j, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandit"));
			}
		}
		
		CombatManager combat = new CombatManager(grid, new TextDisplay(new Text()));
		combat.round();
		
	}

}

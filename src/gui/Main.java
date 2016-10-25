package gui;

import character.EquipmentSlot;
import character.ICharacter;
import core.EquipmentRegistry;
import core.MainRegistry;
import core.NPCRegistry;
import environment.Grid;
import environment.Layer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MainRegistry.init();
		
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
		
		GridDisplay gridDisplay = new GridDisplay(grid);
		
		stage.setTitle("RPG Model");

		BorderPane main = new BorderPane();
		main.setCenter(gridDisplay.getPane());
		
        stage.setScene(new Scene(main, 300, 300));
        stage.show();
	}

}

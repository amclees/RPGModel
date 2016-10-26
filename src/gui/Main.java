package gui;


import character.EquipmentSlot;
import character.Human;
import character.ICharacter;
import core.EquipmentRegistry;
import core.MainRegistry;
import core.NPCRegistry;
import environment.CombatManager;
import environment.Grid;
import environment.Layer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * TODO Use a style sheet to format this
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MainRegistry.init();
		VBox main = new VBox();
		main.setAlignment(Pos.CENTER);
		
		Grid grid = new Grid(100, 100);
		CombatManager combat = new CombatManager(grid);
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
		
		GridDisplay gridDisplay = new GridDisplay(grid);
		
		Button nextRound = new Button("Next Round");
		nextRound.setAlignment(Pos.TOP_CENTER);
	       
        nextRound.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	 combat.round();
                 main.getChildren().set(1, gridDisplay.getPane());
            }
        });
        main.getChildren().add(nextRound);
		main.getChildren().add(gridDisplay.getPane());
		
		
		ScrollPane scroll = new ScrollPane(main);
		scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		stage.setTitle("RPG Model");
        stage.setScene(new Scene(scroll, 1920, 1080));
        stage.setMaximized(true);
        stage.show();
        
        
	}

}

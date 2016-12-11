package gui;

import character.ICharacter;
import environment.Grid;
import environment.Layer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class GridDisplay {
	private Grid grid;
	private Main main;
	private GridPane gridPane;
	
	public GridDisplay(Grid grid, Main main) {
		this.grid = grid;
		this.main = main;
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		for(int i = 0; i < grid.getWidth(); i++) {
			for(int j = 0; j < grid.getHeight(); j++) {
				gridPane.add(getNode(i, j), i, j);
			}
		}
		this.gridPane = gridPane;
	}
	
	public void update() {
		this.gridPane.getChildren().removeAll(this.gridPane.getChildren());
		for(int i = 0; i < grid.getWidth(); i++) {
			for(int j = 0; j < grid.getHeight(); j++) {
				gridPane.add(getNode(i, j), i, j);
			}
		}
	}
	
	public Node getPane() {
		return gridPane;
	}
	
	private Node getNode(int x, int y) {
		Label node = new Label("");
		node.setPrefWidth(75);
		node.setPrefHeight(75);
		node.setStyle("-fx-padding: 5px; -fx-font-size: 12px;");
		node.setAlignment(Pos.CENTER);
		node.setMinSize(node.getPrefWidth(), node.getPrefHeight());
		ICharacter character = (ICharacter)grid.getElement(x, y, Layer.CHARACTER);
		if(character != null) {
			node.setText(character.getName());
			node.setOnMouseClicked(e -> {
				main.detailDisplay.display(x, y);
				main.updateDetail();
			});
		}
		return node;
	}
}

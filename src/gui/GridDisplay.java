package gui;

import character.ICharacter;
import environment.Grid;
import environment.Layer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class GridDisplay {
	private Grid grid;
	
	public GridDisplay(Grid grid) {
		this.grid = grid;
	}
	
	public GridPane getPane() {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		for(int i = 0; i < grid.getWidth(); i++) {
			for(int j = 0; j < grid.getHeight(); j++) {
				gridPane.add(getNode(i, j), i, j);
			}
		}
		return gridPane;
	}
	
	private Node getNode(int x, int y) {
		Label node = new Label("");
		node.setPrefWidth(75);
		node.setPrefHeight(75);
		node.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
		node.setAlignment(Pos.CENTER);
		ICharacter character = (ICharacter)grid.getElement(x, y, Layer.CHARACTER);
		if(character != null) {
			node.setText(character.getName().substring(0, 2));
		} 
		return node;
	}
}

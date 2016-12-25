package gui;

import character.ICharacter;
import environment.Grid;
import environment.Layer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GridDisplay {
	private Grid grid;
	private Main main;
	private GridPane gridPane;
	private int selectedX;
	private int selectedY;
	
	public GridDisplay(Grid grid, Main main) {
		selectedX = 0;
		selectedY = 0;
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
	
	public void setSelected(int x, int y) {
		this.selectedX = x;
		this.selectedY = y;
		this.update();
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
			if(this.selectedX == x && this.selectedY == y) {
				node.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}
			node.setText(character.getName());
			node.setOnMouseClicked(e -> {
				this.setSelected(x, y);
				main.detailDisplay.display(x, y);
				main.updateDetail();
			});
		}
		return node;
	}
}

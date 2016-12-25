package gui;

import java.io.File;

import character.ICharacter;
import environment.Grid;
import environment.Layer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DetailDisplay {
	private Grid grid;
	private Node node;
	private int x;
	private int y;
	private ICharacter character;
	
	public ICharacter getCharacter() {
		return character;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public DetailDisplay(Grid grid) {
		this.grid = grid;
		this.node = new Label("");
	}
	
	public void display(int x, int y) {
		HBox display = new HBox();
		display.setAlignment(Pos.CENTER);
		
		this.x = x;
		this.y = y;
		Text text = new Text();
		text.setFont(new Font(18));
		text.setWrappingWidth(400);
		text.setTextAlignment(TextAlignment.LEFT);
		character = (ICharacter)grid.getElement(x, y, Layer.CHARACTER);
		if(character == null) text.setText("No Character");
		else {
			text.setText("Level " + character.getLevel() + " " + character.getName() + "\n"
					+ character.getHP() + " / " + character.getMaxHP() + " HP\n"
					+ character.getFaction() + "\n"
					+ "STR " + character.getStrength() 
					+ " DEX " + character.getDexterity() 
					+ " INT " + character.getIntelligence() 
					+ " WIS " + character.getWisdom() 
					+ " CON " + character.getConstitution()
					+ " CHA " + character.getCharisma() + "\n"
					+ "GUID " + character.getGUID());
		}
		
		text.setText("Viewing (" + x + ", " + y + ")\n" + text.getText());
		
		try {
			File file = new File("");
			Image image = new Image(file.getAbsolutePath());
        	ImageView imageView = new ImageView(image);
        	display.getChildren().add(imageView);
		} catch(Exception e) {}	
		display.getChildren().add(text);
		
		
		Node inventory = new InventoryDisplay(character.getInventory()).getNode();
		
		display.getChildren().add(inventory);
		
		this.node = display;
	}
	
	public void display(ICharacter character) throws NullPointerException {
		this.display(character.getX(), character.getY());
	}
	
	public Node getNode() {
		return this.node;
	}
}

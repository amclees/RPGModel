package gui;

import inventory.IItem;
import inventory.Inventory;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InventoryDisplay {
	private Inventory inventory;
	private Node display;
	
	public InventoryDisplay(Inventory inventory) {
		this.inventory = inventory;
		this.update();
	}
	
	public void update() {
		String invDis = "";
		for(IItem item : inventory.getItems()) {
			invDis += inventory.getQuantity(item) + " of GUID " + item.getGUID() + " " + item.getName() 
			+ " with description: " + item.getDescription() + "\n";
		}
		
		Text text = new Text(invDis);
		
		text.setFont(new Font(18));
		//text.setWrappingWidth(400);
		text.setTextAlignment(TextAlignment.LEFT);
		
		ScrollPane scroll = new ScrollPane(text);
		scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		this.display = scroll;
	}
	
	public Node getNode() {
		return this.display;
	}
}

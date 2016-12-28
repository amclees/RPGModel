package gui;

import java.util.ArrayList;
import java.util.List;

import inventory.IItem;
import inventory.Inventory;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InventoryDisplay {
	private Inventory inventory;
	private Node display;
	private List<ItemDisplay> itemDisplays;
	
	public InventoryDisplay(Inventory inventory) {
		this.inventory = inventory;
		this.update();
	}
	
	public void update() {
	  this.itemDisplays = new ArrayList<ItemDisplay>(this.inventory.size());
	  VBox itemList = new VBox();
	  
		
		for(IItem item : inventory.getItems()) {
			ItemDisplay display = new ItemDisplay(item, inventory.getQuantity(item));		
			itemList.getChildren().add(display.getNode());
			itemDisplays.add(display);
		}
		
		ScrollPane scroll = new ScrollPane(itemList);
		scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		scroll.setPadding(new Insets(10));
		
		this.display = scroll;
	}
	
	public Node getNode() {
		return this.display;
	}
}

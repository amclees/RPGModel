package inventory;

import java.util.LinkedList;
import java.util.List;

public class Inventory {
	private List<IItem> inventory;
	
	public Inventory() {
		inventory = new LinkedList<IItem>();
	}
	
	public void addItem(IItem item) {
		inventory.add(item);
	}
	
	public IItem getItem(int index) {
		return inventory.get(index);
	}
	
	public List<IItem> getItems() {
		return this.inventory;
	}
}

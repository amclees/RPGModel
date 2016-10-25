package inventory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import environment.IGridItem;

public class Inventory extends Item {
	private List<IItem> inventory;
	
	public Inventory(String name) {
		super(name, 0.0d, "");
		inventory = new LinkedList<IItem>();
	}
	
	public void addItem(IItem item) {
		inventory.add(item);
	}
	
	public void removeItem(IItem item) {
		inventory.remove(item);
	}
	
	public void removeItem(int index) {
		inventory.remove(index);
	}
	
	public IItem getItem(int index) {
		return inventory.get(index);
	}
	
	public List<IItem> getItems() {
		return this.inventory;
	}
}

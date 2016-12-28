package inventory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import environment.IGridItem;

public class Inventory implements Serializable {
	private static final long serialVersionUID = -1576521236862039739L;
	private List<IItem> inventory;
	private Map<IItem, Integer> quantity;
	
	public Inventory() {
		inventory = new LinkedList<IItem>();
		quantity = new HashMap<IItem, Integer>();
	}
	
	public void addItem(IItem item, int quantity) {
		if(!inventory.contains(item)) {
			inventory.add(item);
			this.quantity.put(item, quantity);
		} else {
			this.quantity.put(item, this.quantity.get(item) + quantity);
		}
	}
	
	public void removeItem(IItem item, int quantity) {
		this.quantity.put(item, this.quantity.get(item) - quantity);
		if(this.quantity.get(item) <= 0) {
			this.quantity.remove(item);
			inventory.remove(item);
		}
	}
	
	public IItem getItem(int index) {
		return inventory.get(index);
	}
	
	//0 if no key
	public int getQuantity(IItem item) {
		int toReturn = quantity.get(item);
		if(!quantity.containsKey(item)) return 0;
		return toReturn;
	}
	
	//Returns -1 if not found
	public int indexOf(IItem item) {
		return inventory.indexOf(item);
	}
	
	public boolean hasItem(IItem item) {
		if(inventory.contains(item)) return true;
		else return false;
	}
	
	/**
	 * Returns true if the inventory has AT LEAST the quantity
	 */
	public boolean hasQuantity(IItem item, int quantity) {
		int realQuantity = this.getQuantity(item);
		return realQuantity >= quantity;
	}
	
	public List<IItem> getItems() {
		return this.inventory;
	}
	
	public double getWeight() {
		double weight = 0.0d;
		for(IItem item : this.inventory) {
			weight += this.quantity.get(item) * item.getWeight();
		}
		return weight;
	}
	
	public int size() {
	  return this.getItems().size();
	}
}

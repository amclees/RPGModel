package inventory;

import java.util.HashMap;
import java.util.Map;

public class Item implements IItem {
	private Map<String, Object> properties;
	private String name;
	private String description;
	private double weight;
	
	
	public Item(String name, Double weight, String description) { //Add params from equipable  to remove errors
		this.properties = new HashMap<String, Object>();
		this.name = name;
		this.description = description;
		this.weight = weight;
		
	}
	
	public Item(IItem toCopy) {
		this.name = toCopy.getName();
		this.description = toCopy.getDescription();
		this.weight = toCopy.getWeight();
	}
	
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setProperty(String key, Object value) {
		properties.remove(key);
		properties.put(key, value);
	}
	
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	public String getDescription() {
		return description;
	}
	
	
}

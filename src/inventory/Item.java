package inventory;

import java.util.HashMap;
import java.util.Map;

public class Item implements IItem {
	private Map<String, Object> properties;
	private String name;
	
	
	public Item(String name) { //Add params from equipable  to remove errors
		this.properties = new HashMap<String, Object>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setProperty(String key, Object value) {
		properties.remove(key);
		properties.put(key, value);
	}
	
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	
}

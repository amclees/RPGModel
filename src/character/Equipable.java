package character;

import java.util.Map;

import core.Entity;

public class Equipable implements Entity {
	
	public Equipable(String name, EquipmentSlot slot, Size size, int aCBonus, int dexPenalty, int attackDice, int diceSides, int range,
			String description) {
		this.name = name;
		this.slot = slot;
		this.size = size;
		ACBonus = aCBonus;
		this.dexPenalty = dexPenalty;
		this.attackDice = attackDice;
		this.diceSides = diceSides;
		this.range = range;
		this.description = description;
	}

	private String name;
	private EquipmentSlot slot;
	private Size size;
	private int ACBonus;
	private int dexPenalty;
	private int attackDice;
	private int diceSides;
	private String description;
	private int range;
	private Map<String, Object> properties;
	
	public EquipmentSlot getEquipmentSlot() {
		return this.slot;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setProperty(String key, Object value) {
		properties.remove(key);
		properties.put(key, value);
	}
	
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	public Size getSize() {
		return size;
	}
	public int getACBonus() {
		return ACBonus;
	}
	public int getDexPenalty() {
		return dexPenalty;
	}
	public int getAttackDice() {
		return attackDice;
	}
	public int getDiceSides() {
		return diceSides;
	}

	public String getName() {
		return name;
	}
	
	
	

}

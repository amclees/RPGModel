package character;

import inventory.Item;

import java.util.HashMap;
import java.util.Map;

public class Equipable extends Item {
	
	public Equipable(String name, EquipmentSlot slot, Size size, double weight, int aCBonus, int dexPenalty, int attackDice, int diceSides, int range,
			String description) {
		super(name, weight); //Add super params
		this.slot = slot;
		this.size = size;
		ACBonus = aCBonus;
		this.dexPenalty = dexPenalty;
		this.attackDice = attackDice;
		this.diceSides = diceSides;
		this.range = range;
		this.description = description;
		this.weight = weight;
	}

	private double weight;
	private EquipmentSlot slot;
	private Size size;
	private int ACBonus;
	private int dexPenalty;
	private int attackDice;
	private int diceSides;
	private String description;
	private int range;
	
	
	public EquipmentSlot getEquipmentSlot() {
		return this.slot;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getRange() {
		return range;
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

	public double getWeight() {
		return weight;
	}
	
	
	

}

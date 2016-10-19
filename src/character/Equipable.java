package character;

import inventory.Item;

import java.util.HashMap;
import java.util.Map;

public class Equipable extends Item {
	
	public Equipable(String name, EquipmentSlot slot, Size size, double weight, int aCBonus, int dexPenalty, int attackDice, int diceSides, int range,
			String description) {
		super(name, weight, description); //Add super params
		this.slot = slot;
		this.size = size;
		this.ACBonus = aCBonus;
		this.dexPenalty = dexPenalty;
		this.attackDice = attackDice;
		this.diceSides = diceSides;
		this.range = range;
	}

	private EquipmentSlot slot;
	private Size size;
	private int ACBonus;
	private int dexPenalty;
	private int attackDice;
	private int diceSides;
	private int range;
	
	
	public EquipmentSlot getEquipmentSlot() {
		return this.slot;
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
	
	
	

}

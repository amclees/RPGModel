package environment;

import character.Equipable;
import character.EquipmentSlot;
import character.ICharacter;
import core.Dice;
import core.EquipmentRegistry;

public class AttackAction implements Action {

	private ICharacter attacker;
	private GridItem defender;
	private Grid grid;
	private Equipable weapon;
	
	/*
	 * Grid is here for future sophisticated attacks (Ranged that might miss and hit something else)
	 */
	public AttackAction(ICharacter attacker, GridItem defender, Grid grid, EquipmentSlot weapon) {
		this.attacker = attacker;
		this.defender = defender;
		this.grid = grid;
		this.weapon = attacker.getEquipmentInSlot(weapon);
		if(this.weapon == null) {
			this.weapon = EquipmentRegistry.getEquipment("fist");
		}
	}
	
	public void applyAction() {
		if(attacker.attackRoll() > defender.ACRoll()) {
			System.out.println(attacker.getName() + " hits " + defender.getName());
			defender.loseHP(Dice.dx(weapon.getDiceSides(), weapon.getAttackDice()));
		}
		else System.out.println(attacker.getName() + " misses " + defender.getName());
	}
	
}

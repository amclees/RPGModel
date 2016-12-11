package environment;

import character.Equipable;
import character.EquipmentSlot;
import character.ICharacter;
import core.Dice;
import core.EquipmentRegistry;
import gui.TextDisplay;

public class AttackAction implements Action {

	private ICharacter attacker;
	private IGridObject defender;
	private Grid grid;
	private Equipable weapon;
	
	/*
	 * Grid is here for future sophisticated attacks (Ranged that might miss and hit something else)
	 */
	public AttackAction(ICharacter attacker, IGridObject defender, Grid grid, EquipmentSlot weapon) {
		this.attacker = attacker;
		this.defender = defender;
		this.grid = grid;
		this.weapon = attacker.getEquipmentInSlot(weapon);
		if(this.weapon == null) {
			this.weapon = EquipmentRegistry.getEquipment("fist");
		}
	}
	
	public void applyAction(TextDisplay out) {
		if(attacker.attackRoll() > defender.ACRoll()) {
			int dmg = Dice.dx(weapon.getDiceSides(), weapon.getAttackDice());
			out.print(attacker.getName() + " hits " + defender.getName() + " for " + dmg + " damage with a " + weapon.getAttackDice() + "d" + weapon.getDiceSides());
			defender.loseHP(dmg);
			try {
				if(defender.getHP() < 0) attacker.addXP(((ICharacter)defender).getChallengeRating());
			} catch(ClassCastException e) {}
		}
		else out.print(attacker.getName() + " misses " + defender.getName());
	}
	
}

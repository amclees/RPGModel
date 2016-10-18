package character;

import java.util.List;

import environment.Action;
import environment.Grid;
import environment.GridItem;

public interface ICharacter extends GridItem {
	public List<Equipable> getEquipment();
	public Equipable getEquipmentInSlot(EquipmentSlot slot);
	public Size getSize();
	public String getFaction();
	
	public int getStrength();
	public int getDexterity();
	public int getIntelligence();
	public int getWisdom();
	public int getCharisma();
	public int getConstitution();
	
	public void addXP(int xp);
	public int getLevel();
	public int getChallengeRating();
	
	public int attackRoll();
	public int getSpeed();
	public int initiativeRoll();
	public Action getCombatAction(Grid grid);
	
}

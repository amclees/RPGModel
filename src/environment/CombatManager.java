package environment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import character.ICharacter;
import test.SimpleCombatTest;

public class CombatManager {
	private Grid grid;
	
	public CombatManager(Grid grid) {
		this.grid = grid;
	}
	
	public void round() {
		List<ICharacter> characters = getTurnOrder();
		for(ICharacter actor : characters) {
			Action action = actor.getCombatAction(grid);
			if(action != null) {
				action.applyAction();
				//SimpleCombatTest.gridStatus(grid);
			}
		}
	}
	
	/*
	 * This method could be improved in performance.
	 */
	private List<ICharacter> getTurnOrder() {
		Map<ICharacter, Integer> initiatives = new HashMap<ICharacter, Integer>();
		for(ICharacter c : grid.getCharacters()) {
			initiatives.put(c, c.initiativeRoll());
		}
		
		List<ICharacter> toReturn = new LinkedList<ICharacter>();
		for(ICharacter c : grid.getCharacters()) {
			int index = 0;
			for(ICharacter r : toReturn) {
				if(initiatives.get(r) > initiatives.get(c)) {
					index++;
				}
			}
			toReturn.add(index, c);
		}
		
		return toReturn;
	}
}

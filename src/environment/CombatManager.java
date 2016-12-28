package environment;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import character.ICharacter;
import gui.TextDisplay;

public class CombatManager {
  private Grid grid;
  private TextDisplay display;
  int round;

  public CombatManager(Grid grid, TextDisplay display) {
    this.grid = grid;
    this.display = display;
    this.round = 1;
  }

  public void round() {
    display.print("\nRound " + round++ + "\n");
    Queue<ICharacter> characters = getTurnOrder();
    while (!characters.isEmpty()) {
      ICharacter actor = characters.poll();
      Action action = actor.getCombatAction(grid);
      if (action != null) {
        action.applyAction(this.display);
        // SimpleCombatTest.gridStatus(grid);
      }
    }
  }

  public void round(int times) {
    for (int i = 0; i < times; i++) {
      this.round();
    }
  }

  // This method's performance issues are fixed
  private Queue<ICharacter> getTurnOrder() {
    Map<ICharacter, Integer> initiatives = new HashMap<ICharacter, Integer>();
    Queue<ICharacter> toReturn = new PriorityQueue<ICharacter>(new TurnComparator(initiatives));
    for (ICharacter c : grid.getCharacters()) {
      initiatives.put(c, c.initiativeRoll());
      toReturn.add(c);
    }

    return toReturn;
  }

  private class TurnComparator implements Comparator<ICharacter> {
    Map<ICharacter, Integer> initiatives;

    public TurnComparator(Map<ICharacter, Integer> initiatives) {
      this.initiatives = initiatives;
    }

    @Override
    public int compare(ICharacter c1, ICharacter c2) {
      return initiatives.get(c2) - initiatives.get(c1);
    }

  }
}

package environment;

import character.ICharacter;
import gui.TextDisplay;

public class MoveAction implements Action {
  private ICharacter actor;
  private Grid grid;
  private Path path;

  public MoveAction(ICharacter actor, Grid grid, Path path) {
    this.actor = actor;
    this.grid = grid;
    this.path = path;
  }

  /*
   * Attack of Opportunity will be handled in Grid neighbor update 
   */
  public void applyAction(TextDisplay out) {
    Path toApply = path.getShortenedPath(actor.getSpeed());
    //System.out.println(toApply.getLength() + " short path length, " + path.getLength() + " full path length");
    while(toApply.getSteps().size() > 0) {
      int[] step = toApply.pop();
      int dX = step[0];
      int dY = step[1];
      //System.out.println(actor.getName() + " stepped to " + dX + ", "+ dY);
      grid.moveElement(actor.getX(), actor.getY(), dX, dY, Layer.CHARACTER);
    }
    out.print(actor.getName() + " moved to (" + actor.getX() + ", " + actor.getY() + ")");
  }

}

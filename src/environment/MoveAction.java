package environment;

import java.nio.file.Path;
import java.util.List;

import character.ICharacter;
import gui.TextDisplay;

public class MoveAction implements Action {
	private ICharacter actor;
	private Grid grid;
	private List<int[]> steps;
	
	public MoveAction(ICharacter actor, Grid grid, List<int[]> steps) {
		this.actor = actor;
		this.grid = grid;
		this.steps = steps;
	}
	
	/*
	 * Will need to add attack of opportunity later
	 * Might need a method with an argument for player movement
	 */
	public void applyAction(TextDisplay out) {
		for(int[] step : steps) {
			int dX = step[0];
			int dY = step[1];
			grid.moveElement(actor.getX(), actor.getY(), dX, dY, Layer.CHARACTER);
		}
		out.print(actor.getName() + " moved to (" + actor.getX() + ", " + actor.getY() + ")");
	}
	
	

}

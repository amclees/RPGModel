package environment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//TODO Cache utility method results
public class Path {
	private List<int[]> steps;
	private Grid grid;
	
	public Path(Grid grid, List<int[]> steps) {
		this.grid = grid;
		this.steps = new LinkedList<int[]>();
		for(int[] step : steps) {
			this.steps.add(step);
		}
	}
	
	public Path(Grid grid) {
		this(grid, new LinkedList<int[]>());
	}
	
	public List<int[]> getSteps() {
		return this.steps;
	}
	
	public void add(int[] step) {
		this.steps.add(step);
	}
	
	public boolean isClear() { 
		boolean clear = true;
		for(int[] step : steps) {
			clear = clear && this.grid.isClear(step[0], step[1]); 
		}
		return clear;
	}
	
	public int[] pop() {
		int[] step = Arrays.copyOf(steps.get(0), steps.get(0).length);
		steps.remove(0);
		return step;
	}
	
	public int[] getNextStep() {
		return this.steps.get(0);
	}
	
	public boolean isEmpty() {
		return steps.isEmpty();
	}
	
	public int getLength() {
		int length = 0;
		for(int[] step : steps) {
			length += grid.getCost(step[0], step[1]);
		}
		return length;
	}
}

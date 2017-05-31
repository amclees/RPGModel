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
    for (int[] step : steps) {
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
    for (int[] step : steps) {
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

  public int getDistance() {
    int length = 0;
    for (int[] step : steps) {
      length += grid.getCost(step[0], step[1]);
    }
    return length;
  }

  /**
   * Returns a NEW path with newLength as its length or returns a copy of this
   */
  public Path getShortenedPath(int newLength) {
    if (newLength >= this.getDistance())
      return new Path(grid, steps);
    else if (newLength <= 0)
      return new Path(grid);
    else {
      List<int[]> newSteps = new LinkedList<int[]>();
      int newStepCost = 0;
      for (int i = 0; i < this.getSteps().size(); i++) {
        int toAddCost = grid.getCost(this.steps.get(0)[0], this.steps.get(0)[1]);
        if (newStepCost + toAddCost > newLength)
          break;
        else {
          newStepCost += toAddCost;
          newSteps.add(this.steps.get(i)); // Needs to be Array.copy if
                                           // individual steps are ever modified
        }
      }
      return new Path(grid, newSteps);
    }
  }
}

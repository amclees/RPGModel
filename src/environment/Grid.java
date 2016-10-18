package environment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import character.ICharacter;

public class Grid {
	private Map<Layer, GridItem[][]> grid;
	
	public Grid(int width, int height) {
		this.grid = new HashMap<Layer, GridItem[][]>();
		for(Layer layer : Layer.values()) {
			this.grid.put(layer, new GridItem[width][height]);
		}
	}
	
	public int getHeight() {
		return grid.get(Layer.CHARACTER)[0].length;
	}
	
	public int getWidth() {
		return grid.get(Layer.CHARACTER).length;
	}
	
	public GridItem getElement(int x, int y, Layer layer) throws ArrayIndexOutOfBoundsException {
		return grid.get(layer)[x][y];
	}
	
	public void moveElement(int x, int y, int dX, int dY, Layer layer) {
		GridItem[][] items = grid.get(layer);
		if(items[dX][dY] != null || items[x][y] == null) {
			return;
		}
		this.setElement(dX, dY, layer, items[x][y]);
		this.removeElement(x, y, layer);
	}
	
	/*
	 * Limit standard use
	 */
	public void removeElement(int x, int y, Layer layer) {
		grid.get(layer)[x][y] = null;
	}
	public void setElement(int x, int y, Layer layer, GridItem item) {
		item.setX(x);
		item.setY(y);
		grid.get(layer)[x][y] = item;
		//Notify adjacent elements of the change
	}
	
	public List<ICharacter> getCharacters() {
		GridItem[][] grid = this.grid.get(Layer.CHARACTER);
		List<ICharacter> list = new LinkedList<ICharacter>();
		for(GridItem[] row : grid) {
			for(GridItem item : row) {
				try {
					ICharacter c = (ICharacter) item;
					if(c != null) list.add(c);
				} catch(ClassCastException e) {} catch(NullPointerException e) {}
			}
		}
		return list;
	}
	
}

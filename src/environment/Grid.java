package environment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import character.ICharacter;

public class Grid {
	private Map<Layer, IGridItem[][]> grid;
	
	public Grid(int width, int height) {
		this.grid = new HashMap<Layer, IGridItem[][]>();
		for(Layer layer : Layer.values()) {
			this.grid.put(layer, new IGridItem[width][height]); 
		}
	}
	
	public int getHeight() {
		return grid.get(Layer.CHARACTER)[0].length;
	}
	
	public int getWidth() {
		return grid.get(Layer.CHARACTER).length;
	}
	
	/*
	 * Modifications to allow for different storage types go here, need inventory
	 */
	public IGridItem getElement(int x, int y, Layer layer) throws ArrayIndexOutOfBoundsException {
		return grid.get(layer)[x][y];
	}
	
	public void moveElement(int x, int y, int dX, int dY, Layer layer) {
		IGridItem[][] items = grid.get(layer);
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
	public void setElement(int x, int y, Layer layer, IGridItem item) {
		item.setX(x);
		item.setY(y);
		grid.get(layer)[x][y] = item;
		//Notify adjacent elements of the change
	}
	
	public List<ICharacter> getCharacters() {
		IGridItem[][] grid = this.grid.get(Layer.CHARACTER);
		List<ICharacter> list = new LinkedList<ICharacter>();
		for(IGridItem[] row : grid) {
			for(IGridItem item : row) {
				try {
					ICharacter c = (ICharacter) item;
					if(c != null) list.add(c);
				} catch(ClassCastException e) {} catch(NullPointerException e) {}
			}
		}
		return list;
	}
	
}

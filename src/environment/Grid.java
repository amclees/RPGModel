package environment;

import java.util.ArrayList;
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
		for(IGridItem c : this.getNeighbors(x, y, layer)) {
			c.neighborChange(this);
		}
		
	}
	
	public List<IGridItem> getNeighbors(int x, int y, Layer layer) {
		List<IGridItem> neighbors = new ArrayList<IGridItem>(8);
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(!(i == 0 && j == 0)){
					int[] neighbor = {i + x, j + y};
					try {
						if(this.getElement(neighbor[0], neighbor[1], layer) != null) neighbors.add(this.getElement(neighbor[0], neighbor[1], layer));
					} catch(ArrayIndexOutOfBoundsException e) {}
				}			
			}
		}
		return neighbors;
	}
	
	public List<ICharacter> getCharacters() {
		//TODO cache current characters for better performance on sparse grids
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
	
	public boolean hasCharacter(int x, int y) {
		if(this.getElement(x, y, Layer.CHARACTER) == null) return false;
		else return true;
		
	}
	
	public boolean isClear(int x, int y) {
		boolean clear = true;
		Terrain terrain = (Terrain)this.getElement(x, y, Layer.TERRAIN);
		if(terrain != null) clear = clear && terrain.isPassable();
		clear = clear && this.getElement(x, y, Layer.CHARACTER) == null;
		return clear;
	}
	
	//Returns the cost of entering a point, which is 1 plus the terrain cost
	public int getCost(int x, int y) {
		int cost = 1;
		Terrain terrain = (Terrain)this.getElement(x, y, Layer.TERRAIN);
		if(terrain != null) cost += terrain.getMoveCost();
		return cost;
	}
}

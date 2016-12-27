package environment;

import java.util.Map;

import core.Material;

public class Terrain implements IGridItem {
	private static final long serialVersionUID = -5900761463881733825L;
	private int x;
	private int y;
	private Map<String, Object> properties;
	private String name;
	private int moveCost;
	private boolean passable;
	private String description;
	private String imagePath;
	
	public Terrain(int x, int y, String name, String description, boolean passable, int moveCost,
			String imagePath) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.description = description;
		this.passable = passable;
		this.moveCost = moveCost;
		this.imagePath = imagePath;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setProperty(String key, Object value) {
		properties.remove(key);
		properties.put(key, value);
	}
	
	public Object getProperty(String key) {
		return properties.get(key);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void neighborChange(Grid grid) {
		//Custom reaction
	}

	//Never call this
	public double getWeight() {
		return Double.MAX_VALUE * -1;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoveCost() {
		return moveCost;
	}

	public void setMoveCost(int moveCost) {
		this.moveCost = moveCost;
	}
	

}

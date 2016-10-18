package environment;

import core.Entity;
import core.Material;

public interface GridItem extends Entity {
	public double getWeight();
	public int getHP();
	public int getMaxHP();
	public int ACRoll();
	public int getX();
	public int getY();
	void setX(int x);
	void setY(int y);
	public Material getMaterial();
	public void setHP(int hp);
	public void loseHP(int hp);
	
}

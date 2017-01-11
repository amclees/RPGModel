package environment;

import inventory.IItem;
import core.Entity;

public interface IGridObject extends IGridItem {
	public int getHP();
	public int getMaxHP();
	public int ACRoll();
	public Material getMaterial();
	public void setHP(int hp);
	public void loseHP(int hp);
	
}

package environment;

import inventory.IItem;

public interface IGridItem extends IItem {
	public int getX();
	public int getY();
	void setX(int x);
	void setY(int y);
	void neighborChange(Grid grid);
}

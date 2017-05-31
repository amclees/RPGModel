package inventory;

import java.util.HashMap;
import java.util.Map;

import environment.Grid;
import environment.IGridItem;

public class GridInventory extends Inventory implements IGridItem {
  private static final long serialVersionUID = -1576521236862039739L;
  private int x;
  private int y;
  private Map<String, Object> properties;

  public GridInventory(int x, int y) {
    super();
    this.x = x;
    this.y = y;
    this.properties = new HashMap<String, Object>();
  }

  public String getName() {
    return "Inventory at (" + x + ", " + y + ")";
  }

  public void setProperty(String key, Object value) {
    properties.remove(key);
    properties.put(key, value);
  }

  public Object getProperty(String key) {
    return properties.get(key);
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;

  }

  public void setY(int y) {
    this.y = y;
  }

  public void neighborChange(Grid grid) {
  }
}

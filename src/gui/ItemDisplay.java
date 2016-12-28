package gui;

import inventory.IItem;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ItemDisplay {
  private IItem item;
  private int quantity;
  private Node node;

  public ItemDisplay(IItem item, int quantity) {
    this.item = item;
    this.quantity = quantity;
    this.update();
  }

  public Node getNode() {
    return this.node;
  }

  public void update() {
    String invDis = "";
    invDis += quantity + " of GUID " + item.getGUID() + " " + item.getName() + " with description: "
        + item.getDescription() + "\n";
    Text text = new Text(invDis);

    text.setFont(new Font(18));
    // text.setWrappingWidth(400);
    text.setTextAlignment(TextAlignment.LEFT);

    this.node = text;
  }
}

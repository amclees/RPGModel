package core;

import character.EquipmentSlot;
import character.Human;
import character.ICharacter;
import environment.Grid;
import environment.Layer;
import resource.IResourceLoader;
import resource.StaticResourceLoader;

public class GridGenerator {
  public static Grid makeGrid() {
    IResourceLoader source = new StaticResourceLoader();
    RegistryManager registry = new RegistryManager(source);
    Grid grid = new Grid(20, 20);
    ICharacter h1 = registry.getNpcRegistry().get("Bandit Leader 2");
    h1.equip(registry.getEquipmentRegistry().get("Tower Sword"), EquipmentSlot.RIGHTHAND);
    ICharacter h2 = registry.getNpcRegistry().get("Bandit Leader 1");
    ICharacter h3 = registry.getNpcRegistry().get("Blue");
    ICharacter h4 = registry.getNpcRegistry().get("Green");
    h4.getInventory().addItem(registry.getItemRegistry().get("Nickel"), 7);
    h1.addXP(5000);
    h3.addXP(50000);
    h4.addXP(200);
    grid.setElement(0, 0, Layer.CHARACTER, h1);
    grid.setElement(1, 0, Layer.CHARACTER, h2);
    grid.setElement(3, 4, Layer.CHARACTER, h3);
    grid.setElement(3, 7, Layer.CHARACTER, h4);
    grid.setElement(7, 6, Layer.CHARACTER, new Human("Bandit", 50.0d, 25, 25, 25, 25, 25, 25, "Bandits"));
    int bandits = 20;
    int pirates = 12;
    while (bandits > 0) {
      bandits--;
      boolean empty = false;
      int i, j;
      i = j = 0;
      while (!empty) {
        i = (int) (Math.random() * grid.getWidth());
        j = (int) (Math.random() * grid.getHeight());
        if (grid.getElement(i, j, Layer.CHARACTER) == null)
          empty = true;
      }
      grid.setElement(i, j, Layer.CHARACTER, new Human("Bandit", 50.0d, 25 + (int) Math.round(Math.random() * 5),
          25 + (int) Math.round(Math.random() * 5), 25 + (int) Math.round(Math.random() * 5), 25, 25, 25, "Bandits"));
      if (Math.random() < 0.5)
        ((ICharacter) grid.getElement(i, j, Layer.CHARACTER)).equip(registry.getEquipmentRegistry().get("Bow"),
            EquipmentSlot.RIGHTHAND);
    }
    while (pirates > 0) {
      pirates--;
      boolean empty = false;
      int i, j;
      i = j = 0;
      while (!empty) {
        i = (int) (Math.random() * grid.getWidth());
        j = (int) (Math.random() * grid.getHeight());
        if (grid.getElement(i, j, Layer.CHARACTER) == null)
          empty = true;
      }
      grid.setElement(i, j, Layer.CHARACTER, new Human("Pirate", 50.0d, 25, 25, 25, 25, 25, 25, "Pirates"));
      ((ICharacter) grid.getElement(i, j, Layer.CHARACTER)).equip(registry.getEquipmentRegistry().get("Cutlass"),
          EquipmentSlot.RIGHTHAND);
    }
    return grid;
  }
}

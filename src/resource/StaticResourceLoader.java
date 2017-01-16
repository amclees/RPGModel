package resource;

import java.util.HashMap;
import java.util.Map;

import character.Equipable;
import character.EquipmentSlot;
import character.Human;
import character.ICharacter;
import character.Size;
import character.Template;
import environment.Material;
import environment.Terrain;
import inventory.IItem;
import inventory.Item;
import inventory.LootTable;

public class StaticResourceLoader implements IResourceLoader {
  Map<String, ICharacter> characters;
  Map<String, Material> materials;
  Map<String, IItem> items;
  Map<String, Terrain> terrains;
  Map<String, Template> templates;
  Map<String, LootTable> lootTables;
  Map<String, Equipable> equipment;

  public StaticResourceLoader() {
    this.characters = new HashMap<String, ICharacter>();
    this.materials = new HashMap<String, Material>();
    this.items = new HashMap<String, IItem>();
    this.terrains = new HashMap<String, Terrain>();
    this.templates = new HashMap<String, Template>();
    this.lootTables = new HashMap<String, LootTable>();
    this.equipment = new HashMap<String, Equipable>();
  }

  @Override
  public void fetch() {
    materials.put("steel", new Material("steel", 8.0, 0.6, 0.8, 3)); // 8 g/cm3
    // - 3
    // silver/g
    materials.put("flesh", new Material("flesh", 0.985, 0.05, 0.1, 0));

    items.put("Nickel", new Item("Nickel", 0.05d, "A single nickel"));

    equipment.put("Tower Sword", new Equipable("Tower Sword", EquipmentSlot.RIGHTHAND, Size.MEDIUM, 40.0d, 4, 2, 4, 10,
        1, "A broadsword approximately 2 meters long."));
    equipment.put("Cutlass",
        new Equipable("Cutlass", EquipmentSlot.RIGHTHAND, Size.MEDIUM, 1.8d, 4, 2, 1, 7, 1, "A pirate's cutlass."));
    equipment.put("Bow",
        new Equipable("Bow", EquipmentSlot.RIGHTHAND, Size.MEDIUM, 1.2d, 0, 3, 1, 4, 8, "A roughly crafted bow."));

    characters.put("Bandit Leader 2", new Human("Bandit Leader 2", 60.0d, 90, 45, 30, 25, 21, 65, "Bandits"));
    characters.put("Bandit Leader 1", new Human("Bandit Leader", 55.0d, 45, 80, 40, 40, 45, 30, "Bandits"));
    characters.put("Green", new Human("Green", 40.0d, 55, 85, 60, 28, 23, 35, "None"));
    characters.put("Blue", new Human("Blue", 40.0d, 50, 80, 70, 15, 32, 80, "None"));

  }

  public Map<String, ICharacter> getCharacters() {
    return characters;
  }

  public Map<String, Material> getMaterials() {
    return materials;
  }

  public Map<String, IItem> getItems() {
    return items;
  }

  public Map<String, Terrain> getTerrains() {
    return terrains;
  }

  public Map<String, Template> getTemplates() {
    return templates;
  }

  public Map<String, LootTable> getLootTables() {
    return lootTables;
  }

  public Map<String, Equipable> getEquipment() {
    return equipment;
  }

}

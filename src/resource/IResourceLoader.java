package resource;

import java.util.Map;

import character.Equipable;
import character.ICharacter;
import character.Template;
import environment.Material;
import environment.Terrain;
import inventory.IItem;
import inventory.LootTable;

public interface IResourceLoader {
  public void fetch();

  public Map<String, ICharacter> getCharacters();

  public Map<String, Material> getMaterials();

  public Map<String, IItem> getItems();

  public Map<String, Equipable> getEquipment();

  public Map<String, Template> getTemplates();

  public Map<String, LootTable> getLootTables();

  public Map<String, Terrain> getTerrains();
}

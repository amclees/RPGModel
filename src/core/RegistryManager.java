package core;

import character.Equipable;
import character.ICharacter;
import character.Template;
import environment.Material;
import environment.Terrain;
import inventory.IItem;
import inventory.LootTable;
import resource.IResourceLoader;

public class RegistryManager {
  Registry<Material> materialRegistry;
  Registry<IItem> itemRegistry;
  Registry<Equipable> equipmentRegistry;
  Registry<ICharacter> npcRegistry;
  Registry<Template> templateRegistry;
  Registry<LootTable> lootTableRegistry;
  Registry<Terrain> terrainRegistry;

  public RegistryManager(IResourceLoader source) {
    source.fetch();
    this.materialRegistry = new Registry<Material>(source.getMaterials());
    this.itemRegistry = new Registry<IItem>(source.getItems());
    this.equipmentRegistry = new Registry<Equipable>(source.getEquipment());
    this.npcRegistry = new Registry<ICharacter>(source.getCharacters());
    this.templateRegistry = new Registry<Template>(source.getTemplates());
    this.lootTableRegistry = new Registry<LootTable>(source.getLootTables());
    this.terrainRegistry = new Registry<Terrain>(source.getTerrains());
  }

  public Registry<Material> getMaterialRegistry() {
    return materialRegistry;
  }

  public Registry<IItem> getItemRegistry() {
    return itemRegistry;
  }

  public Registry<Equipable> getEquipmentRegistry() {
    return equipmentRegistry;
  }

  public Registry<ICharacter> getNpcRegistry() {
    return npcRegistry;
  }

  public Registry<Template> getTemplateRegistry() {
    return templateRegistry;
  }

  public Registry<LootTable> getLootTableRegistry() {
    return lootTableRegistry;
  }

  public Registry<Terrain> getTerrainRegistry() {
    return terrainRegistry;
  }
}

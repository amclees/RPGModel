package core;

import character.Equipable;
import character.ICharacter;
import character.Template;
import environment.Material;
import environment.Terrain;
import inventory.IItem;
import inventory.LootTable;

public class RegistryManager {
  Registry<Material> materialRegistry;
  Registry<IItem> itemRegistry;
  Registry<Equipable> equipmentRegistry;
  Registry<ICharacter> npcRegistry;
  Registry<Template> templateRegistry;
  Registry<LootTable> lootTableRegistry;
  Registry<Terrain> terrainRegistry;
  
  public RegistryManager() {
    this.materialRegistry = new Registry<Material>();
    this.itemRegistry = new Registry<IItem>();
    this.equipmentRegistry = new Registry<Equipable>();
    this.npcRegistry = new Registry<ICharacter>();
    this.templateRegistry = new Registry<Template>();
    this.lootTableRegistry = new Registry<LootTable>();
    this.terrainRegistry = new Registry<Terrain>();
  }
  
  
  public static void init() {
    MaterialRegistry.init();
    ItemRegistry.init();
    EquipmentRegistry.init();
    NPCRegistry.init();
    TerrainRegistry.init();
    
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

package core;

public class MainRegistry {
  public static void init() {
    MaterialRegistry.init();
    ItemRegistry.init();
    EquipmentRegistry.init();
    NPCRegistry.init();
    TerrainRegistry.init();
  }
}

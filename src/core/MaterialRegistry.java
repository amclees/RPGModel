package core;

import java.util.HashMap;
import java.util.Map;

public class MaterialRegistry {
  private static Map<String, Material> materials;

  public static void init() {
    materials = new HashMap<String, Material>();
    materials.put("steel", new Material("steel", 8.0, 0.6, 0.8, 3)); // 8 g/cm3
                                                                     // - 3
                                                                     // silver/g
    materials.put("flesh", new Material("flesh", 0.985, 0.05, 0.1, 0));
  }

  public static Material getMaterial(String name) {
    return materials.get(name);
  }
}

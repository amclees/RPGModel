package core;

import java.util.HashMap;
import java.util.Map;

public class GUIDFactory {
  private static long GUID = 0;
  private static Map<Entity, Long> lookup = new HashMap<Entity, Long>();

  public static long getGUID(Entity entity) {
    if (lookup.containsKey(entity)) {
      return lookup.get(entity);
    } else {
      long returnGUID = GUID++;
      lookup.put(entity, returnGUID);
      return returnGUID;
    }
  }
}

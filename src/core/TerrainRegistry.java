package core;

import java.util.HashMap;
import java.util.Map;

import environment.Terrain;

public class TerrainRegistry {
	private static Map<String, Terrain> terrain;
	
	public static void init() {
		terrain = new HashMap<String, Terrain>();
		
		
	}
	
	public static Terrain getTerrain(String name) {
		return terrain.get(name);
	}
}

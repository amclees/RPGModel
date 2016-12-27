package core;

import environment.Path;

public class GridUtil {
	
	public static int[] array(int x, int y) {
		int[] array = {x, y};
		return array;
	}
	
	public static int x(int[] coords) {
		return coords[0];
	}
	
	public static int y(int[] coords) {
		return coords[1];
	}
	
}

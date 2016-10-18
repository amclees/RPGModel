package core;

public class GUIDFactory {
	private static long GUID = 0;
	
	public static long getGUID() {
		return GUID++;
	}
}

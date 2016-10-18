package core;

import java.io.Serializable;
import java.util.Map;

public interface Entity extends Serializable {
	public default String getDescription() {
		return "";
	}
	public default long getGUID() {
		return GUIDFactory.getGUID();
	}
	public String getName();
	public Object getProperty(String key);
	public void setProperty(String key, Object value);
	
}

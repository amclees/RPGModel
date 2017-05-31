package core;

import java.util.HashMap;
import java.util.Map;

public class Registry<T> {
  private Map<String, T> map;

  public Registry(Map<String, T> map) {
    this.map = map;
  }

  public Registry() {
    this(new HashMap<String, T>());
  }

  public synchronized void register(String key, T value) {
    this.map.put(key, value);
  }

  public synchronized void register(Map<String, T> values) {
    this.map.putAll(values);
  }

  public T get(String key) {
    return this.map.get(key);
  }

  public String getKey(T value) {
    if (this.map.containsValue(value)) {
      for (String key : this.map.keySet()) {
        if (this.map.get(key) == value)
          return key;
      }
    }
    return null;
  }

  public int getSize() {
    return this.map.size();
  }

  public boolean hasKey(String key) {
    return this.map.keySet().contains(key);
  }

  public boolean hasValue(T value) {
    return this.map.containsValue(value);
  }

}

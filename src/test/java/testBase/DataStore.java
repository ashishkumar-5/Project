package testBase;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
	private Map<String, Object> dataMap = new HashMap<>();

	private static final DataStore instance = new DataStore();

	public static DataStore getInstance() {
		return instance;
	}

	public void store(String key, Object value, boolean updateIfExists) {
		if (!contains(key)) {
			dataMap.put(key, value);
		} else {
			if (updateIfExists) {
				update(key, value);
			}
		}
	}

	public <T> T get(String key) {
		if (dataMap.containsKey(key)) {
			return (T) dataMap.get(key);
		}
		return null;
	}

	public void remove(String key) {
		if (contains(key)) {
			dataMap.remove(key);
		}
	}

	public void update(String key, Object value) {
		dataMap.put(key, value);
	}

	public boolean contains(String key) {
		return dataMap.containsKey(key);
	}

	public void clearAll() {
		dataMap.clear();
	}
}

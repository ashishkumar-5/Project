package testBase;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

	private static ThreadLocal<Map<String, Object>> threadLocalDataMap = ThreadLocal.withInitial(HashMap::new);

	private static final DataStore instance = new DataStore();

	public static DataStore getInstance() {
		return instance;
	}

	public void store(String key, Object value, boolean updateIfExists) {
		Map<String, Object> dataMap = threadLocalDataMap.get();
		if (!dataMap.containsKey(key)) {
			dataMap.put(key, value);
		} else {
			if (updateIfExists) {
				update(key, value);
			}
		}
	}

	public <T> T get(String key) {
		Map<String, Object> dataMap = threadLocalDataMap.get();
		return dataMap.containsKey(key) ? (T) dataMap.get(key) : null;
	}

	public void remove(String key) {
		Map<String, Object> dataMap = threadLocalDataMap.get();
		if (dataMap.containsKey(key)) {
			dataMap.remove(key);
		}
	}

	public void update(String key, Object value) {
		Map<String, Object> dataMap = threadLocalDataMap.get();
		dataMap.put(key, value);
	}

	public boolean contains(String key) {
		Map<String, Object> dataMap = threadLocalDataMap.get();
		return dataMap.containsKey(key);
	}

	public void clearAll() {
		threadLocalDataMap.get().clear();
	}

	public void clearCurrentThreadData() {
		threadLocalDataMap.remove();
	}
}

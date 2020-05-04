package test.caching;

import java.util.HashMap;
import java.util.Map;

public class NaiveCache {
	private Map<Integer, Integer> cache = new HashMap<>();

	public NaiveCache() {
		cache.put(0, 1);
		cache.put(1, 1);
	}

	public Integer getNumber(int index) {
		if (cache.containsKey(index))
			return cache.get(index);

		Integer value = getNumber(index - 1) + getNumber(index - 2);
		cache.put(index, value);
		return value;
	}
}

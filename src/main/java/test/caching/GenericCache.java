package test.caching;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class GenericCache<K, V> {
	private final ConcurrentHashMap<K, Future<V>> cache = new ConcurrentHashMap<>();

	public V getValue(final K key, final Callable<V> callableValue) throws ExecutionException, InterruptedException {
		try {
			final Future<V> future = createFutureIfAbsent(key, callableValue);
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			cache.remove(key);
			throw e;
		}
	}

	public void setValueIfAbsent(final K key, final V value) {
		createFutureIfAbsent(key, new Callable<V>() {
			@Override
			public V call() throws Exception {
				return value;
			}
		});

	}

	private Future<V> createFutureIfAbsent(K key, Callable<V> callableValue) {
		Future<V> future = cache.get(key);
		if (future == null) {
			final FutureTask<V> futureTask = new FutureTask<>(callableValue);
			future = cache.putIfAbsent(key, futureTask);
			if (future == null) {
				future = futureTask;
				futureTask.run();
			}
			return future;
		}
		return future;
	}
}

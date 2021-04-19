package ru.job4j.garbagecollection.ref;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 19.04.2021
 */

public class CacheDemo<K, V> {

    private final Cache<K, V> cache;
    private final DataStorage<K, V> dataStorage;

    public CacheDemo(Cache<K, V> cache, DataStorage<K, V> dataStorage) {
        this.cache = cache;
        this.dataStorage = dataStorage;
    }

    public V get(K key) {
        V value = cache.get(key);
        if (value == null) {
            value = dataStorage.getValue(key);
            cache.put(key, value);
        }
        return value;
    }
}

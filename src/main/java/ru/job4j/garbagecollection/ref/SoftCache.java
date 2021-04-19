package ru.job4j.garbagecollection.ref;

/**
 * @author Shegai Evgenii
 * @version 1.0
 * @since 19.04.2021
 */

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftCache<K, V> implements Cache<K, V> {

    private final Map<K, SoftReference<V>> map = new HashMap<>();

    @Override
    public V get(K key) {
        V rsl = null;
        SoftReference<V> softRef = map.get(key);
        if (softRef != null) {
            rsl = softRef.get();
        }
        return rsl;
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (value != null) {
            SoftReference<V> softRef = new SoftReference<>(value);
            map.put(key, softRef);
            rsl = true;
        }
        return rsl;
    }
}

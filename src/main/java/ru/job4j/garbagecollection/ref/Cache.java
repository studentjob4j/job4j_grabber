package ru.job4j.garbagecollection.ref;

public interface Cache<K, V> {

    V get(K key);

    boolean put(K key, V value);
}

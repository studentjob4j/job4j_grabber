package ru.job4j.garbagecollection.ref;

public interface DataStorage<K, V> {

    V getValue(K key);
}

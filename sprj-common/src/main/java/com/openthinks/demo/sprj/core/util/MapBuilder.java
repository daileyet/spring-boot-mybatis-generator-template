package com.openthinks.demo.sprj.core.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MapBuilder<K, V> {

    private final Map<K, V> map;

    /**
     * Create a HashMap builder
     */
    public MapBuilder() {
        map = new HashMap<>();
    }

    /**
     * Create a HashMap builder
     * @param initialCapacity
     */
    public MapBuilder(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    /**
     * Create a Map builder
     * @param mapFactory
     */
    public MapBuilder(Supplier<Map<K, V>> mapFactory) {
        map = mapFactory.get();
    }

    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }

    /**
     * Returns an unmodifiable Map. Strictly speaking, the Map is not immutable because any code with a reference to
     * the builder could mutate it.
     *
     * @return
     */
    public Map<K, V> buildUnmodifiable() {
        return Collections.unmodifiableMap(map);
    }
}
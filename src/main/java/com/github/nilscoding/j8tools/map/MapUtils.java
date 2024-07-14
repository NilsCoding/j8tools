package com.github.nilscoding.j8tools.map;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Map utils.
 * @author NilsCoding
 */
public final class MapUtils {

    private MapUtils() {
    }

    /**
     * Removes entries from a Map using a Predicate on the Map's keys.
     * @param <K>  type of key
     * @param <V>  type of value
     * @param map  map to remove entries from
     * @param pred key predicate
     * @return collection with removed keys, can be empty
     */
    public static <K, V> Collection<K> removeByKey(final Map<K, V> map, final Predicate<? super K> pred) {
        if ((map == null) || (pred == null)) {
            return new HashSet<>();
        }
        if (map.isEmpty() == true) {
            return new HashSet<>();
        }
        List<K> keysToRemove = map.keySet().stream().filter(pred).collect(Collectors.toCollection(LinkedList::new));
        if (keysToRemove.isEmpty() == false) {
            keysToRemove.forEach(map::remove);
        }
        return keysToRemove;
    }

    /**
     * Removes entries from a Map using a Predicate on the Map's entries.
     * @param <K>  type of key
     * @param <V>  type of value
     * @param map  map to remove entries from
     * @param pred entry predicate
     * @return number of removed entries
     */
    public static <K, V> int removeByEntry(final Map<K, V> map, final Predicate<Map.Entry<K, V>> pred) {
        if ((map == null) || (pred == null)) {
            return 0;
        }
        if (map.isEmpty() == true) {
            return 0;
        }
        // the predicate works on the entries, but the removal will be done via keys anyway
        List<K> keysToRemove = map.entrySet().stream()
                .filter(pred)
                .map(kvp -> kvp.getKey())
                .collect(Collectors.toCollection(LinkedList::new));
        int countRemoved = keysToRemove.size();
        if (keysToRemove.isEmpty() == false) {
            keysToRemove.forEach(map::remove);
        }
        return countRemoved;
    }

}

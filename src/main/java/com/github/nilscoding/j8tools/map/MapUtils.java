package com.github.nilscoding.j8tools.map;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Map utils
 * @author NilsCoding
 */
public class MapUtils {
    
    private MapUtils() { 
    }
    
    /**
     * Removes entries from a Map using a Predicate on the Map's keys
     * @param <K>   type of key
     * @param <V>   type of value
     * @param map   map to remove entries from
     * @param pred  key predicate
     * @return  collection with removed keys, can be empty
     */
    public static <K,V> Collection<K> removeByKey(Map<K,V> map, Predicate<? super K> pred) {
        if ((map == null) || (pred == null)) {
            return new HashSet<>();
        }
        if (map.isEmpty() == true) {
            return new HashSet<>();
        }
        List<K> keysToRemove = map.keySet().stream().filter(pred).collect(Collectors.toList());
        if (keysToRemove.isEmpty() == false) {
            keysToRemove.stream().forEach((oneKey) -> {
                map.remove(oneKey);
            });
        }
        return keysToRemove;
    }
    
    /**
     * Removes entries from a Map using a Predicate on the Map's entries
     * @param <K>   type of key
     * @param <V>   type of value
     * @param map   map to remove entries from
     * @param pred  entry predicate
     * @return  number of removed entries
     */
    public static <K,V> int removeByEntry(Map<K,V> map, Predicate<Map.Entry<K,V>> pred) {
        if ((map == null) || (pred == null)) {
            return 0;
        }
        if (map.isEmpty() == true) {
            return 0;
        }
        List<Map.Entry<K,V>> entriesToRemove = map.entrySet().stream().filter(pred).collect(Collectors.toList());
        int countRemoved = entriesToRemove.size();
        if (entriesToRemove.isEmpty() == false) {
            entriesToRemove.stream().forEach((oneEntry) -> {
                map.remove(oneEntry.getKey());
            });
        }
        return countRemoved;        
    }
    
}

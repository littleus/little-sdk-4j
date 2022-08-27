package org.littleus.sdk.cache.storage;

/**
 * @author hyzhangj
 */
public interface ICache<K, V> {
    /**
     * put cache
     *
     * @param key
     * @param value
     */
    void put(K key, V value);
}

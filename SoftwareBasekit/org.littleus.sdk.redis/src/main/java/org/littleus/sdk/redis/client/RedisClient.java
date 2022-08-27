package org.littleus.sdk.redis.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;

/**
 * @author hyzhangj
 */
public class RedisClient {
    public Jedis getJedis() {
        Jedis jedis = null;
        jedis.scan("", ScanParams);
        jedis.hscan()

        JedisPool jedisPool = null;

        return null;
    }
}

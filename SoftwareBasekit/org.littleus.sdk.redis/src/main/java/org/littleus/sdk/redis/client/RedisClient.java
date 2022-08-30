package org.littleus.sdk.redis.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

/**
 * @author hyzhangj
 */
public class RedisClient {
    public Jedis getJedis() {
        JedisPool jedisPool = null;

        try (Jedis jedis = jedisPool.getResource()){
            ScanParams scanParams = new ScanParams().match("*abc*").count(10000);
            ScanResult<String> scanResult = jedis.scan("0", scanParams);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

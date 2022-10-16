package org.littleus.sdk.redis.client;

import redis.clients.jedis.*;

/**
 * @author hyzhangj
 */
public class JedisClient {

    public JedisClient() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(100);

        JedisPool jedisPool = new JedisPool(config, "", 6379, 2000, "");
    }

    public Jedis getJedis() {
        // TODO
        return null;
    }
}

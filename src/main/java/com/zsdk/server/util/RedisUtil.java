package com.zsdk.server.util;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zhj on 18/1/24.
 */
@Repository
public class RedisUtil {

    //    redis连接池
    @Autowired
    private JedisPool jedisPool;


    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.set(key, value);
    }
}

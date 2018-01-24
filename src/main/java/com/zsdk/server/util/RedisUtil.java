package com.zsdk.server.util;

import com.zsdk.server.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

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

    public boolean setToken(String token, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        if (jedis.exists(token)){
            return false;
        }
        jedis.watch(token);
        Transaction transaction = jedis.multi();
        transaction.set(token, value);
        transaction.expire(token, expire);

        List<Object> list = transaction.exec();
        return list.size() > 0;
    }
}

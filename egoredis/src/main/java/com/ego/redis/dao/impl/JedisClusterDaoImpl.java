package com.ego.redis.dao.impl;

import com.ego.redis.dao.JedisClusterDao;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @Author: yk
 * @Date: 2019/6/9 9:26
 */
@Repository
public class JedisClusterDaoImpl implements JedisClusterDao {

    @Resource
    private JedisCluster jedisCluster;

    @Override
    public boolean exist(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String setExpire(String key, int seconds, String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    @Override
    public Long delete(String key) {
        return jedisCluster.del(key);
    }
}

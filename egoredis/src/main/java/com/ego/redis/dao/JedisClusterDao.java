package com.ego.redis.dao;

/**
 * @Author: yk
 * @Date: 2019/6/9 9:22
 */
public interface JedisClusterDao {

    boolean exist(String key);

    String get(String key);

    String set(String key, String value);

    String setExpire(String key, int seconds, String value);

    Long delete(String key);

}

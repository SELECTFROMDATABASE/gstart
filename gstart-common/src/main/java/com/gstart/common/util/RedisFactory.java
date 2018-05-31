package com.gstart.common.util;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisFactory {
    private static JedisPool pool;
    private static JedisPoolConfig je = null;
    private static PropertyUtil pro = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisFactory.class);

    static {

        pro = PropertyUtil.getInstance("redis");
        je = new JedisPoolConfig();
        je.setMaxIdle(Integer.valueOf(pro.get("master.redis.maxIdle")));
        je.setMaxWaitMillis(Long.valueOf(pro.get("master.redis.maxWait")));
        je.setTestOnBorrow(Boolean.valueOf(pro.get("master.redis.testOnBorrow")));
        je.setTestOnReturn(Boolean.valueOf(pro.get("master.redis.testOnReturn")));
        pool = new JedisPool(je, pro.get("master.redis.ip"));
    }

    public static Jedis getPool() {
        if (pool == null) {
            pool = new JedisPool(je, pro.get("master.redis.ip"));
        }
        return pool.getResource();
    }

    public static Function<Function<Jedis, Object>, Object> returnJedis = (s) -> {
        Jedis jedis = getPool();
        try {
            return s.apply(jedis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }

    };

    public static Consumer<Consumer<Jedis>> voidJedis = (s) -> {
        Jedis jedis = getPool();
        try {
            s.accept(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

    };

    /*** <p>Description: 返回资源 </p>
     * @param
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void set(String key, String value) {
        voidJedis.accept((o) -> o.set(key, value));
    }

    public static void set(String key, String value, int maxTime) {
        voidJedis.accept((o) -> {
            LOGGER.info(" Redis set '{}' , '{}' ", key, value);
            o.setex(key, maxTime, value);
        });
    }

    public static String get(String key) {
        LOGGER.info(" Redis get '{}' ", key);
        Object values = returnJedis.apply((s) -> s.get(key));
        return values == null? null : String.valueOf(values);
    }

    public static void del(String key) {
        voidJedis.accept((s) -> {
            LOGGER.info(" Redis del '{}' ", key);
            s.del(key);
        });
    }

    public static void srem(String key, String... member) {
        voidJedis.accept((s) -> {
            LOGGER.info(" Redis srem '{}' , '{}'", key, member);
            s.srem(key, member);
        });
    }

    public static Set<String> smembers(String s) {
        return (Set<String>) returnJedis.apply(jedis -> {
            LOGGER.info(" Redis smembers '{}' ", s);
            return jedis.smembers(s);
        });
    }

    public static Object scard(String s) {
        return returnJedis.apply(jedis -> {
            LOGGER.info(" Redis scard '{}' ", s);
            return jedis.scard(s);
        });
    }

    public static void lrem(String key, int count, String value) {
        voidJedis.accept(jedis -> {
            LOGGER.info(" Redis lrem '{}','{}','{}' ", key,count,value);
            jedis.lrem(key, count, value);
        });
    }

    public static void lpush(String key ,String ... objects){
        voidJedis.accept(jedis -> {
            LOGGER.info(" Redis lpush '{}','{}' ",key,objects);
            jedis.lpush(key,objects);
        });
    }

    public static void main(String[] args) {
        set("gstart-upms-shiro-session-id_3f40853b-3d83-425f-a1d4-cc626e2705af", "123");
        System.out.println(get("gstart-upms-shiro-session-id_3f40853b-3d83-425f-a1d4-cc626e2705af"));
    }
}

package cn.appservice.redis;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    private static String  hostRedis;
    private static String  auth;
    private static Integer port;
    private static Integer db;

    private static JedisPool pool;

    static {
        hostRedis = "101.200.41.116";
        port = 6379;
        auth = "cfyr1712";
        db = 10;
    }

    public static JedisPool instance() {
        if (null == hostRedis || hostRedis.equals("")) return null;
        if (pool != null) {
            return pool;
        }
        //创建JedisPool实例
        JedisPoolConfig config = new JedisPoolConfig();
       pool =  new JedisPool(config,hostRedis,port,3000, auth, db);
        return pool;
    }

    public static  JedisPool  instance(int dbIndex) {
        setDb(dbIndex);
        return pool;
    }

    public static String getHostRedis() {
        return hostRedis;
    }

    public static void setHostRedis(String hostRedis) {
        RedisUtils.hostRedis = hostRedis;
    }

    public static String getAuth() {
        return auth;
    }

    public static void setAuth(String auth) {
        RedisUtils.auth = auth;
    }


    public static Integer getPort() {
        return port;
    }

    public static void setPort(Integer port) {
        RedisUtils.port = port;
    }

    public static Integer getDb() {
        return db;
    }

    public static void setDb(Integer db) {
        RedisUtils.db = db;
    }


}
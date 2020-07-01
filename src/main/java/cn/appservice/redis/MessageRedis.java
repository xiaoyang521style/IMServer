package cn.appservice.redis;

import redis.clients.jedis.JedisPool;

import java.util.Map;

public class MessageRedis {

    /**
     * 消息保存
     * */
    public static void saveMessage(String key,String messageId,String message) throws Exception {
       JedisPool jedisPool = RedisUtils.instance();
       JedisUtil jedisUtil = new JedisUtil(jedisPool);
       JedisUtil.Hash hash = jedisUtil.new Hash();
       key = "im:message:"+ key;
       long res = hash.hset(key,messageId,message);
       if (res == 0) {
           System.out.println("消息存失败");
       } else {
           System.out.println("消息存成功");
       }
    }


    /**
     * 消息删除
     * */

    public static void delectMsg(String key,String messageId) throws  Exception {
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        key = "im:message:"+ key;
        long res = hash.hdel(key,messageId);
        if (res == 0) {
            System.out.println("删除消息失败");
        } else {
            System.out.println("删除消息成功");
        }
    }

    /**
     * 删除房间内的消息
     * */

    public static void delectRoomMsg(String key) throws Exception {
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        key = "im:message:"+ key;
        long res = hash.hdel(key);
        if (res == 0) {
            System.out.println("删除房间内消息失败");
        } else {
            System.out.println("删除房间内消息成功");
        }
    }

    /**
     * 取出房间内的消息
     * */

    public static Map selMsg(String roomid) throws  Exception {
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        String key = "im:message:"+roomid;
        Map map = hash.hgetAll(key);
        return map;
    }

    /**
     *
     * 删除房间内单独消息
     * */

    public static void delectMsgInRoom(String key, String messageId) {
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        key = "im:message:"+ key;

        hash.hdel(key,messageId);
    }



    /**
     * 将群信息变更保存
     * key 为 userId
     * id 为 roomid+time
     * */
    public static void saveChangegroupRoomInfo(String key, String id, String content) {
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        key = "im:groupRoomInfo:"+ key;
        long res = hash.hset(key,id,content);

    }



    /**
     * 取出房间变更信息
     * */

    public static Map selChangegroupRoomInfo(String roomid) throws  Exception {
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        String key = "im:groupRoomInfo"+roomid;
        Map map = hash.hgetAll(key);
        return map;
    }

    /**
     * 删除房间变更信息
     * */
    public static void delectChangegroupRoomInfo(String key) {
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        key = "im:groupRoomInfo"+ key;
        long res = hash.hdel(key);
        if (res == 0) {
            System.out.println("删除房间内消息失败");
        } else {
            System.out.println("删除房间内消息成功");
        }

    }

}

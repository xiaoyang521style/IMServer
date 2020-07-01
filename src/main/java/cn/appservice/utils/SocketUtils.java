package cn.appservice.utils;

import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.entities.UserSocket;
import cn.appservice.handler.SendMessageOffLine;
import cn.appservice.po.UserSetting;
import cn.appservice.redis.JedisUtil;
import cn.appservice.redis.RedisUtils;
import cn.appservice.serverimpl.UserServerImpl;
import cn.appservice.servers.UserServer;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import redis.clients.jedis.JedisPool;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class SocketUtils {

    private static Map<Integer, ChannelHandlerContext> contexts = new HashMap();

    private static volatile SocketUtils instance;

    private static String userIPTable = "user:t_userIP";

    private String ip = "20001";

    private  String port = "101.200.41.116";

    private static UserServer us = new UserServerImpl();


    public Gson gson = new Gson();
    private SocketUtils() {}
    public static SocketUtils getInstance() {
        if (instance == null) {
            synchronized (SocketUtils.class) {
                if (instance == null) {
                    instance = new SocketUtils();
                }
            }
        }
        return instance;
    }


    public Map<Integer, ChannelHandlerContext> getContexts() {
        return contexts;
    }

    public void setContexts(Map<Integer, ChannelHandlerContext> contexts) {
        this.contexts = contexts;
    }

    public void removeAllUserSocket(){
        Iterator<Map.Entry<Integer, ChannelHandlerContext>> it = contexts.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, ChannelHandlerContext> entry = it.next();
            Integer userId = entry.getKey();
            ChannelHandlerContext ctx = entry.getValue();
            delectUserIP(String.valueOf(userId));
            ctx.close();
            ctx.channel().close();
            it.remove();//使用迭代器的remove()方法删除元素
        }
    }
    public void removeCtx(Integer key) {

        ChannelHandlerContext ctx = contexts.get(key);
        if (ctx!=null) {
           ctx.close();
            ctx.channel().close();
        }

        contexts.remove(key);
    }

    public void removeCtx(ChannelHandlerContext value) {
        Iterator<Map.Entry<Integer, ChannelHandlerContext>> it = contexts.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, ChannelHandlerContext> entry = it.next();
            if (value == entry.getValue()) {
                Integer userId = entry.getKey();
                ChannelHandlerContext ctx = entry.getValue();
                delectUserIP(String.valueOf(userId));
                ctx.close();
                ctx.channel().close();
                it.remove();//使用迭代器的remove()方法删除元素
            }

        }
    }

    public void addCtx(ChannelHandlerContext context, Integer key) {
        contexts.put(key, context);
        UserSocket userSocket = new UserSocket(key,ip,port);
        bandUserIP(userSocket);
    }

    public ChannelHandlerContext getCtx(Integer key) {
        return contexts.get(key);
    }

    public void sendToContext(ChannelHandlerContext context, Object response){
            //在线推送
            Msg msg = new Msg();
            msg.writeInt(0);//dataType
            msg.writeString("5b0580f9e4b03bdaf9a28966");//jobId
            msg.writeString("msgTag");//jobTag
            msg.writeInt(6);
            String content =gson.toJson(response);
            System.out.println("返回参数："+content);
            try {
                String privatekey = "chat@cfyr.com.cn";
                content = SecurityUtil.encrypt(privatekey,content);
                Map map = new HashMap();
                map.put("data",content);
                ObjectMapper objectMapper = new ObjectMapper();
                content = objectMapper.writeValueAsString(map);
                msg.writeContent(content);
                ByteBuf resp = Unpooled.copiedBuffer(msg.getBuffer());
                boolean contains = contexts.containsValue(context);
                if (contains) {

                }
                context.channel().writeAndFlush(resp);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void sendToContext(Object object, int userId) throws Exception{
        ChannelHandlerContext context = contexts.get(userId);
        if (context != null){
            sendToContext(context,object);
        }else {

            /**
             * 1.获取用户设置
             * */
            UserSetting userSetting= us.getUserSetting(userId);
            if (userSetting.getMessage_notif() == 1) {
                Response response = (Response) object;
                if (response.getResult().getClass() == IMMessage.class){
                    //离线推送
                    SendMessageOffLine sendMessageOffLine =  new SendMessageOffLine();
                    IMMessage messageBean = (IMMessage) response.getResult();
                    String content = null;
                    if(messageBean.getAction().equals("chat")) {
                        if (userSetting.getShow_msgDetail() == 1) {
                            if(messageBean.getChatType() == 1) {
                                content = messageBean.getContent();
                            }
                            if (messageBean.getChatType() == 2) {
                                content = "[图片]";
                            }
                            if (messageBean.getChatType() == 3) {
                                content = "[语音]";
                            }
                            if (messageBean.getChatType() == 4) {
                                content = "[位置]";
                            }
                            if (messageBean.getChatType() == 5) {
                                content = "[视频]";
                            }
                            if (messageBean.getChatType() == 6) {
                                /**
                                 * 表情gif
                                 * */
                                content = messageBean.getContent();
                                Map<String, Object> res = null;
                                Gson gson = new Gson();
                                res = gson.fromJson(content, new TypeToken<Map<String, Object>>() {
                                }.getType());
                                content = (String) res.get("text");
                            }
                            sendMessageOffLine.sendMessageOffLine(content,userId,messageBean.getUserId());
                        }else if (messageBean.getAction().equals("call")) {
                            if (messageBean.isVideo()) {
                                sendMessageOffLine.sendMessageOffLine("发起视频通话请求",userId,messageBean.getCallUserID());
                            }else {
                                sendMessageOffLine.sendMessageOffLine("发起语音通话请求",userId,messageBean.getCallUserID());
                            }
                        } else {
                            content = "您有一条新消息";
                            sendMessageOffLine.sendMessageOffLine(content,userId,messageBean.getUserId());
                        }
                    }
                }
            }
        }
    }



    public  void bandUserIP(UserSocket userSocket){
        String userId = String.valueOf(userSocket.getUserId());
        Map<String,String> userIPMap = new HashMap<String, String>();
        userIPMap.put(userId, JSON.toJSONString(userSocket));


        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        hash.hmset(userIPTable,userIPMap);
    }

    public void delectUserIP(String userId){
        JedisPool jedisPool = RedisUtils.instance();
        JedisUtil jedisUtil = new JedisUtil(jedisPool);
        JedisUtil.Hash hash = jedisUtil.new Hash();
        hash.hdel(userIPTable,userId);
    }


}

package cn.appservice.serverimpl;

import cn.appservice.entities.IMMessage;
import cn.appservice.entities.RedisMessage;
import cn.appservice.servers.ChannelServer;
import cn.appservice.utils.CommonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * [com.appservice.serverimpl desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/7/2
 */
public class ChannelServerImpl implements ChannelServer {
    private static Logger                    logger = Logger.getLogger(ChannelServer.class);
    private static Map<String, ChannelGroup> ucMap  = new ConcurrentHashMap<>();

    @Override
    public void roomDelete(RedisMessage m) { }

    /**
     * [描述： add  channel relation with user to channelGroup when login]
     *
     * @param ctx
     * @param m
     * @author yangkun[Email:vectormail@163.com] 2018/7/2
     */
    @Override
    public void userJoin(ChannelHandlerContext ctx, IMMessage m) {


        logger.info("ChannelServer userJoin current Channel Group is =>" + ucMap);
    }

    /**
     * [描述： remove  channel relation with user to channelGroup when login]
     *
     * @param ctx
     * @param m
     * @author yangkun[Email:vectormail@163.com] 2018/7/2
     */
    @Override
    public void userLogout(ChannelHandlerContext ctx, IMMessage m) {

        logger.info("ChannelServer userLogout current Channel Group is =>" + ucMap);

    }


    /**
     * [描述： desc]
     *
     * @param uid
     * @param role
     * @param from
     * @return
     * @author yangkun[Email:vectormail@163.com] 2018/7/2
     */
    @Override
    public ChannelGroup getChannelGroup(int uid, String role, boolean from) {
        ChannelGroup ch;
        ch = ucMap.get(CommonUtils.getKey(uid, role, from));
        if(null != ch)
            ch.forEach(c -> {if (!c.isActive()) ch.remove(c);});
        logger.info("ChannelServer getchannelGroup=>" + ch);
        return ch;
    }
}

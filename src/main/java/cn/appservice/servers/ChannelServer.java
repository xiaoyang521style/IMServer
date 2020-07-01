package cn.appservice.servers;

import cn.appservice.entities.IMMessage;
import cn.appservice.entities.RedisMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

/**
 * [com.appservice.servers desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/5/29
 */
public interface ChannelServer {


    /**
     * [描述： add  channel relation with user to channelGroup when login]
     *
     * @param ctx
     * @param m
     * @author yangkun[Email:vectormail@163.com] 2018/7/2
     */
    void userJoin(ChannelHandlerContext ctx, IMMessage m);


    void roomDelete(RedisMessage m);



    /**
     * [描述： remove  channel relation with user to channelGroup when login]
     *
     * @param ctx
     * @param m
     * @author yangkun[Email:vectormail@163.com] 2018/7/2
     */
    void userLogout(ChannelHandlerContext ctx, IMMessage m);


    /**
     * [描述： desc]
     *
     * @param uid int
     * @param from boolean
     * @return
     * @author yangkun[Email:vectormail@163.com] 2018/7/2
     */
    ChannelGroup getChannelGroup(int uid, String role, boolean from);


}

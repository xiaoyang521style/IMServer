package cn.appservice.servers;

import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import io.netty.channel.ChannelHandlerContext;

public interface ContactsServer {
    Response getContacts(ChannelHandlerContext ctx, IMMessage m) throws Exception;
}

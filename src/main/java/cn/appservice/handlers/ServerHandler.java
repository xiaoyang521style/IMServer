package cn.appservice.handlers;


import cn.appservice.utils.MessageUtils;
import cn.appservice.utils.SocketUtils;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;

import io.netty.channel.ChannelHandlerContext;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;

@Component
@Qualifier("serverHandler")
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    /** 空闲次数 */
    private int idle_count =1;
    private static final int MAX_CONN = 10000;//指定最大连接数
    private static AtomicInteger connectNum = new AtomicInteger();//当前连接数
    private byte[] req = new byte[0];
    //获取当前时间
    private String getTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return new String(year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端Handler创建。。。");
        super.handlerAdded(ctx);
    }

    /*
     * 重写channelActive()方法
     * 更新当前连接数
     * 控制连接客户端的个数，超过则关闭该channel
     * 更新contexts数组
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        // TODO Auto-generated method stub
        //
        //控制客户端连接数量，超过则关闭
        connectNum.incrementAndGet();
        if (connectNum.get() > MAX_CONN) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("达到人数上限".getBytes()));
            ctx.channel().close();
            //当前连接数的更新放在channelInactive()里
        }
        //更新contexts

        //控制台输出相关信息
        InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println(socket.getAddress().getHostAddress() + ":" + socket.getPort() + "已连接");
        System.out.println("当前连接数：" + connectNum.get());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }


    /*
     * 重写channelInactive()方法
     * 更新当前连接数
     * 更新contexts数组
     * 控制台输出相关信息
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
        //更新当前连接数
        connectNum.decrementAndGet();
       // 更新contexts数组
        super.channelInactive(ctx);

        SocketUtils.getInstance().removeCtx(ctx);
        //控制台输出相关信息
//        InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
//        System.out.println(getTime() + ' ' + socket.getAddress().getHostAddress() + ":" + socket.getPort() + "已退出");
        System.out.println("当前连接数：" + connectNum.get());
        ctx.close();
        ctx.channel().close();

    }
    /*
     * 重写channelRead()函数
     * 读取数据
     * 转发消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO Auto-generated method stub
        MessageUtils.msgHandler(ctx, (String) msg);
        idle_count = 0;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        if (!ctx.channel().isActive()) {
            System.out.println(ctx.channel().remoteAddress() + "客户端异常");
        }
        cause.printStackTrace();
        SocketUtils.getInstance().removeCtx(ctx);
        ctx.close();
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (IdleState.READER_IDLE.equals(event.state())) {  //如果读通道处于空闲状态，说明没有接收到心跳命令
                System.out.println("已经30秒没有接收到客户端的信息了");
                if (idle_count > 2) {
                    System.out.println("关闭这个不活跃的channel");
                    ctx.channel().close();
                }
                idle_count++;
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }
}

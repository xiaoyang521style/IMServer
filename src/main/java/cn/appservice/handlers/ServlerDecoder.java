package cn.appservice.handlers;

import cn.appservice.common.SocketResponse;
import cn.appservice.utils.ByteUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ServlerDecoder extends ByteToMessageDecoder {


    boolean ishalf;

    private byte[] req = new byte[0];

    int msgLen;
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //取到tag与报文长度后再处理，各2字节
        if(byteBuf.readableBytes()<4){
            return;
        }

        //要读整个报文的长度
        int readableBytesLen = byteBuf.readableBytes();

        if (ishalf) {
            byte[] bytes = new byte[readableBytesLen];
            byteBuf.readBytes(bytes);
            req = ByteUtil.addBytes(req,bytes);
            if (msgLen+4 == req.length ) {
                ishalf = false;
                String content = handleMsg(req);
                req = new byte[0];
                list.add(content);
            }
        }else {
            byte[] bytes = new byte[readableBytesLen];
            byteBuf.readBytes(bytes);
            byte[] contentByte = new byte[4];
            System.arraycopy(bytes, 0, contentByte, 0, 4);
            msgLen  = ByteUtil.byteArrayToInt(contentByte);
            System.out.println("content =========" + msgLen);
            System.out.println("byteBuf.readableBytes =========" + readableBytesLen);
            //收到的报文长度不足一个完整的报文，继续接收
            if(readableBytesLen-4<msgLen){
                ishalf = true;
                req = ByteUtil.addBytes(req,bytes);
                System.out.println("======bytes====" +bytes.toString());
                return;
            }
            //提出完整报文(readBytes读到msg中)，放到list给下一个Handler处理
            if(msgLen>0){
                String content = handleMsg(bytes);
                list.add(content);
                ishalf = false;
            }
        }

    }


    private String handleMsg(byte[] bytes) {
        SocketResponse socketResponse = new SocketResponse(bytes,20);
        String localName = socketResponse.readString();
        System.out.print(" localName : " + localName);

        String userId = socketResponse.readString();
        System.out.print(" userId: " + userId);

        String mobile = socketResponse.readString();
        System.out.print(" mobile: " + mobile);

        socketResponse.setPosition(socketResponse.getPosition() + 20);
        String msgId = socketResponse.readString();
        System.out.print(" MsgId: " + msgId);

        String msgTag = socketResponse.readString();
        System.out.print(" MsgTag: " + msgTag);

        String msgTo = socketResponse.readString();
        System.out.print(" Msgto: " + msgTo);

        socketResponse.setPosition(socketResponse.getPosition() + 8);

        String content = socketResponse.readContent();
        System.out.print(" content: " + content);

        return content;
    }
}

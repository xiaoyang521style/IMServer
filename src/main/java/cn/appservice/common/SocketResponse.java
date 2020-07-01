package cn.appservice.common;

import cn.appservice.utils.ByteUtil;
import cn.appservice.utils.SecurityUtil;
import org.xerial.snappy.Snappy;

import java.io.IOException;

public class SocketResponse {
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private byte[] bytes;
    private String content;
    private int position;

    public SocketResponse(byte[] bytes, int position) {
        super();
        this.bytes = bytes;
        this.position = position;
    }

    public int readInt() {
        byte[] contentByte = new byte[4];
        System.arraycopy(this.bytes, position, contentByte, 0, 4);
        int content = ByteUtil.byteArrayToInt(contentByte);
        position += 4;

       return content;
    }

    public long readLong() {
        byte[] LenByte = new byte[8];
        System.arraycopy(this.bytes, position, LenByte, 0, 4);
        int Len = ByteUtil.byteArrayToInt(LenByte);

        byte[] contentByte = new byte[Len];
        System.arraycopy(this.bytes, position, contentByte, 0, Len);
        long content = ByteUtil.byteArrayToLong(contentByte);
        position += 8;
        return content;
    }

    public String readString() {
        byte[] LenByte = new byte[4];
        System.arraycopy(this.bytes, position, LenByte, 0, 4);
        int Len = ByteUtil.byteArrayToInt(LenByte);
        position += 4;

        byte[] contentByte = new byte[Len];
        System.arraycopy(this.bytes, position, contentByte, 0, Len);
        String content = new String(contentByte);
        position += Len;
        return content;
    }

    public String readContent() {
        int needDecompression = readInt();

        byte[] contentLenByte = new byte[4];
        System.arraycopy(bytes, position, contentLenByte, 0, 4);
        int contentLen = ByteUtil.byteArrayToInt(contentLenByte);

        position += 4;
        byte[] contentByte = new byte[contentLen];
        System.arraycopy(bytes, position, contentByte, 0, contentLen);

        String content = null;
        if (needDecompression == 0) {
            System.out.print("不需要解压字符串");
            content = new String(contentByte);
        } else if (needDecompression == 1) {
            System.out.print("需要解压字符串");
            try {
                content = new String(Snappy.uncompress(contentByte));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            String privatekey = "team.@bkbedu.com";
            content = SecurityUtil.decryptString(privatekey, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;

    }


}

package cn.appservice.utils;

import org.xerial.snappy.Snappy;

import java.io.IOException;

public class ByteUtil {

    //将低字节在前转为int，高字节在后的byte数组(与IntToByteArray1想对应)
    public static int byteArrayToInt(byte[] b){
        byte[] a = new byte[4];
        int i = a.length - 1,j = b.length - 1;
        for (; i >= 0 ; i--,j--) {//从b的尾部(即int值的低位)开始copy数据
            if(j >= 0)
                a[i] = b[j];
            else
                a[i] = 0;//如果b.length不足4,则将高位补0
        }
        int v0 = (a[0] & 0xff) << 24;//&0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        int v1 = (a[1] & 0xff) << 16;
        int v2 = (a[2] & 0xff) << 8;
        int v3 = (a[3] & 0xff) ;
        return v0 + v1 + v2 + v3;
    }

    public static long byteArrayToLong(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    public static byte[] integerToBytes(int res) {
        byte[] targets = new byte[4];

        targets[3] = (byte) (res & 0xff);// 最低位
        targets[2] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[1] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[0] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     *
     * @param data1
     * @param data2
     * @return data1 与 data2拼接的结果
     */
    public static byte[] addBytes(byte[] data1, byte[] data2) {
        if (data1 == null) {
            return  data2;
        } else {
            byte[] data3 = new byte[data1.length + data2.length];
            System.arraycopy(data1, 0, data3, 0, data1.length);
            System.arraycopy(data2, 0, data3, data1.length, data2.length);
            return data3;
        }
    }

    /**
     * 压缩字符
     * @param
     * @param
     *
     * */

    public static  byte[] compressHtml(String value){
        try {
            return Snappy.compress(value.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解压字符
     * @param
     * @param
     *
     * */

    public static  String decompressString(byte[] bytes){
        try {
            return new String(Snappy.uncompress(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package cn.appservice.utils;

public class Msg {

      byte[] buffer;

      public void writeInt(int value) {
          byte[] result = new byte[4];
          result[0] = (byte)((value >>> 24) & 0xff );
          result[1] = (byte)((value >>> 16)& 0xff );
          result[2] = (byte)((value >>> 8) & 0xff );
          result[3] = (byte)((value >>> 0) & 0xff );
          buffer = ByteUtil.addBytes(buffer,result);
      }

      public void writeLong(long value) {
          byte[] result = new byte[8];
          for (int i = 0; i < 8; i++) {
              int offset = 64 - (i + 1) * 8;
              buffer[i] = (byte) ((value >> offset) & 0xff);
          }
          buffer = ByteUtil.addBytes(buffer,result);
      }

      public void writeString(String value) {
          byte[] result =value.getBytes();
          writeInt(result.length);
          buffer = ByteUtil.addBytes(buffer,result);
      }

      public void writeContent(String value) {
          byte[] result =value.getBytes();
          if(result.length >= 1024) {
              //压缩
              writeInt(1);
              byte[] compress = ByteUtil.compressHtml(value);
              writeInt(compress.length);
              buffer = ByteUtil.addBytes(buffer,compress);
          } else  {
              writeInt(0);
              //不压缩
              writeInt(result.length);
              buffer = ByteUtil.addBytes(buffer,result);
          }

      }

      public  byte[] getBuffer() {
          int len = buffer.length;
          byte[] result = new byte[4];
          result[0] = (byte)((len >>> 24) & 0xff );
          result[1] = (byte)((len >>> 16)& 0xff );
          result[2] = (byte)((len >>> 8) & 0xff );
          result[3] = (byte)((len >>> 0) & 0xff );
          buffer = ByteUtil.addBytes(result,buffer);
          return buffer;
      }

    }
package cn.appservice.utils;


import java.util.Base64;


public class SecurityUtil {
    //AES加密
    public  static String encrypt (String password ,String content) throws Exception {
        if (AES.Encrypt(content,password) == null) {
            content = Base64.getEncoder().encodeToString(content.getBytes("utf-8"));
        }else {
            content = AES.Encrypt(content,password);
        }
        return  content;
    }

    //AES解密
    public  static String decryptString (String password ,String content) throws Exception {
        byte[] dataBytes = Base64.getEncoder().encode(content.getBytes());
        dataBytes = Base64.getDecoder().decode(dataBytes);
        return  AES.Decrypt(new String(dataBytes, "utf-8"),password);
    }
}

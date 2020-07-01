package cn.appservice.utils;

import cn.appservice.httpclient.JPushHttpClient;
import cn.appservice.serverimpl.MessageServerImpl;
import cn.appservice.servers.MessageServer;

public class ApnsSend {

    private static MessageServer ms     = new MessageServerImpl();

   public void pushOneMessage (String message, String deviceToken, int deviceModel,int userId) throws  Exception {
        switch (deviceModel){
            case 1 : {
            	
            	//查询消息未读个数

                int badge = ms.unreadCount(userId);
                
                //苹果推送
                JPushHttpClient jPushHttpClient = new JPushHttpClient(true);
                jPushHttpClient.sendToIOSMessage(message,"",deviceToken,badge);

            }
            break;
            case 2:{
                JPushHttpClient jPushHttpClient = new JPushHttpClient(true);
                jPushHttpClient.sendToAndroidMessage(message,"",deviceToken);
            }
            break;
            default:
                break;
        }
   }

}

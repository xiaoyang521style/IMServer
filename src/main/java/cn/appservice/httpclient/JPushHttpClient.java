package cn.appservice.httpclient;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPushHttpClient {

    protected static final String APP_KEY = "59817a525243d461ba54042f";
    protected static final String MASTER_SECRET = "0711e1da3ad74651eff05749";
    protected static  JPushClient jpushClient;

    boolean production;

    /**
     * @param  production 是否为生成环境，影响iOS开发
     *
     * 初始化对象
     * */
    protected static final Logger LOG =  LoggerFactory.getLogger(PushResult.class);
    public JPushHttpClient(boolean production){
        ClientConfig clientConfig =  ClientConfig.getInstance();
        this.production = production;
        jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
    }

    /**
     *
     * @param title 标题
     * @param message 信息
     * @param registrationId 设备标识
     * @param badge 圆点个数
     * 给具体某个iOS设备发送通知
     */
    public  void  sendToIOSMessage(String title, String message , String registrationId, int badge) {
        PushPayload payload =  PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(title)
                                .setBadge(badge)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(message))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(this.production)
                        .build())
                .build();
        sendToMessage(payload);
    }
/**
 * @param title 标题
 * @param message 信息
 * 给全部设备发送通知
 * */
    public  void sendToObject_ios_audienceMore_messageWithExtras(String title, String message, int badge) {
        PushPayload payload = PushPayload.newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(Audience.newBuilder()
                            .build())
                    .setMessage(Message.newBuilder()
                            .setMsgContent(message)
                            .addExtra("from", "JPush")
                            .build())
                    .setOptions(Options.newBuilder()
                        .setApnsProduction(this.production)
                        .build())
                    .build();

        sendToMessage(payload);
    }
    /**
     * @param title 标题
     * @param message 信息
     * @param registrationId 设备标识
     * 给具体某个安卓设备发送通知
     */
    public void sendToAndroidMessage (String title, String message, String registrationId) {
        PushPayload payload =  PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.android(title, message, null))
                .build();
        sendToMessage(payload);
    }

    public void sendToMessage(PushPayload payload) {

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error RedisMessage: " + e.getErrorMessage());
        }

    }

}

package cn.appservice.servers;

import cn.appservice.base.po.Message;
import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;

/**
 * [com.appservice.servers desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/5/29
 */
public interface MessageServer {

     Response readHistroyMessage(IMMessage m) throws Exception;

     void saveMessage(IMMessage m) throws Exception;

     int unreadCount(int userId) throws  Exception;

     Response readMessage(IMMessage m) throws Exception;


     Object callBackMsg(IMMessage m) throws Exception;
}

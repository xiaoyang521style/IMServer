package cn.appservice.servers;

import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;

public interface JPushServer {
   Response deviceTokenAndUserHandel(IMMessage m)throws Exception;

}

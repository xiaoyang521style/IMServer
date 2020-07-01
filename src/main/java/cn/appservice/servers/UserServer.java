package cn.appservice.servers;

import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.po.AddMeSetting;
import cn.appservice.po.UserSetting;
import io.netty.channel.ChannelHandlerContext;

/**
 * [com.appservice.servers desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/5/29
 */
public interface UserServer {
     Response userLogin(ChannelHandlerContext ctx, IMMessage m) throws Exception;

     //获取用户设置
     public UserSetting getUserSetting(int userId)throws Exception;

     public AddMeSetting getAddMeSetting(int userId)throws  Exception;
}

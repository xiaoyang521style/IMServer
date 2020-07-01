package cn.appservice.serverimpl;

import cn.appservice.base.mapper.UserMapper;
import cn.appservice.base.po.User;
import cn.appservice.base.po.UserExample;
import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.handler.HandleMessage;
import cn.appservice.mapper.AddMeSettingMapper;
import cn.appservice.mapper.UserSettingMapper;
import cn.appservice.po.AddMeSetting;
import cn.appservice.po.UserSetting;
import cn.appservice.servers.UserServer;
import cn.appservice.utils.MyBatisUtil;
import io.netty.channel.ChannelHandlerContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

/**
 * [com.appservice.serverimpl desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/7/2
 */
public class UserServerImpl extends HandleMessage implements UserServer {
    private static Logger logger = Logger.getLogger(UserServer.class);




    /**
     * [描述： desc] 登录
     * @param ctx ChannelHandlerContext
     * @param m IMMessage
     * */
    @Override
    public Response userLogin(ChannelHandlerContext ctx, IMMessage m) throws Exception{
        //获取session

        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andTokenEqualTo(m.getToken());
        List<User> list= userMapper.selectByExample(example);
        session.close();
        Response response = new Response();
        if(list.size() == 1){
            User user = list.get(0);
            int userId = user.getId();
            HashMap hashmap = new HashMap(userId);
            hashmap.put("userId",userId);
            response.setMessage("soket用户登录成功");
            response.setXeach(true);
            response.setResult(hashmap);
        }else {
            response.setMessage("soket用户登录失败");
            response.setXeach(false);
        }
        response.setAction("login");

        return response;
    }

    @Override
    public UserSetting getUserSetting(int userId)throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserSettingMapper userSettingMapper = session.getMapper(UserSettingMapper.class);
        List list = userSettingMapper.selectUserSetting(userId);
        UserSetting userSetting =null;
        if (list.size() == 0) {
            userSetting = new UserSetting();
            userSetting.setMessage_notif(1);
            userSetting.setShow_msgDetail(1);
            userSetting.setUserId(userId);
            userSettingMapper.insertUserSetting(userSetting);
            session.commit();
        }else {
            userSetting = (UserSetting) list.get(0);

        }
        session.close();
        return userSetting;
    }

    @Override
    public AddMeSetting getAddMeSetting(int userId)throws  Exception {
        SqlSession session = MyBatisUtil.getSession();
        AddMeSettingMapper addMeSettingMapper = session.getMapper(AddMeSettingMapper.class);
        List list = addMeSettingMapper.selectAddMeSetting(userId);
        AddMeSetting addMeSetting = null;
        if (list.size() == 0) {
            addMeSetting = new AddMeSetting();
            addMeSetting.setOpen_code(1);
            addMeSetting.setOpen_phone(1);
            addMeSetting.setUserId(userId);
            addMeSettingMapper.insertAddMeSetting(addMeSetting);
            session.commit();
        } else {
            addMeSetting = (AddMeSetting) list.get(0);
        }
        session.close();
        return addMeSetting;
    }
}

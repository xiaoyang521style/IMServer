package cn.appservice.handler;


import cn.appservice.base.mapper.UserMapper;
import cn.appservice.base.po.User;
import cn.appservice.base.po.UserExample;
import cn.appservice.common.CheckOutToken;
import cn.appservice.common.Response;
import cn.appservice.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class HandleMessage {

    //创建工厂

    public static Response userTokenCheckout(String token) throws Exception {
        Response response = new Response();
        if (token == null){
            response.setXeach(false);
            response.setMessage("token为null");
            return response;
        }
        CheckOutToken state = userCheckout(token);
        switch (state){
            case NOTOKEN:{
                response.setXeach(false);
                response.setMessage("登录失败,没有对应的用户");
            }
            break;
            case ONETOKEN:{
                response.setXeach(true);
                response.setMessage("登录成功");
            }
            break;
            case MORETOKEN:{
                response.setXeach(false);
                response.setMessage("登录失败,有多个相同的token的用户");
            }
            break;
            default:
                break;
        }
        return response;
    }

    public static CheckOutToken userCheckout(String token) throws Exception{

        //获取session
        SqlSession session =  MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andTokenEqualTo(token);
        List<User> list= userMapper.selectByExample(example);
        CheckOutToken state = CheckOutToken.NOTOKEN;
        switch (list.size()){
            case 0:
                state = CheckOutToken.NOTOKEN;
                break;
            case 1:
                state = CheckOutToken.ONETOKEN;
                break;
            default:
                state = CheckOutToken.MORETOKEN;
                break;
        }
        session.close();
        return  state;
    }

    public static User selectUserById(int userId) throws  Exception {
        SqlSession session =  MyBatisUtil.getSession();
       UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(userId);
        session.close();
        return user;
    }

}

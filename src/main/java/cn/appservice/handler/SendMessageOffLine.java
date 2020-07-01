package cn.appservice.handler;

import cn.appservice.mapper.DeviceTokenMapper;
import cn.appservice.po.DeviceTokenQuery;
import cn.appservice.base.po.User;
import cn.appservice.utils.ApnsSend;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class SendMessageOffLine {


    public void sendMessageOffLine(String message, Integer toUserId ,int fromUserId) throws Exception {
        SqlSessionFactory sqlSessionFactory;

        //配置文件（SqlMapConfig.xml）
        String resource = "mybatis/SqlMapConfig-Chat.xml";

        //加载配置文件到输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //创建会话工厂
        sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        DeviceTokenMapper deviceTokenMapper = session.getMapper(DeviceTokenMapper.class);
        List<DeviceTokenQuery> list = deviceTokenMapper.selectDeviceInfoByUserId(toUserId);
        session.close();

        User fromUser = HandleMessage.selectUserById(fromUserId);



        if(list.size()>0) {
            DeviceTokenQuery query = list.get(0);

            ApnsSend apnsSend = new ApnsSend();
            apnsSend.pushOneMessage(fromUser.getUsername() + "：" + message,query.getDeviceToken(), query.getDeviceModel(),toUserId);
        }
    }
}

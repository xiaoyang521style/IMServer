package cn.appservice.serverimpl;

import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.handler.HandleMessage;
import cn.appservice.mapper.FriendCoustomMapper;
import cn.appservice.po.FriendCoustom;
import cn.appservice.servers.ContactsServer;
import cn.appservice.utils.MyBatisUtil;
import io.netty.channel.ChannelHandlerContext;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ContactsServerImpl extends HandleMessage implements ContactsServer {

    public static FriendCoustomMapper friendCoustomMapper;

    @Override
    public Response getContacts(ChannelHandlerContext ctx, IMMessage m) throws Exception {
        String token = m.getToken();
        Response tokenResponse;
        tokenResponse = userTokenCheckout(token);

        if (!tokenResponse.isXeach()) {
            return  tokenResponse;
        }

        int userId = m.getUserId();
        List<FriendCoustom> list =  selectFriendByUserId(userId);
        Response response = new Response();
        response.setXeach(true);
        response.setAction("getContactList");
        response.setMessage("获取联系人列表成功");
        response.setResult(list);
        return  response;
    }


    public static List<FriendCoustom> selectFriendByUserId(int hostId) throws Exception {
        //获取session
        SqlSession session = MyBatisUtil.getSession();
        friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        List<FriendCoustom> friendCoustoms = friendCoustomMapper.selectUserFriendsByUserId(hostId,2);

        session.close();
        return friendCoustoms;
    }
}

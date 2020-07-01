package cn.appservice.serverimpl;

import cn.appservice.base.mapper.FriendMapper;
import cn.appservice.base.mapper.UserMapper;
import cn.appservice.base.po.Friend;
import cn.appservice.base.po.FriendExample;
import cn.appservice.base.po.User;
import cn.appservice.base.po.UserExample;
import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.handler.HandleMessage;
import cn.appservice.mapper.FriendCoustomMapper;
import cn.appservice.mapper.MessageCoustomMapper;
import cn.appservice.po.ContactsQueryVo;
import cn.appservice.po.FriendCoustom;
import cn.appservice.servers.FriendServer;
import cn.appservice.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendServerImpl extends HandleMessage implements FriendServer {
//    public static FriendMapper friendMapper;
//    public static UserMapper userMapper;
//    public static MessageCoustomMapper messageCoustomMapper;
//
//    public static FriendCoustomMapper friendCoustomMapper;

    @Override
    public boolean checkFriend(IMMessage m) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        FriendCoustomMapper friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        List<Integer> list = friendCoustomMapper.selectFriendsByUserIdAndFriendsId(m.getUserId(),m.getFriendId(),2);
        session.close();
        for (int i = 0 ;i<list.size(); i++) {
            Integer friendId = list.get(i);
            if (friendId == m.getFriendId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Response matchPhoneNumber(IMMessage m) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
       UserMapper userMapper = session.getMapper(UserMapper.class);

       FriendCoustomMapper friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        List list = friendCoustomMapper.selectUserFriendsByHostId(m.getUserId(),2);
        List list1 = friendCoustomMapper.selectUserFriendsByFriendId(m.getUserId(),2);
        list.addAll(list1);
        List<Map> matchUsers = new ArrayList<Map>();
        for (int i = 0 ;i < m.getPhonenums().size(); i++) {
            Map phonenums = m.getPhonenums().get(i);
            String phonenum = (String) phonenums.get("phonenum");
            UserExample example = new UserExample();
            UserExample.Criteria  criteria = example.createCriteria();
            criteria.andPhonenumEqualTo(phonenum);
            List<User> users = userMapper.selectByExample(example);
            if (users.size()>0) {
                User user = users.get(0);
                Map matchUser = new HashMap();
                matchUser.put("phonenum", user.getPhonenum());
                matchUser.put("username", user.getUsername());
                matchUser.put("icon", user.getIcon());
                matchUser.put("userId", user.getId());
                if (m.getUserId() == user.getId()) {
                    matchUser.put("isAdd", true);
                } else {
                    matchUser.put("isAdd", false);
                    for (int j =0 ;j<list.size();j++){
                        FriendCoustom friendCoustom = (FriendCoustom) list.get(j);
                        if (friendCoustom.getUserId() == user.getId()) {
                            matchUser.put("isAdd", true);
                        }
                    }
                }
                matchUsers.add(matchUser);
            }
        }
        session.close();
        Response response = new Response();
        response.setAction("matchPhoneNumber");
        response.setResult(matchUsers);
        response.setXeach(true);
        response.setMessage("匹配手机通讯录");
        return response;
    }

    @Override
    public Response searchFriendsMessage(IMMessage m) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhonenumEqualTo(m.getPhoneNum());
        List<User> list = userMapper.selectByExample(example);


        int userId = m.getUserId();
        FriendMapper friendMapper = session.getMapper(FriendMapper.class);
        FriendExample example1 = new FriendExample();
        FriendExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andHostidEqualTo(userId);
        List<Friend> friends = friendMapper.selectByExample(example1);

        session.close();

        List<ContactsQueryVo> contactsQueryVos = new ArrayList<ContactsQueryVo>();
        for (int i = 0 ; i < list.size(); i++){
            User user = list.get(i);
            ContactsQueryVo contactsQueryVo = new ContactsQueryVo();
            contactsQueryVo.setBirthday(user.getBirthday());
            contactsQueryVo.setIcon(user.getIcon());
            contactsQueryVo.setLocation(user.getLocation());
            contactsQueryVo.setUserId(user.getId());
            contactsQueryVo.setPhonenum(user.getPhonenum());
            contactsQueryVo.setSex(user.getSex());
            contactsQueryVo.setUsername(user.getUsername());
            contactsQueryVo.setAdded(false);

            for (int j = 0; j < friends.size(); j++) {
                Friend friend = friends.get(j);
                if (friend.getFriendid() == contactsQueryVo.getUserId() && friend.getState() == 2) {
                    contactsQueryVo.setAdded(true);
                }
            }
            contactsQueryVos.add(contactsQueryVo);
        }

        Response response = new Response();
        response.setAction("searchFriends");
        response.setMessage("搜索好友结果");
        response.setResult(contactsQueryVos);
        response.setXeach(true);
        return response;
    }

    @Override
    public Response manageFriend(IMMessage m) throws Exception {

        Response response = null;
        switch(m.getType()){
            case 1:
                //请求添加
                response = requestAddFriend(m);
                break;
            case 2:
                //同意
                response = addFriend(m,0);
                break;
            case 3:
                //拒绝
                break;
            case 4:
                //删除
                response = delectFriend(m);
                break;
                //扫一扫添加
            case 5:
                response = scanAddFriend(m);
                break;
            default:
                //...;
                break;
        }
        return response;
    }

    /**
     * 扫一扫添加好友
     * */
    public Response scanAddFriend (IMMessage m) throws Exception{

        /**
         * 1。先检查是不是好友
         * */
        Response response = new Response();
        boolean res = checkFriend(m);

        if (res) {
            response.setMessage("扫一扫添加好友,已是好友，不能添加");
            response.setXeach(false);
        }else{
           response = addFriend(m,1);
            response.setXeach(true);
            response.setMessage("扫一扫添加好友，添加成功");
        }



        return response;
    }

    @Override
    public Response changeFriendsNotes(IMMessage m) throws Exception {
        String username = m.getUsername();
        int userId = m.getFriendId();
        int formUserId = m.getUserId();
        SqlSession session = MyBatisUtil.getSession();

        FriendCoustomMapper friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        List list =  friendCoustomMapper.selectUserRemark(userId,formUserId);
        if (list.size() == 0) {
            friendCoustomMapper.insertUserRemarks(userId,username,formUserId,1);
        }else {
            friendCoustomMapper.updateUserRemarks(userId,username,formUserId);
        }
        session.commit();
        session.close();
        Response response = new Response();

        response.setAction("changeFriendsNotes");
        response.setXeach(true);
        response.setResult("");
        response.setMessage("修改好友备注");
        return  response;
    }

    //确认添加
    private static Response addFriend(IMMessage m, int type) throws  Exception {



        SqlSession session = MyBatisUtil.getSession();

        FriendCoustomMapper friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        //删除关系
        friendCoustomMapper.delectFriend(m.getUserId(),m.getFriendId());
        session.commit();

        friendCoustomMapper.delectFriend(m.getFriendId(),m.getUserId());
        session.commit();

       FriendMapper friendMapper = session.getMapper(FriendMapper.class);

        Friend friend = new Friend();
        friend.setFriendid(m.getFriendId());
        friend.setHostid(m.getUserId());
        friend.setState(2);
        friend.setType(type);
        friend.setRoomid("roomId" + "-" +m.getUserId() + "-" + m.getFriendId());
        friendMapper.insert(friend);
        session.commit();
        //设置备注
        List friendUserRemarks = friendCoustomMapper.selectUserRemark(m.getUserId(),m.getFriendId());

        if (friendUserRemarks.size() == 0) {
            friendCoustomMapper.insertUserRemarks(m.getUserId(),"",m.getFriendId(),0);
            session.commit();
        }
        List userUserRemarks = friendCoustomMapper.selectUserRemark(m.getFriendId(),m.getUserId());
        if (userUserRemarks.size() == 0) {
            friendCoustomMapper.insertUserRemarks(m.getFriendId(),"",m.getUserId(),0);
            session.commit();
        }


        session.close();
        Map map = new HashMap();
        map.put("roomId",friend.getRoomid());
        map.put("userId",m.getUserId());
        map.put("friendId",m.getFriendId());

        Response response = new Response();
        response.setMessage("确认添加");
        response.setXeach(true);
        response.setAction("addFriend");
        response.setResult(map);
        return response;
    }
    //删除好友
    private static Response delectFriend(IMMessage messageBean) throws  Exception {
        SqlSession session = MyBatisUtil.getSession();
        FriendCoustomMapper friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        MessageCoustomMapper messageCoustomMapper = session.getMapper(MessageCoustomMapper.class);
        //删除好友聊天记录

        List list = friendCoustomMapper.selectUserFriendsByFriendId(messageBean.getUserId(),2);

        List list1 = friendCoustomMapper.selectUserFriendsByHostId(messageBean.getUserId(), 2);

        list.addAll(list1);
        for (int i = 0 ;i<list.size(); i++) {
            FriendCoustom friendCoustom = (FriendCoustom) list.get(i);
            if (friendCoustom.getUserId() == messageBean.getFriendId()) {
                messageCoustomMapper.delectMessageByRoomId(friendCoustom.getRoomId());
                session.commit();
            }
        }

        //删除好友关系
        friendCoustomMapper.delectFriend(messageBean.getUserId(),messageBean.getFriendId());
        session.commit();

        friendCoustomMapper.delectFriend(messageBean.getFriendId(),messageBean.getUserId());
        session.commit();
        session.close();

        Response response = new Response();
        response.setXeach(true);
        response.setMessage("删除好友成功");
        response.setAction("delectFriend");
        response.setResult("");
        return response;
    }

    //请求添加
    private  static Response requestAddFriend(IMMessage m) throws  Exception{

        SqlSession session = MyBatisUtil.getSession();
        FriendCoustomMapper friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        List<Integer> list = friendCoustomMapper.selectFriendsByUserIdAndFriendsId(m.getUserId(),m.getFriendId(),2);
        if (list.size() == 0) {
            Friend friend = new Friend();
            friend.setFriendid(m.getFriendId());
            friend.setHostid(m.getUserId());
            friend.setState(1);
            FriendMapper friendMapper = session.getMapper(FriendMapper.class);
            friendMapper.insert(friend);
            session.commit();
        } else {

            return null;
            //存在请求
        }

        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(m.getUserId());

        session.close();

        HashMap map = new HashMap();
        map.put("userId",m.getUserId());
        map.put("content",m.getContent());



        map.put("icon",user.getIcon());
        map.put("username",user.getUsername());
        session.close();

        Response response = new Response();
        response.setAction("requestAddFriend");
        response.setResult(map);
        response.setMessage("请求添加好友");
        response.setXeach(true);

        return response;
    }
}

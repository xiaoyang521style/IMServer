package cn.appservice.serverimpl;

import cn.appservice.base.mapper.GroupMemberMapper;
import cn.appservice.base.mapper.GroupMessageMapper;
import cn.appservice.base.mapper.MessageMapper;
import cn.appservice.base.po.*;
import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.handler.HandleMessage;
import cn.appservice.mapper.FriendCoustomMapper;
import cn.appservice.mapper.GroupMessageCoustomMapper;
import cn.appservice.mapper.GroupPropertyCoustomMapper;
import cn.appservice.po.*;
import cn.appservice.redis.MessageRedis;
import cn.appservice.servers.MessageServer;
import cn.appservice.utils.MyBatisUtil;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * [com.appservice.serverimpl desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/7/2
 */
public class MessageServerImpl extends HandleMessage implements MessageServer {



    /**
     * 读取历史消息
     * */

    @Override
    public Response readHistroyMessage(IMMessage m) throws Exception {
        //获取session

        SqlSession session = MyBatisUtil.getSession();
        Response response =new Response();

        List<GroupMessageVo> groupMessages = readRedisGroupMessage(m.getUserId());
        List<MessagesVo> messages = readRedisMessageOneToOne(m.getUserId());

        //请求好友
        FriendCoustomMapper friendCoustomMapper = session.getMapper(FriendCoustomMapper.class);
        List<FriendCoustom> friendCoustoms = friendCoustomMapper.selectRequestAddFriend(m.getUserId());
        session.close();

        HistroyMessage histroyMessage = new HistroyMessage();
        histroyMessage.setMessages(messages);
        histroyMessage.setGroupMessage(groupMessages);
        histroyMessage.setRequestAdd(friendCoustoms);
        response.setResult(histroyMessage);
        response.setXeach(true);
        response.setAction("readHistroyMessage");
        response.setMessage("读取历史消息");

        return response;
    }

    /**
     * 消息保存
     * */
    @Override
    public void saveMessage(IMMessage m) throws Exception {
        if(m.getIsGroup() == 1) {
            saveGroupMessage(m);
        }else {
            saveOneToOneMessage(m);
        }
    }

    /**
     * 消息未读个数
     * */
    @Override
    public int unreadCount(int userId) throws Exception {
        List<GroupMessageVo> groupMessages = readRedisGroupMessage(userId);
        List<MessagesVo> messages = readRedisMessageOneToOne(userId);
        //群聊未读消息数
        int groupcount = 0;
        for (int i = 0; i < groupMessages.size() ; i++) {
            GroupMessageVo groupMessageVo = groupMessages.get(i);
            groupcount += groupMessageVo.getMessagesCount();
        }
        //单聊未读消息数
        int scount = 0;
        for (int i = 0; i < messages.size(); i++) {
            MessagesVo messagesVo =messages.get(i);
            scount+= messagesVo.getMessagesCount();
        }
        return groupcount + scount;
    }

    /**
     * 消息已读
     * */
    @Override
    public Response readMessage(IMMessage m) throws Exception {
        //获取session
        if (m.getIsGroup() == 1) {
            delectRedisMessage(m.getRoomId(),m.getUserId());

        } else {
            delectRedisMessage(m.getRoomId(),m.getFriendId());
        }
        return null;
    }

    /***
     * 获取群房间成员
     * */

    public List getGroupGroupMember(IMMessage m){
        SqlSession sqlSession = MyBatisUtil.getSession();
        GroupMemberMapper groupMemberMapper = sqlSession.getMapper(GroupMemberMapper.class);
        GroupMemberExample example = new GroupMemberExample();
        GroupMemberExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo((long) Integer.parseInt(m.getRoomId()));
        criteria.andGroupMemberIdentityEqualTo((short) 2);
        List<GroupMember> groupMembers = groupMemberMapper.selectByExample(example);
        sqlSession.close();
        return groupMembers;
    }

    /**
     * 群消息保存到redis中
     * */

    public void  saveGroupMessageToRedis(List<GroupMember> groupMembers, GroupMessage groupMessage) throws  Exception{

        String roomid = String.valueOf(groupMessage.getGroupId());
        int sendId = groupMessage.getSendid();
        //设置消息状态
            for (int i = 0; i < groupMembers.size(); i++) {
                GroupMember groupMember = groupMembers.get(i);
                int groupMemberUserId = groupMember.getGroupMemberId();
                if (sendId != groupMemberUserId) {
                    saveMessageToRedis(roomid, groupMessage.getMessageid(), groupMessage, groupMemberUserId);
                }
            }

    }
    /**消息撤回*/
    @Override
    public Object callBackMsg(IMMessage m) throws Exception {

        /**
         * 消息撤回只是在发送一边消息。
         * 根据messageId，isgroup在mysql中找到消息。
         * 更新消息状态，在线转发。
         * 未在线存放在redis中，等待用户下次登录，返给用户。
         * * */

        if (m.getIsGroup() == 1) {
            List<GroupMember> groupMembers = getGroupGroupMember(m);
            for (int i = 0; i < groupMembers.size(); i++) {
                GroupMember groupMember = groupMembers.get(i);
                if (groupMember.getGroupMemberId() != m.getUserId()) {
                    int userId = groupMember.getGroupMemberId();
                    String roomid = m.getRoomId() + '-' + String.valueOf(userId);
                    //删除reids中的未读消息
                    MessageRedis.delectMsgInRoom(roomid,m.getCallBackMsgId());
                }
            }

            GroupMessage groupMessage = new GroupMessage();
            groupMessage.setChattype(m.getChatType());
            groupMessage.setContent(m.getContent());
            groupMessage.setSendid(m.getUserId());
            groupMessage.setMessageid(m.getMessageId());
            groupMessage.setGroupId((long) Integer.parseInt(m.getRoomId()));
            groupMessage.setExtend(m.getCallBackMsgId());
            Date date = new Date( m.getSendtime());
            groupMessage.setSendtime(date);
            saveGroupMessageToRedis(groupMembers,groupMessage);

            //获取session

            SqlSession session = MyBatisUtil.getSession();
            //获取mapper接口的代理对象
            GroupMessageMapper messageMapper  = session.getMapper(GroupMessageMapper.class);

            //调用代理对象方法
            messageMapper.insert(groupMessage);
            //提交对话
            session.commit();

            //关闭session
            session.close();

            System.out.println("---------------");
            return groupMessage;
        } else {
            String roomid = m.getRoomId() + '-' + String.valueOf(m.getFriendId());
            //删除reids中的未读消息
            MessageRedis.delectMsgInRoom(roomid,m.getCallBackMsgId());
            Message message = new Message();
            message.setChattype(m.getChatType());
            message.setFriendid(m.getFriendId());
            message.setMessageid(m.getMessageId());
            message.setContent(m.getContent());
            Date date = new Date( m.getSendtime());
            message.setSendtime(date);
            message.setUserid(m.getUserId());
            message.setRoomid(m.getRoomId());
            message.setExtend(m.getCallBackMsgId());
            //获取session

            SqlSession session = MyBatisUtil.getSession();
            //获取mapper接口的代理对象
            MessageMapper messageMapper  = session.getMapper(MessageMapper.class);

            //调用代理对象方法
            messageMapper.insert(message);
            //提交对话
            session.commit();

            //关闭session
            session.close();
            saveMessageToRedis(message.getRoomid(),message.getMessageid(),message,m.getFriendId());
            return message;
        }

    }




    /**
     *
     * 群聊消息保存
     */
    private void saveGroupMessage(IMMessage m) throws  Exception {

        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setChattype(m.getChatType());
        groupMessage.setContent(m.getContent());
        groupMessage.setSendid(m.getUserId());
        groupMessage.setMessageid(m.getMessageId());
        groupMessage.setGroupId((long) Integer.parseInt(m.getRoomId()));
        Date date = new Date( m.getSendtime());
        groupMessage.setSendtime(date);

        //获取session
        SqlSession session = MyBatisUtil.getSession();

        //获取mapper接口的代理对象
        GroupMessageCoustomMapper groupMessageCoustomMapper = session.getMapper(GroupMessageCoustomMapper.class);

        //调用代理对象方法
        groupMessageCoustomMapper.insertReturnId(groupMessage);
        //提交对话
        session.commit();
        session.close();
        List<GroupMember> groupMembers = getGroupGroupMember(m);
        saveGroupMessageToRedis(groupMembers,groupMessage);
        System.out.println("---------------");
    }
    /**
     * 单聊保存
     * */
    private void saveOneToOneMessage(IMMessage m) throws  Exception{
        Message message = new Message();
        message.setChattype(m.getChatType());
        message.setFriendid(m.getFriendId());
        message.setMessageid(m.getMessageId());
        message.setContent(m.getContent());
        Date date = new Date( m.getSendtime());
        message.setSendtime(date);
        message.setUserid(m.getUserId());
        message.setRoomid(m.getRoomId());

        //获取session

        SqlSession session = MyBatisUtil.getSession();
        //获取mapper接口的代理对象
        MessageMapper messageMapper  = session.getMapper(MessageMapper.class);

        //调用代理对象方法
        messageMapper.insert(message);
        //提交对话
        session.commit();

        //关闭session
        session.close();

        saveMessageToRedis(m.getRoomId(),m.getMessageId(),message,m.getFriendId());

    }

    /***************************************redis处理************************************************/
    /**
     * 消息保存到redis里
     * */
    public static void saveMessageToRedis(String roomid, String messageId,Object message, int userId) throws  Exception {
        roomid = roomid + '-' + String.valueOf(userId);
        Gson gson = new Gson();
        String msgStr = gson.toJson(message);
        MessageRedis messageRedis = new MessageRedis();
        messageRedis.saveMessage(roomid,messageId,msgStr);
    }


    /**
     * 将消息从redis中移除
     * */

    public static void delectRedisMessage(String roomid, int userId) throws Exception{
        roomid = roomid + '-' + String.valueOf(userId);
        MessageRedis messageRedis = new MessageRedis();
        messageRedis.delectRoomMsg(roomid);
        messageRedis.delectChangegroupRoomInfo(roomid);
    }

    /**
     * 从redis中读取消息
     * */

    public static List readRedisGroupMessage(int userId) throws  Exception{

        //获取session
        SqlSession session = MyBatisUtil.getSession();

        GroupMemberMapper groupMemberMapper = session.getMapper(GroupMemberMapper.class);
        GroupMemberExample example = new GroupMemberExample();
        GroupMemberExample.Criteria criteria = example.createCriteria();
        criteria.andGroupMemberIdEqualTo(userId);
        criteria.andGroupMemberIdentityEqualTo((short) 2);
        List<GroupMember> groupMembers = groupMemberMapper.selectByExample(example);
        session.close();

        Gson gson = new Gson();
        List  groupMessageVos = new ArrayList();
        for (int i = 0; i < groupMembers.size(); i++) {
            GroupMember groupMember = groupMembers.get(i);

            String roomid = String.valueOf(groupMember.getGroupId());
            GroupMessageVo groupMessageVo = new GroupMessageVo();
            groupMessageVo.setRoomId(roomid);
            List messages = new ArrayList();

            roomid = roomid + '-' + userId;

            MessageRedis messageRedis = new MessageRedis();
            //获取redis房间消息
            Map<String, String> msgs = messageRedis.selMsg(roomid);
//            Map<String, String> roomInfo = messageRedis.selChangegroupRoomInfo(roomid);
//            groupMessageVo.setRoomInfoChnage((List) roomInfo.values());
            int count = 0;
            for (Map.Entry<String, String> entry : msgs.entrySet()) {
                String value = entry.getValue();
                GroupMessage groupMessage = gson.fromJson(value, GroupMessage.class);

                GroupMessageCoustom groupMessageCoustom = new GroupMessageCoustom();
                groupMessageCoustom.setChatType(groupMessage.getChattype());
                groupMessageCoustom.setContent(groupMessage.getContent());
                groupMessageCoustom.setMessageId(groupMessage.getMessageid());
                groupMessageCoustom.setState(0);
                groupMessageCoustom.setRoomId(String.valueOf(groupMessage.getGroupId()));
                groupMessageCoustom.setUserId(groupMessage.getSendid());
                groupMessageCoustom.setSendtime(groupMessage.getSendtime().getTime());
                groupMessageCoustom.setExtend(groupMessage.getExtend());
                messages.add(groupMessageCoustom);
                if (groupMessageCoustom.getState() != 7) {
                    count ++;
                }
            }
            if (messages.size() >0 ) {
                //获取session
                session = MyBatisUtil.getSession();
                GroupPropertyCoustomMapper groupPropertyCoustomMapper = session.getMapper(GroupPropertyCoustomMapper.class);
                List list = groupPropertyCoustomMapper.selectGroupInfo(Math.toIntExact(groupMember.getGroupId()));
                session.close();
                groupMessageVo.setMessagesCount(count);
                groupMessageVo.setMessages(messages);
                groupMessageVos.add(groupMessageVo);
                groupMessageVo.setGroupInfo((GroupInfoCoustom) list.get(0));
            }
        }

        return groupMessageVos;
    }


    /**
     * redis消息
     * */
    public static List readRedisMessageOneToOne(int userId) throws Exception{
        Gson gson = new Gson();
        ContactsServerImpl contactsServer = new ContactsServerImpl();
        //获取好友
        List<FriendCoustom> friendCoustoms =  contactsServer.selectFriendByUserId(userId);

        List messagesVos = new ArrayList();
        for (int i = 0; i <friendCoustoms.size() ; i++) {


            FriendCoustom friendCoustom = friendCoustoms.get(i);
            String roomid = friendCoustom.getRoomId();

            MessagesVo messagesVo = new MessagesVo();
            messagesVo.setRoomId(roomid);

            roomid = roomid + '-' + userId;
            //根据好友中的roomid获取未读消息
            MessageRedis messageRedis = new MessageRedis();
            Map<String, String> msgs = messageRedis.selMsg(roomid);

            int count = 0;
            List messages =  new ArrayList();
            for (Map.Entry<String, String> entry : msgs.entrySet()) {
                String value = entry.getValue();
                Message message = gson.fromJson(value, Message.class);

                MessageCoustom messageCoustom = new MessageCoustom();

                messageCoustom.setChatType(message.getChattype());
                messageCoustom.setContent(message.getContent());
                messageCoustom.setSendtime(message.getSendtime().getTime());
                messageCoustom.setMessageId(message.getMessageid());
                messageCoustom.setState(0);
                messageCoustom.setRoomId(message.getRoomid());
                messageCoustom.setFriendId(message.getFriendid());
                messageCoustom.setUserId(message.getUserid());
                messageCoustom.setExtend(message.getExtend());
                messages.add(messageCoustom);
                if (messageCoustom.getState() != 7) {
                    count ++;
                }

            }

            if (messages.size() >0 ) {
                messagesVo.setMessagesCount(count);
                messagesVo.setMessages(messages);
                messagesVos.add(messagesVo);
            }

        }

        return messagesVos;
    }



}

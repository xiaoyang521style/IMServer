package cn.appservice.serverimpl;

import cn.appservice.base.mapper.GroupDynamicPropertyMapper;
import cn.appservice.base.mapper.GroupMemberMapper;
import cn.appservice.base.mapper.GroupPropertyChangeMapper;
import cn.appservice.base.mapper.UserMapper;
import cn.appservice.base.po.*;
import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.handler.HandleMessage;
import cn.appservice.mapper.GroupMemberCoustomMapper;
import cn.appservice.mapper.GroupMessageCoustomMapper;
import cn.appservice.mapper.GroupPropertyCoustomMapper;
import cn.appservice.po.GroupInfoCoustom;
import cn.appservice.po.GroupInfoUser;
import cn.appservice.po.MemberUserInfo;
import cn.appservice.po.UserInfo;
import cn.appservice.redis.MessageRedis;
import cn.appservice.servers.GroupServer;
import cn.appservice.utils.MyBatisUtil;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

public class GroupServerImpl extends HandleMessage implements GroupServer {

    @Override
    public Response creatGroup(IMMessage m) throws Exception {

        SqlSession session = MyBatisUtil.getSession();
        //创建group
        Date date = new Date( m.getSendtime());
        GroupProperty groupProperty = new GroupProperty();
        groupProperty.setGroupCreaterId(m.getUserId());
        groupProperty.setGroupCreateDatetime(date);
        groupProperty.setGroupStatu(1);
        GroupPropertyCoustomMapper groupPropertyCoustomMapper = session.getMapper(GroupPropertyCoustomMapper.class);
        groupPropertyCoustomMapper.insertReturnId(groupProperty);
        session.commit();
        int groupId = Math.toIntExact(groupProperty.getId());

        //添加group_member
        GroupMemberMapper groupMemberMapper = session.getMapper(GroupMemberMapper.class);
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId((long)groupId);
        groupMember.setGroupMemberId(m.getUserId());
        groupMember.setGroupMemberJoinDatetime(date);
        groupMember.setGroupMemberIdentity((short) 1);
        groupMemberMapper.insert(groupMember);
        session.commit();

        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<Map> users = new ArrayList<Map>();
        for (int i = 0; i < m.getUserIds().size(); i++) {
            int userId = m.getUserIds().get(i);
            if (userId != m.getUserId()) {
                groupMember = new GroupMember();
                groupMember.setGroupId((long)groupId);
                groupMember.setGroupMemberId(userId);
                groupMember.setGroupMemberJoinDatetime(date);
                groupMember.setGroupMemberIdentity((short) 2);
                groupMemberMapper.insert(groupMember);
                session.commit();
                UserExample example = new UserExample();
                UserExample.Criteria criteria = example.createCriteria();
                criteria.andIdEqualTo(userId);
                List <User> list = userMapper.selectByExample(example);
                User user = list.get(0);
                Map map = new HashMap();
                map.put("username",user.getUsername());
                map.put("userId",user.getId());
                map.put("icon",user.getIcon());
                users.add(map);
            }
        }

        //添加群信息
        GroupDynamicProperty groupDynamicProper = new GroupDynamicProperty();
        groupDynamicProper.setGroupChangeDatetime(date);
        groupDynamicProper.setGroupDescription(m.getGroup_description());
        groupDynamicProper.setGroupOwnerUid(m.getUserId());
        groupDynamicProper.setGroupName(m.getGroupName());
        groupDynamicProper.setGroupId((long)groupId);
        session = MyBatisUtil.getSession();
        GroupDynamicPropertyMapper groupDynamicPropertyMapper = session.getMapper(GroupDynamicPropertyMapper.class);

        groupDynamicPropertyMapper.insertSelective(groupDynamicProper);
        session.commit();

        //添加群改变信息表
        GroupPropertyChangeMapper groupPropertyChangeMapper =session.getMapper(GroupPropertyChangeMapper.class);
        GroupPropertyChange groupPropertyChange = new GroupPropertyChange();
        groupPropertyChange.setChangeType(1);
        groupPropertyChange.setGroupCreateDatetime(date);
        groupPropertyChange.setGroupChangeDatetime(date);
        groupPropertyChange.setGroupStatu(0);
        groupPropertyChange.setGroupCreaterId(m.getUserId());
        groupPropertyChange.setGroupId((long)groupId);
        groupPropertyChangeMapper.insert(groupPropertyChange);
        session.commit();
        session.close();

        Map map = new HashMap();
        map.put("roomId",String.valueOf(groupId));
        map.put("groupName",groupDynamicProper.getGroupName());
        map.put("users",users);
        map.put("masterId",m.getUserId());

        Response response = new Response();
        response.setResult(map);
        response.setMessage("创建群");
        response.setAction("creatGroup");
        response.setXeach(true);
        return response;

    }

    @Override
    public GroupInfoCoustom selectGroupInfoBy(String roomId) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        GroupPropertyCoustomMapper groupPropertyCoustomMapper =session.getMapper(GroupPropertyCoustomMapper.class);
        List<GroupInfoCoustom> groupInfoCoustoms = groupPropertyCoustomMapper.selectGroupInfo(Integer.parseInt(roomId));
        session.close();
        return  groupInfoCoustoms.get(0);
    }

    /**更改群聊名字*/
    @Override
    public Response updateGroupName(IMMessage m) throws Exception {
        MyBatisUtil.getSession();
        SqlSession session = MyBatisUtil.getSession();

        GroupPropertyCoustomMapper groupPropertyCoustomMapper =  session.getMapper(GroupPropertyCoustomMapper.class);
        GroupDynamicProperty groupDynamicProperty = new GroupDynamicProperty();
        groupDynamicProperty.setGroupName(m.getGroupName());
        groupDynamicProperty.setGroupId(Long.parseLong(m.getRoomId()));
        groupPropertyCoustomMapper.updateGroupName(groupDynamicProperty);
        session.commit();

        Date time = new Date();
        groupPropertyCoustomMapper.updateGroupChangeTable(time, Integer.parseInt(m.getRoomId()));
        session.commit();
        session.close();
        GroupInfoCoustom groupInfoCoustom = selectGroupInfoBy(m.getRoomId());
        Map map = new HashMap();
        map.put("groupInfo",groupInfoCoustom);
        map.put("userId",groupInfoCoustom);
        map.put("time",System.currentTimeMillis());
        map.put("roomId",m.getRoomId());
        map.put("groupName",m.getGroupName());
        saveGroupInfoToRedis(map,m.getUserId(),m.getRoomId(),groupInfoCoustom.getUsers(),"updateGroupName");
        Response response = new Response();
        response.setMessage("更新群名字");
        response.setXeach(true);
        response.setResult(map);
        response.setAction("updateGroupName");
        return response;
    }
    /**拉人加入群聊*/
    @Override
    public Response joinGroup(IMMessage m) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        GroupPropertyCoustomMapper groupPropertyCoustomMapper = session.getMapper(GroupPropertyCoustomMapper.class);

        Date time = new Date();
        for (int i =0;i < m.getUserIds().size();i++) {
            groupPropertyCoustomMapper.insertGroupMember(Integer.parseInt(m.getRoomId()), m.getUserIds().get(i),
                    2,
                    time,
                    m.getJoinType());
            session.commit();
        }

        groupPropertyCoustomMapper.updateGroupChangeTable(time, Integer.parseInt(m.getRoomId()));
        session.commit();

        GroupInfoCoustom groupInfoCoustom = selectGroupInfoBy(m.getRoomId());
        session.close();
        Map map = new HashMap();
        map.put("joinUserId",m.getUserId());
        map.put("userIds",m.getUserIds());
        map.put("groupInfo",groupInfoCoustom);
        map.put("roomId",m.getRoomId());
        map.put("time",System.currentTimeMillis());
        map.put("joinType",m.getJoinType());
        if (m.getJoinType() == 1) {

        }
        saveGroupInfoToRedis(map,m.getUserId(),m.getRoomId(),groupInfoCoustom.getUsers(),"newJoinMember");
        Response response = new Response();
        response.setMessage("群新增人员");
        response.setXeach(true);
        response.setResult(map);
        response.setAction("newJoinMember");
        return response;
    }

    /**退出群聊*/
    @Override
    public Response quitGroup(IMMessage m) throws Exception {
        SqlSession session = MyBatisUtil.getSession();

        GroupMessageCoustomMapper groupMessageCoustomMapper = session.getMapper(GroupMessageCoustomMapper.class);
        List <GroupMessage> groupMessages = groupMessageCoustomMapper.selectMessageIdBySendId(m.getUserId(),
                Integer.parseInt(m.getRoomId()));
        for (int i = 0 ; i< groupMessages.size();i++) {
            GroupMessage groupMessage = groupMessages.get(i);
            groupMessageCoustomMapper.delectGroupMessageStateByMeessageId(groupMessage.getId());
            session.commit();
        }

        /**
         *
         * 判断是否群主，群主退出该群后，群主由第二个进群的人来继承，会排列在群成员列表的第一位。若您再次回到该群，之前群主身份不会恢复。
         *
         * */
        GroupPropertyCoustomMapper groupPropertyCoustomMapper = session.getMapper(GroupPropertyCoustomMapper.class);
        GroupInfoCoustom groupInfoCoustom = selectGroupInfoBy(m.getRoomId());
        if (groupInfoCoustom.getUsers().size() == 1) {
            return null;
        }
        if (groupInfoCoustom.getMasterId() == m.getUserId()) {
            GroupInfoUser userInfo = groupInfoCoustom.getUsers().get(1);
            groupPropertyCoustomMapper.updateGroupGroupOwnerUid(Integer.parseInt(m.getRoomId()),userInfo.getUserId());
            session.commit();
            groupPropertyCoustomMapper.updateGroupMember(1,userInfo.getUserId(),Integer.parseInt(m.getRoomId()));
            session.commit();
        }

        groupPropertyCoustomMapper.delectGroupMember(Integer.parseInt(m.getRoomId()),
                m.getUserId());
        session.commit();

        Date time = new Date();
        groupPropertyCoustomMapper.updateGroupChangeTable(time, Integer.parseInt(m.getRoomId()));
        session.commit();

        groupInfoCoustom = selectGroupInfoBy(m.getRoomId());
        session.close();
        Map map = new HashMap();
        map.put("userId",m.getUserId());
        map.put("groupInfo",groupInfoCoustom);
        map.put("roomId",m.getRoomId());
        map.put("time",System.currentTimeMillis());
        saveGroupInfoToRedis(map,m.getUserId(),m.getRoomId(),groupInfoCoustom.getUsers(),"memberQuitGroup");
        Response response = new Response();
        response.setMessage("群减少人员");
        response.setXeach(true);
        response.setResult(map);
        response.setAction("memberQuitGroup");
        return response;
    }

    @Override
    public List<Integer> getGroupUsersId(int groupId) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        GroupMemberCoustomMapper groupMemberCoustomMapper = session.getMapper(GroupMemberCoustomMapper.class);
        List<Integer> list = groupMemberCoustomMapper.selectGroupMemberUserIdsByGroupId(groupId);
        session.close();
        return list;
    }
    /**进入群聊房间*/
    @Override
    public Response enterGroupRoom(IMMessage m) throws Exception {
        GroupInfoCoustom groupInfoCoustom =  selectGroupInfoBy(m.getRoomId());
        Response response = new Response();
        response.setResult(groupInfoCoustom);
        response.setMessage("进入房间");
        response.setAction("enterGroupRoom");
        response.setXeach(true);
        return response;
    }

    /**移除群聊*/
    @Override
    public Response deleteGroupUserHandel(IMMessage m) throws Exception {
        Response response = new Response();
        GroupInfoCoustom groupInfoCoustom = selectGroupInfoBy(m.getRoomId());
        if (groupInfoCoustom.getMasterId() != m.getMasterId()) {
            response.setXeach(false);
            response.setMessage("不是群主");
            return response;
        }else {
            SqlSession session = MyBatisUtil.getSession();
            GroupMemberCoustomMapper groupMemberCoustomMapper = session.getMapper(GroupMemberCoustomMapper.class);
            groupMemberCoustomMapper.delectGroupMemberUser(Integer.valueOf(m.getRoomId()),m.getUserId());
            session.commit();
            GroupPropertyCoustomMapper groupPropertyCoustomMapper = session.getMapper(GroupPropertyCoustomMapper.class);
            Date time = new Date();
            groupPropertyCoustomMapper.updateGroupChangeTable(time, Integer.parseInt(m.getRoomId()));
            session.commit();
            session.close();
            response.setXeach(true);
        }
        Map map = new HashMap();
        map.put("roomId",m.getRoomId());
        map.put("userId",m.getUserId());
        map.put("groupInfo",selectGroupInfoBy(m.getRoomId()));
        map.put("time",System.currentTimeMillis());
        saveGroupInfoToRedis(map,m.getMasterId(),m.getRoomId(),groupInfoCoustom.getUsers(),"deleteGroupUser");
        response.setResult(map);
        response.setAction("deleteGroupUser");
        response.setXeach(true);
        response.setMessage("移除群聊成功");
        return response;
    }
    /**交换群主*/
    @Override
    public Response exchangeMaster(IMMessage m) throws Exception {
        Response response = new Response();
        GroupInfoCoustom groupInfoCoustom = selectGroupInfoBy(m.getRoomId());
        if (groupInfoCoustom.getMasterId() != m.getMasterId()) {
            response.setXeach(false);
            response.setMessage("不是群主");
            return response;
        } else {
            SqlSession session = MyBatisUtil.getSession();
            GroupPropertyCoustomMapper groupPropertyCoustomMapper = session.getMapper(GroupPropertyCoustomMapper.class);
            groupPropertyCoustomMapper.updateGroupGroupOwnerUid(Integer.parseInt(m.getRoomId()),m.getUserId());
            session.commit();
            groupPropertyCoustomMapper.updateGroupMember(1,m.getUserId(),Integer.parseInt(m.getRoomId()));
            groupPropertyCoustomMapper.updateGroupMember(2,m.getMasterId(),Integer.parseInt(m.getRoomId()));
            session.commit();
            Date time = new Date();
            groupPropertyCoustomMapper.updateGroupChangeTable(time, Integer.parseInt(m.getRoomId()));
            session.commit();
            session.close();

        }

        Map map = new HashMap();
        map.put("roomId",m.getRoomId());
        map.put("masterId",m.getUserId());
        map.put("groupInfo",selectGroupInfoBy(m.getRoomId()));
        map.put("time",System.currentTimeMillis());

        saveGroupInfoToRedis(map,m.getUserId(),m.getRoomId(),groupInfoCoustom.getUsers(),"exchangeMaster");
        response.setResult(map);
        response.setAction("exchangeMaster");
        response.setXeach(true);
        response.setMessage("交换群主成功");
        return response;
    }

    /**
     * 将群信息变动保存到redis中
     * */

    public void  saveGroupInfoToRedis(Object object, int userId, String roomId, List<GroupInfoUser> groupInfoUsers ,String action) throws  Exception{
        for (int i = 0; i < groupInfoUsers.size(); i++) {
            GroupInfoUser groupInfoUser = groupInfoUsers.get(i);
            if (userId != groupInfoUser.getUserId()) {
                Map map = (Map) object;
                map.put("action",action);
                String id =  roomId + String.valueOf(map.get("time"));
                MessageRedis messageRedis = new MessageRedis();
                String key = roomId + String.valueOf(groupInfoUser.getUserId());
                Gson gson = new Gson();
                messageRedis.saveChangegroupRoomInfo(key,id ,gson.toJson(object));
            }
        }
    }

}

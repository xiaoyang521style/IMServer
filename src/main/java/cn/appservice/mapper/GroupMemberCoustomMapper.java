package cn.appservice.mapper;

import cn.appservice.po.MemberUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMemberCoustomMapper {
    public List<Integer> selectGroupMemberUserIdsByGroupId(int groupId);
    public List<MemberUserInfo> selectUserInfo(int groupId);

    public void delectGroupMemberUser(@Param("groupId")int groupId,@Param("group_member_id") int group_member_id);
}

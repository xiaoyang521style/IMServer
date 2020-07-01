package cn.appservice.mapper;

import cn.appservice.base.po.GroupDynamicProperty;
import cn.appservice.base.po.GroupProperty;
import cn.appservice.po.GroupInfoCoustom;
import cn.appservice.po.GroupNameChange;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GroupPropertyCoustomMapper {

    public int insertReturnId(GroupProperty groupProperty);

    public List<GroupInfoCoustom> selectGroupInfo(int id);
    
    public void updateGroupName(GroupDynamicProperty groupDynamicProperty);
    
    public void updateGroupChangeTable(@Param("time") Date time, @Param("groupId") int groupId);

    public void insertGroupMember(@Param("groupId") int groupId,
                                  @Param("groupMemberId") int groupMemberId,
                                  @Param("groupMemberIdentity") int groupMemberIdentity,
                                  @Param("groupMemberJoinDatetime") Date groupMemberJoinDatetime,
                                  @Param("groupJoinType") int groupJoinType);

    public void delectGroupMember(@Param("groupId") int groupId, @Param("groupMemberId") int groupMemberId);

    public void insertGroupNameChange(GroupNameChange groupNameChange);

    public void updateGroupGroupOwnerUid(@Param("groupId") int groupId,
                                         @Param("group_owner_uid") int group_owner_uid);

    public void updateGroupMember(@Param("group_member_identity")int group_member_identity,@Param("group_member_id") int group_member_id,@Param("group_id")int group_id);
}

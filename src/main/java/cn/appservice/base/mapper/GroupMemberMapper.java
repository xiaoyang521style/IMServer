package cn.appservice.base.mapper;

import cn.appservice.base.po.GroupMember;
import cn.appservice.base.po.GroupMemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMemberMapper {
    int countByExample(GroupMemberExample example);

    int deleteByExample(GroupMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    List<GroupMember> selectByExample(GroupMemberExample example);

    GroupMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);

    int updateByExample(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);

    int updateByPrimaryKeySelective(GroupMember record);

    int updateByPrimaryKey(GroupMember record);
}
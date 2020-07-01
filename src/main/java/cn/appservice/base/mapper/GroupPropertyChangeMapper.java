package cn.appservice.base.mapper;

import cn.appservice.base.po.GroupPropertyChange;
import cn.appservice.base.po.GroupPropertyChangeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupPropertyChangeMapper {
    int countByExample(GroupPropertyChangeExample example);

    int deleteByExample(GroupPropertyChangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupPropertyChange record);

    int insertSelective(GroupPropertyChange record);

    List<GroupPropertyChange> selectByExample(GroupPropertyChangeExample example);

    GroupPropertyChange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupPropertyChange record, @Param("example") GroupPropertyChangeExample example);

    int updateByExample(@Param("record") GroupPropertyChange record, @Param("example") GroupPropertyChangeExample example);

    int updateByPrimaryKeySelective(GroupPropertyChange record);

    int updateByPrimaryKey(GroupPropertyChange record);
}
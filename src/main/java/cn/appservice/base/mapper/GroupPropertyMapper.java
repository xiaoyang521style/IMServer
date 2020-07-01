package cn.appservice.base.mapper;

import cn.appservice.base.po.GroupProperty;
import cn.appservice.base.po.GroupPropertyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupPropertyMapper {
    int countByExample(GroupPropertyExample example);

    int deleteByExample(GroupPropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupProperty record);

    int insertSelective(GroupProperty record);

    List<GroupProperty> selectByExample(GroupPropertyExample example);

    GroupProperty selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupProperty record, @Param("example") GroupPropertyExample example);

    int updateByExample(@Param("record") GroupProperty record, @Param("example") GroupPropertyExample example);

    int updateByPrimaryKeySelective(GroupProperty record);

    int updateByPrimaryKey(GroupProperty record);
}
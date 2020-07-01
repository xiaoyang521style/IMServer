package cn.appservice.base.mapper;

import cn.appservice.base.po.GroupDynamicProperty;
import cn.appservice.base.po.GroupDynamicPropertyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupDynamicPropertyMapper {
    int countByExample(GroupDynamicPropertyExample example);

    int deleteByExample(GroupDynamicPropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupDynamicProperty record);

    int insertSelective(GroupDynamicProperty record);

    List<GroupDynamicProperty> selectByExample(GroupDynamicPropertyExample example);

    GroupDynamicProperty selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupDynamicProperty record, @Param("example") GroupDynamicPropertyExample example);

    int updateByExample(@Param("record") GroupDynamicProperty record, @Param("example") GroupDynamicPropertyExample example);

    int updateByPrimaryKeySelective(GroupDynamicProperty record);

    int updateByPrimaryKey(GroupDynamicProperty record);
}
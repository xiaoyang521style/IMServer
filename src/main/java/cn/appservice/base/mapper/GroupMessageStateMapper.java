package cn.appservice.base.mapper;

import cn.appservice.base.po.GroupMessageState;
import cn.appservice.base.po.GroupMessageStateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMessageStateMapper {
    int countByExample(GroupMessageStateExample example);

    int deleteByExample(GroupMessageStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupMessageState record);

    int insertSelective(GroupMessageState record);

    List<GroupMessageState> selectByExample(GroupMessageStateExample example);

    GroupMessageState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupMessageState record, @Param("example") GroupMessageStateExample example);

    int updateByExample(@Param("record") GroupMessageState record, @Param("example") GroupMessageStateExample example);

    int updateByPrimaryKeySelective(GroupMessageState record);

    int updateByPrimaryKey(GroupMessageState record);
}
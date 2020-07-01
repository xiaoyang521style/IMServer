package cn.appservice.base.mapper;

import cn.appservice.base.po.GroupMessage;
import cn.appservice.base.po.GroupMessageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMessageMapper {
    int countByExample(GroupMessageExample example);

    int deleteByExample(GroupMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupMessage record);

    int insertSelective(GroupMessage record);

    List<GroupMessage> selectByExampleWithBLOBs(GroupMessageExample example);

    List<GroupMessage> selectByExample(GroupMessageExample example);

    GroupMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupMessage record, @Param("example") GroupMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") GroupMessage record, @Param("example") GroupMessageExample example);

    int updateByExample(@Param("record") GroupMessage record, @Param("example") GroupMessageExample example);

    int updateByPrimaryKeySelective(GroupMessage record);

    int updateByPrimaryKeyWithBLOBs(GroupMessage record);

    int updateByPrimaryKey(GroupMessage record);
}
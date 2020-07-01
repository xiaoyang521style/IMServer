package cn.appservice.base.mapper;

import cn.appservice.base.po.MessageState;
import cn.appservice.base.po.MessageStateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageStateMapper {
    int countByExample(MessageStateExample example);

    int deleteByExample(MessageStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageState record);

    int insertSelective(MessageState record);

    List<MessageState> selectByExample(MessageStateExample example);

    MessageState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageState record, @Param("example") MessageStateExample example);

    int updateByExample(@Param("record") MessageState record, @Param("example") MessageStateExample example);

    int updateByPrimaryKeySelective(MessageState record);

    int updateByPrimaryKey(MessageState record);
}
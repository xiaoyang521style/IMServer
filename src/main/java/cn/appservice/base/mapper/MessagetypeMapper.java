package cn.appservice.base.mapper;

import cn.appservice.base.po.Messagetype;
import cn.appservice.base.po.MessagetypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessagetypeMapper {
    int countByExample(MessagetypeExample example);

    int deleteByExample(MessagetypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Messagetype record);

    int insertSelective(Messagetype record);

    List<Messagetype> selectByExample(MessagetypeExample example);

    Messagetype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Messagetype record, @Param("example") MessagetypeExample example);

    int updateByExample(@Param("record") Messagetype record, @Param("example") MessagetypeExample example);

    int updateByPrimaryKeySelective(Messagetype record);

    int updateByPrimaryKey(Messagetype record);
}
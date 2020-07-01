package cn.appservice.base.mapper;

import cn.appservice.base.po.Userremarks;
import cn.appservice.base.po.UserremarksExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserremarksMapper {
    int countByExample(UserremarksExample example);

    int deleteByExample(UserremarksExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userremarks record);

    int insertSelective(Userremarks record);

    List<Userremarks> selectByExample(UserremarksExample example);

    Userremarks selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userremarks record, @Param("example") UserremarksExample example);

    int updateByExample(@Param("record") Userremarks record, @Param("example") UserremarksExample example);

    int updateByPrimaryKeySelective(Userremarks record);

    int updateByPrimaryKey(Userremarks record);
}
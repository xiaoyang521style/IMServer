package cn.appservice.base.mapper;

import cn.appservice.base.po.Appinfouser;
import cn.appservice.base.po.AppinfouserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppinfouserMapper {
    int countByExample(AppinfouserExample example);

    int deleteByExample(AppinfouserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Appinfouser record);

    int insertSelective(Appinfouser record);

    List<Appinfouser> selectByExample(AppinfouserExample example);

    Appinfouser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Appinfouser record, @Param("example") AppinfouserExample example);

    int updateByExample(@Param("record") Appinfouser record, @Param("example") AppinfouserExample example);

    int updateByPrimaryKeySelective(Appinfouser record);

    int updateByPrimaryKey(Appinfouser record);
}
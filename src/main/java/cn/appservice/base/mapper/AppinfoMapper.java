package cn.appservice.base.mapper;

import cn.appservice.base.po.Appinfo;
import cn.appservice.base.po.AppinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppinfoMapper {
    int countByExample(AppinfoExample example);

    int deleteByExample(AppinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Appinfo record);

    int insertSelective(Appinfo record);

    List<Appinfo> selectByExample(AppinfoExample example);

    Appinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Appinfo record, @Param("example") AppinfoExample example);

    int updateByExample(@Param("record") Appinfo record, @Param("example") AppinfoExample example);

    int updateByPrimaryKeySelective(Appinfo record);

    int updateByPrimaryKey(Appinfo record);
}
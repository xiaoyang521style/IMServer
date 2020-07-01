package cn.appservice.base.mapper;

import cn.appservice.base.po.Deviceinfo;
import cn.appservice.base.po.DeviceinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceinfoMapper {
    int countByExample(DeviceinfoExample example);

    int deleteByExample(DeviceinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Deviceinfo record);

    int insertSelective(Deviceinfo record);

    List<Deviceinfo> selectByExample(DeviceinfoExample example);

    Deviceinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Deviceinfo record, @Param("example") DeviceinfoExample example);

    int updateByExample(@Param("record") Deviceinfo record, @Param("example") DeviceinfoExample example);

    int updateByPrimaryKeySelective(Deviceinfo record);

    int updateByPrimaryKey(Deviceinfo record);
}
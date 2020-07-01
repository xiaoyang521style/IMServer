package cn.appservice.base.mapper;

import cn.appservice.base.po.Userdevice;
import cn.appservice.base.po.UserdeviceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserdeviceMapper {
    int countByExample(UserdeviceExample example);

    int deleteByExample(UserdeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userdevice record);

    int insertSelective(Userdevice record);

    List<Userdevice> selectByExample(UserdeviceExample example);

    Userdevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userdevice record, @Param("example") UserdeviceExample example);

    int updateByExample(@Param("record") Userdevice record, @Param("example") UserdeviceExample example);

    int updateByPrimaryKeySelective(Userdevice record);

    int updateByPrimaryKey(Userdevice record);
}
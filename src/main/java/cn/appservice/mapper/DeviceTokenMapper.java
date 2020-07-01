package cn.appservice.mapper;

import cn.appservice.po.DeviceTokenQuery;

import java.util.List;

public interface DeviceTokenMapper {

    //查询 deviceToken deviceModel

    public List<DeviceTokenQuery> selectDeviceInfoByUserId(int userd) throws  Exception;


}

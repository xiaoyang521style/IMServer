package cn.appservice.mapper;

import cn.appservice.po.AddMeSetting;

import java.util.List;

public interface AddMeSettingMapper {

    public int insertAddMeSetting(AddMeSetting addMeSetting) throws Exception;
    public List selectAddMeSetting(int userId) throws Exception;
    public int updateAddMeSetting(AddMeSetting addMeSetting) throws Exception;
}

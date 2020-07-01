package cn.appservice.mapper;

import cn.appservice.po.UserSetting;

import java.util.List;

public interface UserSettingMapper {
    public List selectUserSetting(int userId) throws Exception;
    public void insertUserSetting(UserSetting userSetting) throws Exception;
    public void updateUserSetting(UserSetting userSetting) throws Exception;
}

package cn.appservice.mapper;

import cn.appservice.base.po.GroupMessage;
import cn.appservice.po.GroupMessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMessageCoustomMapper {
    public int insertReturnId(GroupMessage groupMessage);

    public void updateGroupMessage(Integer userId);

    public List<GroupMessageVo> readOfflineGroupMessage(Integer userId);
    
    public List selectMessageIdBySendId(@Param("sendId") Integer sendId, @Param("groupId") Integer groupId);
    
    public void delectGroupMessageStateByMeessageId(@Param("meessageId") Integer meessageId);
    
    public List selectUnreadcountByUserId(Integer userId);
    
}

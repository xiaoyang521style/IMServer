package cn.appservice.mapper;

import cn.appservice.po.MessagesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageCoustomMapper {
    public void updateMessageStateForRead(@Param("id") Integer userId, @Param("roomId") String roomId);
    public List<MessagesVo> readOfflineMessage(Integer userId);
    public void delectMessageByRoomId(String roomId);
    public int selectUnreadcountByUserId(Integer userId);
}

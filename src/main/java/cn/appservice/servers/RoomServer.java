package cn.appservice.servers;

import cn.appservice.entities.RoomUser;

import java.util.List;

/**
 * [com.appservice.servers desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/7/2
 */
public interface RoomServer {

    List<RoomUser> findRoomById(String id);

}

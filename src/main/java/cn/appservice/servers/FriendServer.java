package cn.appservice.servers;

import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;

public interface FriendServer {
    boolean checkFriend(IMMessage m) throws Exception;
    Response matchPhoneNumber(IMMessage m) throws Exception;
    Response searchFriendsMessage(IMMessage m) throws Exception;
    Response manageFriend(IMMessage m) throws Exception;
    Response changeFriendsNotes(IMMessage m) throws Exception;
}

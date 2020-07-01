package cn.appservice.mapper;

import cn.appservice.po.FriendCoustom;
import cn.appservice.po.UserRemarksCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendCoustomMapper {
   List<FriendCoustom> selectUserFriendsByFriendId(@Param("id") int id, @Param("state") int state);
   List<FriendCoustom> selectUserFriendsByHostId(@Param("id") int id, @Param("state") int state);

   List<FriendCoustom> selectUserFriendsByUserId(@Param("id") int id, @Param("state") int state);

   List<FriendCoustom> selectRequestAddFriend(int friendId);

   void delectFriend(@Param("hostId") int hostId, @Param("friendId") int friendId);

   /**
    * 好友备注
    * */
   void insertUserRemarks(@Param("userId") int userId, @Param("username") String username, @Param("formUserId") int formUserId, @Param("state") int state);

   void updateUserRemarks(@Param("userId") int userId, @Param("username") String username, @Param("formUserId") int formUserId);

   List<UserRemarksCustom> selectUserRemark(@Param("userId") int userId, @Param("formUserId") int formUserId);

   List<Integer> selectFriendsByUserIdAndFriendsId(@Param("userId") int userId, @Param("friendId") int friendId, @Param("state") int state);
}

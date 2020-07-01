package cn.appservice.servers;

import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.po.GroupInfoCoustom;

import java.util.List;

public interface GroupServer {
    /**
     *
     * 创建群
     */
    Response creatGroup(IMMessage m) throws Exception;

    GroupInfoCoustom selectGroupInfoBy(String roomId) throws  Exception;
    /**
     * 更新群名字
     * */
    Response updateGroupName(IMMessage m) throws Exception;

    /**
     * 加入群聊
     * */
    Response joinGroup(IMMessage m) throws  Exception;

    /**
     *退出群聊
     * */
    Response quitGroup(IMMessage m) throws  Exception;

    /**
     * 获取群组成员
     * */

    List<Integer> getGroupUsersId(int groupId) throws  Exception;

    /**
     * 进入群房间
     * */
    Response enterGroupRoom(IMMessage m)throws Exception;


    /**
     * 群主将人移除群
     * */
    Response deleteGroupUserHandel(IMMessage m)throws Exception;

    /**
     * 交换群主
     * */
    Response exchangeMaster(IMMessage m)throws Exception;


}

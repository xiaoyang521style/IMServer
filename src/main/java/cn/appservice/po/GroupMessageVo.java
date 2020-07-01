package cn.appservice.po;

import java.util.List;

public class GroupMessageVo {
    private  String roomId;

    private int messagesCount;

    private List roomInfoChnage;

    public List getRoomInfoChnage() {
        return roomInfoChnage;
    }

    public void setRoomInfoChnage(List roomInfoChnage) {
        this.roomInfoChnage = roomInfoChnage;
    }

    public GroupInfoCoustom getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfoCoustom groupInfo) {
        this.groupInfo = groupInfo;
    }

    private List<GroupMessageCoustom> messages;

    private GroupInfoCoustom groupInfo;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    public List <GroupMessageCoustom> getMessages() {
        return messages;
    }

    public void setMessages(List <GroupMessageCoustom> messages) {
        this.messages = messages;
    }
}

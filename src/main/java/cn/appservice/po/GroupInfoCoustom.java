package cn.appservice.po;

import java.util.List;

public class GroupInfoCoustom {

    private String roomId;

    private List <GroupInfoUser> users;

    private String groupName;

    private int masterId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List <GroupInfoUser> getUsers() {
        return users;
    }

    public void setUsers(List <GroupInfoUser> users) {
        this.users = users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }
}

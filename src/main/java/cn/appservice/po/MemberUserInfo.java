package cn.appservice.po;

import java.util.List;

public class MemberUserInfo {
    private String roomId;
    private String groupName;
    private List<MemberUser> users;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<MemberUser> getUsers() {
		return users;
	}

	public void setUsers(List<MemberUser> users) {
		this.users = users;
	}

	
}

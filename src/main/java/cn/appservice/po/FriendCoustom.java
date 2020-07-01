package cn.appservice.po;

public class FriendCoustom {

    private String username;

    private  String icon;

    private  int userId;

    private  String roomId;
    
    private  int remarkstate;
    
    private String remarkname;
    
    public String getRemarkname() {
		return remarkname;
	}

	public void setRemarkname(String remarkname) {
		this.remarkname = remarkname;
	}

	public int getRemarkstate() {
		return remarkstate;
	}

	public void setRemarkstate(int remarkstate) {
		this.remarkstate = remarkstate;
	}

	public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

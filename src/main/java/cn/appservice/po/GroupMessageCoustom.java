package cn.appservice.po;

public class GroupMessageCoustom {

    private String messageId;
    private String roomId;
    private String content;
    private long sendtime;
    private int userId;
    private int state;
    private int chatType;

    private int isCallBack;

    public int getIsCallBack() {
        return isCallBack;
    }

    private String extend;

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public void setIsCallBack(int isCallBack) {
        this.isCallBack = isCallBack;
    }

    public int getChatType() {
		return chatType;
	}

	public void setChatType(int chatType) {
		this.chatType = chatType;
	}

	public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSendtime() {
        return sendtime;
    }

    public void setSendtime(long sendtime) {
        this.sendtime = sendtime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

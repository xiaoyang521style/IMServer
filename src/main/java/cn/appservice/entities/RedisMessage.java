package cn.appservice.entities;

/**
 * [com.appservice.entities desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/4/16
 */
public class RedisMessage {

    private String senduname;
    private String senduid;
    private String touid;
    private String tonuame;
    private String isGroup;
    private String content;
    private String state;
    private String roomId;
    private String sendtime;
    private String messageId;
    private String chatType;

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenduname() {
        return senduname;
    }

    public void setSenduname(String senduname) {
        this.senduname = senduname;
    }

    public String getSenduid() {
        return senduid;
    }

    public void setSenduid(String senduid) {
        this.senduid = senduid;
    }

    public String getTouid() {
        return touid;
    }

    public void setTouid(String touid) {
        this.touid = touid;
    }

    public String getTonuame() {
        return tonuame;
    }

    public void setTonuame(String tonuame) {
        this.tonuame = tonuame;
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    @Override
    public String toString() {
        return "RedisMessage{" +
                "senduname='" + senduname + '\'' +
                ", senduid=" + senduid +
                ", touid =" + touid +
                ", tonuame =" + tonuame +
                ", touid=" + touid +
                ", touname='" + tonuame + '\'' +
                ", roomId='" + roomId + '\'' +
                ", state='" + state + '\'' +
                ", content='" + content + '\'' +
                ", messageId='" + messageId + '\'' +
                ", chatType='" + chatType + '\'' +
                '}';
    }


}

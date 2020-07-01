package cn.appservice.po;

import java.util.List;

public class MessagesVo {
    private  String roomId;

    private int messagesCount;

    private List<MessageCoustom> messages;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List <MessageCoustom> getMessages() {
        return messages;
    }

    public void setMessages(List <MessageCoustom> messages) {
        this.messages = messages;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }
}

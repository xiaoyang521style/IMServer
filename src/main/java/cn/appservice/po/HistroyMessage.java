package cn.appservice.po;

import java.util.List;

public class HistroyMessage {

    private List<MessagesVo> messages;

    private List<GroupMessageVo> groupMessage;

    private List<FriendCoustom> requestAdd;

    public List <MessagesVo> getMessages() {
        return messages;
    }

    public void setMessages(List <MessagesVo> messages) {
        this.messages = messages;
    }

    public List <GroupMessageVo> getGroupMessage() {
        return groupMessage;
    }

    public void setGroupMessage(List <GroupMessageVo> groupMessage) {
        this.groupMessage = groupMessage;
    }

    public List <FriendCoustom> getRequestAdd() {
        return requestAdd;
    }

    public void setRequestAdd(List <FriendCoustom> requestAdd) {
        this.requestAdd = requestAdd;
    }
}

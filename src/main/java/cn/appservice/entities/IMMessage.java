package cn.appservice.entities;

import cn.appservice.po.GroupInfoCoustom;

import java.util.List;
import java.util.Map;

public class IMMessage {

    private static final long serialVersionUID = 1L;

    /**
     * 行为
     * */
    private String action;

    /**

     * 登录管理
     *
     * */

    private String username;

    private String token;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    private String callBackMsgId;

    public String getCallBackMsgId() {
        return callBackMsgId;
    }

    public void setCallBackMsgId(String callBackMsgId) {
        this.callBackMsgId = callBackMsgId;
    }

    /**
     * 设备
     * */

    private String deviceToken;
    private int deviceModel;
    private String systemVersion;
    private String deviceName;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public int getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(int deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    private int joinType; //添加方式


    /**
     * friend管理
     * */

    private String phoneNum;

    private int type;

    private List<Map> phonenums;

    public List <Map> getPhonenums() {
        return phonenums;
    }

    public void setPhonenums(List <Map> phonenums) {
        this.phonenums = phonenums;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    /**
     * chatlist 对话列表
     * */


    /**
     * chat
     *
     *
     * **/

    private String messageId;// 消息id
    private int chatType;// 消息类型;1,文本；2，图片；3，小视频；4，文件；5，地理位置；6，语音；7，视频通话
    private String content;// 文本消息内容
    private String errorMsg;// 错误信息
    private int errorCode;// 错误代码
    private int friendId;//目标好友id
    private MessageFileBean chatFile;// 消息附件
    private long  sendtime; //发送时间
    private String roomId;


    private GroupInfoCoustom groupInfo;

    public GroupInfoCoustom getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfoCoustom groupInfo) {
        this.groupInfo = groupInfo;
    }

    /**群信息*/
    private List<Integer> userIds;
    private int group_change_type;
    private long group_change_datetime;
    private int group_owner_uid;
    private String groupName;
    private String group_announcement;
    private String group_description;
    private int isGroup;
    private int masterId;


    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    /**打电话*/
    private int callUserID;
    private int remoteUserID;
    private boolean isVideo;
    private int callRoomId;

    public int getCallUserID() {
        return callUserID;
    }

    public void setCallUserID(int callUserID) {
        this.callUserID = callUserID;
    }

    public int getRemoteUserID() {
        return remoteUserID;
    }

    public void setRemoteUserID(int remoteUserID) {
        this.remoteUserID = remoteUserID;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public int getCallRoomId() {
        return callRoomId;
    }

    public void setCallRoomId(int callRoomId) {
        this.callRoomId = callRoomId;
    }

    public int getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(int isGroup) {
        this.isGroup = isGroup;
    }

    public int getGroup_change_type() {
        return group_change_type;
    }

    public void setGroup_change_type(int group_change_type) {
        this.group_change_type = group_change_type;
    }

    public long getGroup_change_datetime() {
        return group_change_datetime;
    }

    public void setGroup_change_datetime(long group_change_datetime) {
        this.group_change_datetime = group_change_datetime;
    }

    public int getGroup_owner_uid() {
        return group_owner_uid;
    }

    public void setGroup_owner_uid(int group_owner_uid) {
        this.group_owner_uid = group_owner_uid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroup_name(String groupName) {
        this.groupName = groupName;
    }

    public String getGroup_announcement() {
        return group_announcement;
    }

    public void setGroup_announcement(String group_announcement) {
        this.group_announcement = group_announcement;
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }

    public List <Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List <Integer> userIds) {
        this.userIds = userIds;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public long getSendtime() {
        return sendtime;
    }

    public void setSendtime(long sendtime) {
        this.sendtime = sendtime;
    }

    public int getJoinType() {
        return joinType;
    }

    public void setJoinType(int joinType) {
        this.joinType = joinType;
    }

    public void setChatFile(MessageFileBean chatFile) {
        this.chatFile = chatFile;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public MessageFileBean getChatFile() {
        return chatFile;
    }

}

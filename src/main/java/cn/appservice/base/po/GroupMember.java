package cn.appservice.base.po;

import java.util.Date;

public class GroupMember {
    private Long id;

    private Long groupId;

    private Integer groupMemberId;

    private Short groupMemberIdentity;

    private Date groupMemberJoinDatetime;

    private String groupMemberName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(Integer groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public Short getGroupMemberIdentity() {
        return groupMemberIdentity;
    }

    public void setGroupMemberIdentity(Short groupMemberIdentity) {
        this.groupMemberIdentity = groupMemberIdentity;
    }

    public Date getGroupMemberJoinDatetime() {
        return groupMemberJoinDatetime;
    }

    public void setGroupMemberJoinDatetime(Date groupMemberJoinDatetime) {
        this.groupMemberJoinDatetime = groupMemberJoinDatetime;
    }

    public String getGroupMemberName() {
        return groupMemberName;
    }

    public void setGroupMemberName(String groupMemberName) {
        this.groupMemberName = groupMemberName == null ? null : groupMemberName.trim();
    }
}
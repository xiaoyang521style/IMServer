package cn.appservice.base.po;

import java.util.Date;

public class GroupDynamicProperty {
    private Long id;

    private Short groupChangeType;

    private Date groupChangeDatetime;

    private Long groupId;

    private Integer groupOwnerUid;

    private Integer groupLevel;

    private Integer groupParentsType;

    private Integer groupChildType;

    private String groupName;

    private String groupAnnouncement;

    private String groupDescription;

    private Integer groupJoinType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getGroupChangeType() {
        return groupChangeType;
    }

    public void setGroupChangeType(Short groupChangeType) {
        this.groupChangeType = groupChangeType;
    }

    public Date getGroupChangeDatetime() {
        return groupChangeDatetime;
    }

    public void setGroupChangeDatetime(Date groupChangeDatetime) {
        this.groupChangeDatetime = groupChangeDatetime;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupOwnerUid() {
        return groupOwnerUid;
    }

    public void setGroupOwnerUid(Integer groupOwnerUid) {
        this.groupOwnerUid = groupOwnerUid;
    }

    public Integer getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(Integer groupLevel) {
        this.groupLevel = groupLevel;
    }

    public Integer getGroupParentsType() {
        return groupParentsType;
    }

    public void setGroupParentsType(Integer groupParentsType) {
        this.groupParentsType = groupParentsType;
    }

    public Integer getGroupChildType() {
        return groupChildType;
    }

    public void setGroupChildType(Integer groupChildType) {
        this.groupChildType = groupChildType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupAnnouncement() {
        return groupAnnouncement;
    }

    public void setGroupAnnouncement(String groupAnnouncement) {
        this.groupAnnouncement = groupAnnouncement == null ? null : groupAnnouncement.trim();
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription == null ? null : groupDescription.trim();
    }

    public Integer getGroupJoinType() {
        return groupJoinType;
    }

    public void setGroupJoinType(Integer groupJoinType) {
        this.groupJoinType = groupJoinType;
    }
}
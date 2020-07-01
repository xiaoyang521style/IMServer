package cn.appservice.base.po;

import java.util.Date;

public class GroupPropertyChange {
    private Long id;

    private Long groupId;

    private Integer changeType;

    private Date groupCreateDatetime;

    private Date groupChangeDatetime;

    private Integer groupStatu;

    private Integer groupCreaterId;

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

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Date getGroupCreateDatetime() {
        return groupCreateDatetime;
    }

    public void setGroupCreateDatetime(Date groupCreateDatetime) {
        this.groupCreateDatetime = groupCreateDatetime;
    }

    public Date getGroupChangeDatetime() {
        return groupChangeDatetime;
    }

    public void setGroupChangeDatetime(Date groupChangeDatetime) {
        this.groupChangeDatetime = groupChangeDatetime;
    }

    public Integer getGroupStatu() {
        return groupStatu;
    }

    public void setGroupStatu(Integer groupStatu) {
        this.groupStatu = groupStatu;
    }

    public Integer getGroupCreaterId() {
        return groupCreaterId;
    }

    public void setGroupCreaterId(Integer groupCreaterId) {
        this.groupCreaterId = groupCreaterId;
    }
}
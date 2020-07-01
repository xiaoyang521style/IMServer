package cn.appservice.base.po;

import java.util.Date;

public class GroupProperty {
    private Long id;

    private Integer groupCreaterId;

    private Date groupCreateDatetime;

    private Integer groupStatu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupCreaterId() {
        return groupCreaterId;
    }

    public void setGroupCreaterId(Integer groupCreaterId) {
        this.groupCreaterId = groupCreaterId;
    }

    public Date getGroupCreateDatetime() {
        return groupCreateDatetime;
    }

    public void setGroupCreateDatetime(Date groupCreateDatetime) {
        this.groupCreateDatetime = groupCreateDatetime;
    }

    public Integer getGroupStatu() {
        return groupStatu;
    }

    public void setGroupStatu(Integer groupStatu) {
        this.groupStatu = groupStatu;
    }
}
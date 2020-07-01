package cn.appservice.base.po;

public class Userremarks {
    private Integer id;

    private Integer userid;

    private String username;

    private Integer formuserid;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getFormuserid() {
        return formuserid;
    }

    public void setFormuserid(Integer formuserid) {
        this.formuserid = formuserid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
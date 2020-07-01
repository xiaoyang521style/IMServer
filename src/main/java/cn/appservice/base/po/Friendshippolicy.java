package cn.appservice.base.po;

public class Friendshippolicy {
    private Integer id;

    private String friendshippolicy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFriendshippolicy() {
        return friendshippolicy;
    }

    public void setFriendshippolicy(String friendshippolicy) {
        this.friendshippolicy = friendshippolicy == null ? null : friendshippolicy.trim();
    }
}
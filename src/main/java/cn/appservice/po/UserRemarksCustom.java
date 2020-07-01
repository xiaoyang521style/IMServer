package cn.appservice.po;

public class UserRemarksCustom {
    private int userId;
    private String username;
    private int formUserId;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(int formUserId) {
        this.formUserId = formUserId;
    }
}

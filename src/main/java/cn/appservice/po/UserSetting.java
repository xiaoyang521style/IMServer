package cn.appservice.po;

public class UserSetting {

    private int id;
    private int userId;
    private int message_notif;
    private int show_msgDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMessage_notif() {
        return message_notif;
    }

    public void setMessage_notif(int message_notif) {
        this.message_notif = message_notif;
    }

    public int getShow_msgDetail() {
        return show_msgDetail;
    }

    public void setShow_msgDetail(int show_msgDetail) {
        this.show_msgDetail = show_msgDetail;
    }
}

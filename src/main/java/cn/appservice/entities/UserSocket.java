package cn.appservice.entities;

public class UserSocket {
    private int userId;
    private String address;
    private String port;

    public UserSocket() {
        super();
    }

    public UserSocket(int userId, String address, String port) {
         super();
         this.userId = userId;
         this.address = address;
         this.port = port;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "UserSocket{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", port=" + port +
                '}';
    }
}

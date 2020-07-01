package cn.appservice.po;

public class DeviceTokenQuery {
    private Integer deviceModel;
    private String deviceToken;
    private Integer deviceId;

    public Integer getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(Integer deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}

package cn.appservice.base.po;

public class Deviceinfo {
    private Integer id;

    private Integer devicemodel;

    private String devicename;

    private String systemversion;

    private String devicetoken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevicemodel() {
        return devicemodel;
    }

    public void setDevicemodel(Integer devicemodel) {
        this.devicemodel = devicemodel;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename == null ? null : devicename.trim();
    }

    public String getSystemversion() {
        return systemversion;
    }

    public void setSystemversion(String systemversion) {
        this.systemversion = systemversion == null ? null : systemversion.trim();
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken == null ? null : devicetoken.trim();
    }
}
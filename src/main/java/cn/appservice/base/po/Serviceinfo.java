package cn.appservice.base.po;

public class Serviceinfo {
    private Integer id;

    private String serverip;

    private Integer serverport;

    private Integer socketlivetime;

    private Boolean sslenable;

    private String privatekey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip == null ? null : serverip.trim();
    }

    public Integer getServerport() {
        return serverport;
    }

    public void setServerport(Integer serverport) {
        this.serverport = serverport;
    }

    public Integer getSocketlivetime() {
        return socketlivetime;
    }

    public void setSocketlivetime(Integer socketlivetime) {
        this.socketlivetime = socketlivetime;
    }

    public Boolean getSslenable() {
        return sslenable;
    }

    public void setSslenable(Boolean sslenable) {
        this.sslenable = sslenable;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey == null ? null : privatekey.trim();
    }
}
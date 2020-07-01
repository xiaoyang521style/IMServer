package cn.appservice.po;

public class ServerNodeInfo {

    private String serverip;

    private Integer serverport;

    private Integer socketlivetime;

    private Boolean sslenable;

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
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
}

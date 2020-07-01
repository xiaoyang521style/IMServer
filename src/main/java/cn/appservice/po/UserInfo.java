package cn.appservice.po;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)  
public class UserInfo {

	    private String sex;

	    private Date birthday;

	    private String phonenum;

	    private String username;

	    private  String icon;
	    
	    private String location;

	    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLocation() {

			return location;
		}

		public void setLocation(String location) {

			this.location = location;
		}

		public String getIcon() {

			return icon;
		}

		public void setIcon(String icon) {

			this.icon = icon;
		}

		public String getSex() {

			return sex;
		}

		public void setSex(String sex) {

			this.sex = sex;
		}

		public Date getBirthday() {

			return birthday;
		}

		public void setBirthday(Date birthday) {

			this.birthday = birthday;
		}

		public String getPhonenum() {

			return phonenum;
		}

		public void setPhonenum(String phonenum) {

			this.phonenum = phonenum;
		}

		public String getUsername() {

			return username;
		}

		public void setUsername(String username) {

			this.username = username;
		}


}

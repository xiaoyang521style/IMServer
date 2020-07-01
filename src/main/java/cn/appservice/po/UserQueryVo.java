package cn.appservice.po;

import cn.appservice.base.po.User;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)  
public class UserQueryVo {
	
	User user;
  	UserCustom userCustom;
  	
  	public User getUser() {
  		return user;
  	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserCustom getUserCustom() {
		return
				userCustom;
	}
	public void setUserCustom(UserCustom userCustom) {

  		this.userCustom = userCustom;
	}
  
}

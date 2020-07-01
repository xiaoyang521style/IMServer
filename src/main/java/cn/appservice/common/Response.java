package cn.appservice.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)  
public class Response {

	private boolean xeach;
	private String message;
	private String action;
	private Object result;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isXeach() {

		return xeach;
	}

	public void setXeach(boolean xeach) {

		this.xeach = xeach;
	}

	public String getMessage() {

		return message;
	}
	public void setMessage(String message) {

		this.message = message;
	}
	public Object  getResult() {
		return result;
	}
	public void setResult(Object result) {

		this.result = result;
	}
	
	
}

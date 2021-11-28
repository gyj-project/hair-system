package cn.bzu.hair.domain;

import java.io.Serializable;
import java.util.HashMap;

public class Msg implements Serializable {
	
	private Integer code;
	private String message;
	private HashMap<String, Object> extend = new HashMap<String, Object>();
	
	public Integer getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HashMap<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(HashMap<String, Object> extend) {
		this.extend = extend;
	}
	
	public static Msg success() {
		Msg msg = new Msg();
		msg.setCode(200);
		msg.setMessage("成功");
		return msg;
	}
	public static Msg fail() {
		Msg msg = new Msg();
		msg.setCode(500);
		msg.setMessage("错误");
		return msg;
	}
	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
		
	}

}

package com.javaex.vo;
public class GuestBookVo {
	private int num; // 숫자
	private String name; // 방문자 이름
	private String pwd; // 비밀번호
 	private String messege;
	private String date;
	
	public GuestBookVo() {		}
	public GuestBookVo(int num, String pwd) {
		this.num = num;
		this.pwd = pwd;
	}
	public GuestBookVo(String name,  String pwd, String messege) {
		this.name = name;
		this.pwd = pwd;
		this.messege = messege;
	}
	public GuestBookVo(int num, String name, String pwd, String date, String messege) {
		this.num = num;
		this.name = name;
		this.pwd = pwd;
		this.date = date;
		this.messege = messege;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	@Override
	public String toString() {
		return "GuestBookVo [num=" + num + ", name=" + name + ", pwd=" + pwd + ", messege=" + messege + ", date=" + date
				+ "]";
	}
	
}

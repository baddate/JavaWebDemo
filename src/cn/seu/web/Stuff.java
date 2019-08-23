package cn.seu.web;

public class Stuff {
	private String account;
	private String name;
	private String email;
	private String phone;
	private String role;
	private String date;
	private String password;
	public Stuff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stuff(String account, String name, String email, String phone, String role, String date, String password) {
		super();
		this.account = account;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.date = date;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String string) {
		this.account = string;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}

package cn.seu.web;

public class Stuff {
	private int id;
	private String account;
	private String name;
	private String email;
	private String phone;
	private String role;
	private String date;
	private String password;
	private String idcard;
	private String privilege;
	
	public Stuff(int id, String account, String name, String email, String phone, String role, String date,
			String password, String idcard, String privilege) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.date = date;
		this.password = password;
		this.idcard = idcard;
		this.privilege = privilege;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Stuff() {
		super();
		// TODO Auto-generated constructor stub
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

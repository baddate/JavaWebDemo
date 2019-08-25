package cn.seu.web;

public class Role {
	private int id;
	private String name;
	private String Privilege;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrivilege() {
		return Privilege;
	}
	public void setPrivilege(String privilege) {
		Privilege = privilege;
	}
}

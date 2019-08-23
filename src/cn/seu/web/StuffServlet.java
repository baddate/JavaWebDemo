package cn.seu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StuffServlet extends HttpServlet{
	
	Connection  conn;
	final String url = "jdbc:mysql://localhost:3306/seuWeb";
	final String username = "root";
	final String password = "123456";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		System.out.println("test doGet");
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		
		java.util.Date date = new java.util.Date();
		String testurl=pareRequestURI(req);
		System.out.println(testurl);
		if(testurl.equals("/admin/admin_add")) {
			System.out.println("ADMIN ADD");
			Stuff temp=new Stuff();
			temp.setName(req.getParameter("names"));
			System.out.println(req.getParameter("names"));
			temp.setAccount(req.getParameter("account"));
			temp.setEmail(req.getParameter("email"));
			temp.setPassword(req.getParameter("password"));
			temp.setPhone(req.getParameter("phone"));
			//temp.setId(Integer.parseInt(req.getParameter("id")));
			temp.setDate(date.toLocaleString());
			//temp.setRole("role");
			temp.setPrivilege("privilege");
			
			String sql="insert into stuff(name,account,password,phone,email,authdate,privilege) values('"+temp.getName()+"','"
			+temp.getAccount()+"','"
			+temp.getPassword()+"','"
			+temp.getPhone()+"','"
			+temp.getEmail()+"','"
			+temp.getDate()+"','"
			+temp.getPrivilege()+
			"')";
			System.out.println("temp sql");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				stmt.execute(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(testurl.equals("/admin/admin_list")) {
			System.out.println("ADMIN LIST");
			ArrayList<Stuff> admin_list=new ArrayList<Stuff>();
			String sql = "select * from stuff";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Stuff stf=new Stuff();
					stf.setName(rs.getString("name"));
					stf.setAccount(rs.getString("account"));
					stf.setEmail(rs.getString("email"));
					stf.setPhone(rs.getString("phone"));
					admin_list.add(stf);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("adminlist", admin_list);
			
			req.getRequestDispatcher("test.jsp").forward(req, resp);
		}
	}
	/**
	 * 解析请求路径，获取到请求的路径，如【http://localhost/MySpringMVC/testServlet】--> 【/testServlet】
	 */
	private String pareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath();
		String requestUri = request.getRequestURI();
		String lasturl = requestUri.replaceFirst(path, "");
		lasturl = lasturl.substring(0, lasturl.lastIndexOf("."));
		return lasturl;
	}
}
/*
CREATE TABLE IF NOT EXISTS admin (
	admin_name VARCHAR(40) NOT NULL,
	admin_account VARCHAR(40) NOT NULL,
	admin_pwd VARCHAR(40) NOT NULL,
	admin_email VARCHAR(40) NOT NULL,
	admin_phone VARCHAR(40)
	);
 
 */

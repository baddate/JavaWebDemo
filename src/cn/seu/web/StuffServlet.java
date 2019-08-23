package cn.seu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StuffServlet extends HttpServlet{
	
	Connection  conn;
	final String url = "jdbc:mysql://localhost:3306/project";
	final String username = "root";
	final String password = "123456";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		System.out.println("test doGet");
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		System.out.println("temp test");
		
		String testurl=pareRequestURI(req);
		Stuff temp=new Stuff();
		
		temp.setName(req.getParameter("admin_name"));
		temp.setAccount(req.getParameter("admin_account"));
		temp.setEmail(req.getParameter("admin_email"));
		temp.setPassword(req.getParameter("admin_pwd"));
		temp.setPhone(req.getParameter("admin_phone"));
		String sql="insert into admin(admin_name,admin_account,admin_pwd,admin_email,admin_phone) values('"+temp.getName()+"','"
		+temp.getAccount()+"','"
		+temp.getPassword()+"','"
		+temp.getEmail()+"','"
		+temp.getPhone()+
		"')";
		System.out.println(req.getParameter("admin_name"));
		
		System.out.println("temp sql");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, username, password);
			System.out.println("test mysql");

            
			Statement stmt =(Statement) conn.createStatement();

			stmt.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("this is a dopost!");

		
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

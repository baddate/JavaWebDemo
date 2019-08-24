package cn.seu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	Connection  conn;
	final String url = "jdbc:mysql://localhost:3306/seuWeb?characterEncoding=utf-8";
	final String username = "root";
	final String password = "123456";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		doPost(req, resp);
		System.out.println("test login");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		resp.setContentType("text/html;charset=utf-8");
	    resp.setCharacterEncoding("UTF-8");
	    
	    String testurl=pareRequestURI(req);
		System.out.println(testurl);
		if(testurl.equals("/login")) {
			System.out.println("LOGIN");
			
			Stuff temp=new Stuff();
			temp.setAccount(req.getParameter("account"));
			temp.setPassword(req.getParameter("password"));
			System.out.println(temp.getAccount()+" "+temp.getPassword());
			String sql="select * from stuff where(account='"+temp.getAccount()+"')";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				//System.out.println(rs.getString("password"));
				while(rs.next()) {
				if(rs.getString("password").equals(temp.getPassword())) {
					resp.sendRedirect("index.html");
				}else {
					resp.sendRedirect("error.html");
				}
				}
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private String pareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath();
		String requestUri = request.getRequestURI();
		String lasturl = requestUri.replaceFirst(path, "");
		lasturl = lasturl.substring(0, lasturl.lastIndexOf("."));
		return lasturl;
	}
}

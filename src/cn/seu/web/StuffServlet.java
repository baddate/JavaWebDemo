package cn.seu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StuffServlet extends HttpServlet{
	String Account="";
	Connection  conn;
	final String url = "jdbc:mysql://localhost:3306/seuWeb?characterEncoding=utf-8";
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
		resp.setContentType("text/html;charset=utf-8");
	    resp.setCharacterEncoding("UTF-8");
	    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
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
			String[] testadmin=new String[4];
			testadmin[0]=req.getParameter("superadmin");
			testadmin[1]=req.getParameter("billadmin");
			testadmin[2]=req.getParameter("feeadmin");
			testadmin[3]=req.getParameter("zadmin");
			StringBuffer str5 = new StringBuffer();
			for (String s : testadmin) {
			    str5.append(s+",");
			}
			temp.setRole(str5.toString());
			//temp.setId(Integer.parseInt(req.getParameter("id")));
			temp.setDate(date.toLocaleString());
			//temp.setRole("role");
			temp.setPrivilege("privilege");
			
			String sql="insert into stuff(name,account,password,phone,email,authdate,role,privilege) values('"+temp.getName()+"','"
			+temp.getAccount()+"','"
			+temp.getPassword()+"','"
			+temp.getPhone()+"','"
			+temp.getEmail()+"','"
			+temp.getDate()+"','"
			+temp.getRole()+"','"
			+temp.getPrivilege()+
			"')";
			System.out.println(temp.getName());
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				stmt.execute(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect("admin_list.test");
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
					stf.setId(rs.getInt("id"));
					stf.setAccount(rs.getString("account"));
					stf.setEmail(rs.getString("email"));
					stf.setPhone(rs.getString("phone"));
					stf.setRole(rs.getString("role"));
					System.out.println(stf.getRole());
					stf.setDate(sdf.format(rs.getDate("authdate")));
					admin_list.add(stf);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			req.setAttribute("adminlist", admin_list);
			
			req.getRequestDispatcher("admin_list.jsp").forward(req, resp);
		}else if(testurl.equals("/admin/admin_modi")) {
			System.out.println("ADMIN MODI");
			Stuff temp=new Stuff();
			//temp.setId(Integer.parseInt(req.getParameter("id")));
			temp.setName(req.getParameter("name"));
			temp.setAccount(req.getParameter("account"));
			temp.setEmail(req.getParameter("email"));
			temp.setPhone(req.getParameter("phone"));
			String[] testadmin=new String[4];
			testadmin[0]=req.getParameter("superadmin");
			testadmin[1]=req.getParameter("billadmin");
			testadmin[2]=req.getParameter("feeadmin");
			testadmin[3]=req.getParameter("zadmin");
			StringBuffer str5 = new StringBuffer();
			for (String s : testadmin) {
			    str5.append(s+",");
			}
			temp.setRole(str5.toString());
			System.out.println(temp.getPhone());
			String sql="update stuff set name='"+temp.getName()+"',account='"
			+temp.getAccount()+"',phone='"+temp.getPhone()+"',email='"+temp.getEmail()
					+"',role='"+temp.getRole()+"' "
					+ "where name='"+temp.getName()+"'";
					
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				stmt.execute(sql);
				System.out.println("update");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect("admin_list.test");
		}else if(testurl.equals("/admin/admin_del")) {
			System.out.println("ADMIN DEL");
			Stuff temp=new Stuff();

			temp.setName(req.getParameter("name"));
			String sql="delete from stuff where name='"+temp.getName()+"'";
			System.out.println(temp.getName());
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				stmt.execute(sql);
				System.out.println("delete");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//req.getRequestDispatcher("admin.test").forward(req, resp);
			resp.sendRedirect("admin_list.test");
		}else if(testurl.equals("/login")) {//login
			System.out.println("LOGIN");
			
			Stuff temp=new Stuff();
			temp.setAccount(req.getParameter("account"));
			temp.setPassword(req.getParameter("password"));
			System.out.println(temp.getAccount()+" "+temp.getPassword());
			String sql="select * from stuff where account='"+temp.getAccount()+"'";
			Account=temp.getAccount();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				//System.out.println(rs.getString("password"));
				while(rs.next()) {
					if(rs.getString("password").equals(temp.getPassword())) {
						System.out.println("===========");
						resp.sendRedirect("index.html");
					}else {
						resp.sendRedirect("error.html");
					}
				}
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(testurl.equals("/user/user_modi_pwd")) {
			System.out.println("MODI PWD");
			
			String olds=req.getParameter("opwd");
			String news=req.getParameter("npwd");
			String nnews=req.getParameter("nnpwd");
			System.out.println(news+"  "+nnews);
			if(news.equals(nnews)) {
				String sql="update stuff set password='"+nnews+"' where account='"+Account+"'";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn=DriverManager.getConnection(url, username, password);
					Statement stmt =(Statement) conn.createStatement();
					stmt.execute(sql);
					System.out.println("pwd modi");
					resp.sendRedirect("../index.html");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("NOT MATCH! 两次新密码必须相同!!");
				resp.sendRedirect("../notmatch.html");
			}
		}else if(testurl.equals("/user/user_info")) {
			System.out.println("USER INFO");
			String sql="select * from  stuff where account='"+Account+"'";
			Stuff stf=new Stuff();
			Stuff temp=new Stuff();
			//temp.setId(Integer.parseInt(req.getParameter("id")));
			temp.setName(req.getParameter("name"));
			temp.setAccount(req.getParameter("account"));
			temp.setEmail(req.getParameter("email"));
			temp.setPhone(req.getParameter("phone"));
			String sqls="update stuff set name='"+temp.getName()+"',phone='"+temp.getPhone()+"',email='"+temp.getEmail()+"' "
							+ "where account='"+temp.getAccount()+"'";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				Statement stmt =(Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					
					stf.setName(rs.getString("name"));
					stf.setId(rs.getInt("id"));
					stf.setAccount(rs.getString("account"));
					stf.setEmail(rs.getString("email"));
					stf.setPhone(rs.getString("phone"));
					stf.setDate(sdf.format(rs.getDate("authdate")));
					stf.setRole(rs.getString("role"));
				}
				stmt.execute(sqls);
				System.out.println("update info");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("user", stf);
			req.getRequestDispatcher("user_info.jsp").forward(req, resp);
		}else if(testurl.equals("/role/role_list")) {
			System.out.println("ROLE LIST");
		}
			
	}
	/**
	 * 
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

package zzwxiangmu;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongruan.jdbcTool.JdbcTool;

public class zzwserv extends HttpServlet {
	public static int jifei;
	public static int shichang;
	private String pareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath();
		String requestUri = request.getRequestURI();
		String lasturl = requestUri.replaceFirst(path, "");
		lasturl = lasturl.substring(0, lasturl.lastIndexOf("."));
		return lasturl;
	}
	private static List convertList(ResultSet rs) throws SQLException{
		List list = new ArrayList();
		ResultSetMetaData md = rs.getMetaData();//��ȡ����
		int columnCount = md.getColumnCount();//��ȡ�е�����
		while (rs.next()) {
			Map rowData = new HashMap();//����Map
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));//��ȡ������ֵ
			}
			list.add(rowData);
		}
		return list;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcTool jdbctool = new JdbcTool();
        int jifei = 0;
        int shichang = 0;
        request.setAttribute("jifei", jifei);
        request.setAttribute("shichang", shichang);
        String suffix = pareRequestURI(request);
        switch(suffix){
        case"/dianxin/fee/fee_list":
        	if(request.getParameter("a").equals("null,null,null,on,")) {
        		response.sendRedirect("../nopower.htm");
        	}else {
        		ResultSet resultset = jdbctool.executeSqlByQuery("select * from zifei;");
                try {
        			List resultlist = convertList(resultset);
        			request.setAttribute("results", resultlist);
        		} catch (SQLException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
                request.getRequestDispatcher("/dianxin/fee/fee_list.jsp").forward(request, response);
        	}
        	
            break;
        case"/dianxin/fee/fee_add":
        	request.getRequestDispatcher("/dianxin/fee/fee_add.jsp").forward(request, response);
        	break;
        case"/dianxin/account/account_add":
        	request.getRequestDispatcher("/dianxin/account/account_add.jsp").forward(request, response);
        	break;
        
        default:
        	break;
        }
	}

}

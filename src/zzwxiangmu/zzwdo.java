package zzwxiangmu;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongruan.jdbcTool.JdbcTool;

public class zzwdo extends HttpServlet {
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
        String suffix = pareRequestURI(request);
        String tem;
        switch(suffix){
        case"/dianxin/fee/startzifei":
        	tem = request.getParameter("zifeiid");
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String time = df.format(new Date());
            jdbctool.executeSql("update zifei set zhaungtai = \"��ͨ\", kaitongshijian = \""+time+"\" ,qidongshijian = \""+time+"\" where zifeiid ="+tem+" ;");
            response.sendRedirect("/NewWeb/dianxin/fee/fee_list.html");
            break;
        case"/dianxin/fee/deletezifei":
        	tem = request.getParameter("zifeiid");
            jdbctool.executeSql("delete from zifei where zifeiid ="+tem+" ;");
            response.sendRedirect("/NewWeb/dianxin/fee/fee_list.html");
            break;
        case"/dianxin/fee/sortasczifei":
        	tem = request.getParameter("type");
        	if(tem.equals("ʱ��")){
        		ResultSet resultset = jdbctool.executeSqlByQuery("select * from zifei order by jibenshichang asc;");
        		try {
        			List resultlist = convertList(resultset);
        			request.setAttribute("results", resultlist);
        		} catch (SQLException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		int jifei = 0;
                int shichang = 1;
                request.setAttribute("jifei", jifei);
                request.setAttribute("shichang", shichang);
        	}
        	else if(tem.equals("����")){
        		ResultSet resultset = jdbctool.executeSqlByQuery("select * from zifei order by jibenfeiyong asc;");
        		try {
        			List resultlist = convertList(resultset);
        			request.setAttribute("results", resultlist);
        		} catch (SQLException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		int jifei = 1;
                int shichang = 0;
                request.setAttribute("jifei", jifei);
                request.setAttribute("shichang", shichang);
        	}
        	else break;
        	request.getRequestDispatcher("http://localhost:8080/NewWeb/dianxin/fee/fee_list.jsp").forward(request, response);
        	break;
        case"/dianxin/fee/sortdesczifei":
        	tem = request.getParameter("type");
        	if(tem.equals("ʱ��")){
        		ResultSet resultset = jdbctool.executeSqlByQuery("select * from zifei order by jibenshichang desc;");
        		try {
        			List resultlist = convertList(resultset);
        			request.setAttribute("results", resultlist);
        		} catch (SQLException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		int jifei = 0;
                int shichang = 0;
                request.setAttribute("jifei", jifei);
                request.setAttribute("shichang", shichang);
        	}
        	else if(tem.equals("����")){
        		ResultSet resultset = jdbctool.executeSqlByQuery("select * from zifei order by jibenfeiyong desc;");
        		try {
        			List resultlist = convertList(resultset);
        			request.setAttribute("results", resultlist);
        		} catch (SQLException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		int jifei = 0;
                int shichang = 0;
                request.setAttribute("jifei", jifei);
                request.setAttribute("shichang", shichang);
        	}
        	else break;
        	request.getRequestDispatcher("/dianxin/fee/fee_list.jsp").forward(request, response);
        	break;
        case"/dianxin/fee/fee_detail":
        	tem = request.getParameter("zifeiid");
        	ResultSet resultset = jdbctool.executeSqlByQuery("select * from zifei where zifeiid ="+tem+" ;");
        	try {
    			List resultlist = convertList(resultset);
    			request.setAttribute("result", resultlist.get(0));
    		} catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	request.getRequestDispatcher("/dianxin/fee/fee_detail.jsp").forward(request, response);
            break;
        case"/dianxin/fee/fee_modi":
        	tem = request.getParameter("zifeiid");
        	resultset = jdbctool.executeSqlByQuery("select * from zifei where zifeiid ="+tem+" ;");
        	try {
    			List resultlist = convertList(resultset);
    			request.setAttribute("result", resultlist.get(0));
    		} catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	request.getRequestDispatcher("/dianxin/fee/fee_modi.jsp").forward(request, response);
            break;
        default:
        	break;
        }
        
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcTool jdbctool = new JdbcTool();
        String suffix = pareRequestURI(request);
        switch(suffix){
        case"/dianxin/fee/fee_add":
        	String name = request.getParameter("name");
        	String radFeeType = request.getParameter("radFeeType");
        	String shichang = request.getParameter("shichang").equals("")?"0":request.getParameter("shichang");
        	String jifei = request.getParameter("jifei").equals("")?"0":request.getParameter("jifei");
        	String danfei = request.getParameter("danfei").equals("")?"0":request.getParameter("danfei");
        	String shuoming = request.getParameter("shuoming");
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String time = df.format(new Date());
            jdbctool.executeSql("insert into zifei values (0, \""+name+"\", "+shichang+", "+jifei+", "+danfei+", \""+time+"\", \"20000101000000\", \"��ͣ\", \""+shuoming+"\", \""+radFeeType+"\", \"20000101000000\");");
            response.sendRedirect("/NewWeb/dianxin/fee/fee_list.html?a=on,on,on,on,");
            break;
        case"/dianxin/fee/fee_modify":
        	String id = request.getParameter("id");
        	name = request.getParameter("name");
        	radFeeType = request.getParameter("radFeeType");
        	shichang = request.getParameter("shichang").equals("")?"0":request.getParameter("shichang");
        	jifei = request.getParameter("jifei").equals("")?"0":request.getParameter("jifei");
        	danfei = request.getParameter("danfei").equals("")?"0":request.getParameter("danfei");
        	shuoming = request.getParameter("shuoming");
            jdbctool.executeSql("update zifei set zifeimingchen = \""+name+"\", zifeileixing = \""+radFeeType+"\", jibenshichang = "+shichang+", jibenfeiyong = "+jifei+", danweifeiyong = "+danfei+", zifeishuoming = \""+shuoming+"\" where zifeiid = "+id+" ;");
            response.sendRedirect("/NewWeb/dianxin/fee/fee_list.html");
            break;
        default:
        	break;
        }
            	
	}

}

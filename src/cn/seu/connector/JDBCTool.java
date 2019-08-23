package cn.seu.connector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTool {
	/**
	 * ǰ������ Ҫ����Ŀ�м���mysql-connector  jar
	 * ʹ��jdbc���裺
	 * 1.��������
	 * 2.��ȡ����
	 * 3.��ȡ��������
	 * 4.����sql
	 */
		private  static   Connection  conn;
		private  static final String url = "jdbc:mysql://localhost:3306/project";
		private  static final String username = "root";
		private  static final String password = "123456";
		static{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 ִ��sql ��ɾ�� ��������ѯ
		 */
		public  static  void   executeSql(String   sql){
			try {
				Statement  st=(Statement) conn.createStatement();
				st.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		/**
		 ִ��sql ��ѯ ���ؽ��Ϊ ResultSet �����
		 */
		public static  ResultSet executeSqlByQuery(String  querysql){
			ResultSet rs=null;
			try {
				Statement  st=(Statement) conn.createStatement();
				rs = (ResultSet) st.executeQuery(querysql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return  rs;
		}  
}

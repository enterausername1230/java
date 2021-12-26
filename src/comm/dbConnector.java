package comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnector {
	//Ŀ�Կ� ����
	Connection conn; // java.sql.Connection
	Statement stmt = null;

	public dbConnector() {

		// �����ڰ� ����Ǹ� DB�� �ڵ� ����Ǿ� Connection ��ü ����
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://jdb.deu.monster:60001/java03_team03", "java03_team03",
					"980601eh!");
			System.out.println("DB ���� �Ϸ�");
			stmt = conn.createStatement();

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
		}
	}

	public ResultSet executeQurey(String sql) {
		// SQL�� �����ϱ� ���� �޼ҵ� - Parameter : String��ü�� ���� SQL��
		// �������� ResultSet���� ��ȯ

		ResultSet src = null;
		try {
			src = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("SQL ���� ����");
			return null;
		}
		return src;
	}

	public Connection getConnection() {
		// PreparedStatement�̿��� SQL �ۼ��� ��� Connection ��ü�� �ʿ��� ���� �޼ҵ�

		if (conn != null) {
			return conn;
		} else {
			return null;
		}

	}
	
	public int executeUpdate(String str) {
		// TODO Auto-generated method stub
		int src;
		try {
			src = stmt.executeUpdate(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("SQL ���� ����");
			return 0;
		}
		
		return src;
	}

}

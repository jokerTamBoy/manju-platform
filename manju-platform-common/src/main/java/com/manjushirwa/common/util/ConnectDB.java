package com.manjushirwa.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 功能描述：连接数据库-mysql/oracle
 * 
 * @author :FangHewei
 * @Date :Jul 18, 2008
 * @Time :3:28:59 PM
 * @version :1.0
 */
public class ConnectDB {

	private static final String MYSQL = "jdbc:mysql://";

	private static final String ORACLE = "jdbc:oracle:thin:@";

	private static final String SQLSERVER = "jdbc:microsoft:sqlserver://";
	private ConnectDB() {
	}

	public static Connection getConnection(String DBType, String url,
			String user, String password) throws SQLException {
		if ("mysql".equalsIgnoreCase(DBType))
			return getMySqlConn(url, user, password);
		if ("oracle".equalsIgnoreCase(DBType))
			return getOracleConn(url, user, password);
		if ("sqlserver".equals(DBType)){
			return getSqlServerConn(url, user, password);
		}
		return null;
	}

	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static Connection getMySqlConn(String url, String user,
			String password) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(MYSQL + url, "root", "root");

		return conn;
	}

	private static Connection getOracleConn(String url, String user,
			String password) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(ORACLE + url, "scott", "tiger");

		return conn;
	}
	
	private static Connection getSqlServerConn(String url, String user,
			String password) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");// 加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(SQLSERVER + url, "root", "root");

		return conn;
	}
}

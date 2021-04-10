package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private final static String URL = "jdbc:mysql://127.0.0.1:3306/happyhouse?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String ID = "ssafy";
	private final static String PASSWORD = "ssafy";
	
	// 싱글톤으로 해도 되고 static block을 사용해도 된다. 최초에 load되는 순간 한번만 수행
	// 드라이버는 한번만 가져오면 되니까
	static {
		try {
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnect() throws SQLException {
		return DriverManager.getConnection(URL, ID, PASSWORD);
	}
	
	// 데이터의 타입이 같은 경우 ...을 쓰면 자동으로 배열로 바뀐다
	public static void close(AutoCloseable... closeables) {
		try {
			for(AutoCloseable ac : closeables) {
				if(ac != null)
					ac.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

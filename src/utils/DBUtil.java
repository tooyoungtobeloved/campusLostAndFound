package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBUtil {
	public static DruidDataSource dataSource = null;
	static Properties prop = new Properties();
	static {
		InputStream ipt = DBUtil.class.getClassLoader().getResourceAsStream("/utils/druid.properties");
		try {
			prop.load(ipt);
	        dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PreparedStatement createPreparedStatement(String sql,Object[] obj) {
		PreparedStatement pstmt = null;
		try {
			pstmt = getConnection().prepareStatement(sql);
			if(obj!=null) {
				for(int i = 0;i<obj.length;i++) {
					pstmt.setObject(i+1, obj[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public static void closeAll(ResultSet rst,Statement stmt,Connection conn) {
		DbUtils.closeQuietly(conn, stmt, rst);
	}
	
	
	public static ResultSet executeQuery(String sql,Object[] obj) {
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = createPreparedStatement(sql, obj);
			rst= pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	public static int getTotalCount(String sql,Object[] obj) {
		ResultSet rst = null;
		int count = -1;
		try {
			//pstmt = createPreparedStatement(sql, obj);
			rst = executeQuery(sql, obj);
			if(rst.next()) {
				count = rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rst, null, DBUtil.getConnection());
		}
		return count;
	}
	
}

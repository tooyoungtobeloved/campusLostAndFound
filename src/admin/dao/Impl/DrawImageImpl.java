package admin.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.dbutils.QueryRunner;

import admin.dao.IDrawImageDao;
import utils.DBUtil;

public class DrawImageImpl implements IDrawImageDao{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public HashMap<String, Object> drawcolumn() {
		Connection conn = DBUtil.getConnection();
		ArrayList<Object> ar1 = new ArrayList<Object>();
		ArrayList<Object> ar2 = new ArrayList<Object>();
		HashMap<String, Object> res = new HashMap<String, Object>();
		String sql = "SELECT ePlace,count(*) as count from event GROUP BY ePlace desc LIMIT 5";
		ResultSet rst = null;
		PreparedStatement pstt = null;
		try {
			pstt = conn.prepareStatement(sql);
			rst = pstt.executeQuery();
			while(rst.next()) {
				ar1.add(rst.getString(1));
				ar2.add(rst.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rst, pstt, conn);
		}
		res.put("place", ar1);
		res.put("total", ar2);
		return res;
	}

	@Override
	public HashMap<String, Object> drawLine() {
		Connection conn = DBUtil.getConnection();
		ArrayList<Object> ar1 = new ArrayList<Object>();
		ArrayList<Object> ar2 = new ArrayList<Object>();
		HashMap<String, Object> res = new HashMap<String, Object>();
		String sql = "SELECT COunt(*) as  total, DATE_FORMAT(registerTime,'%m-%d') as day FROM userinfo GROUP BY day";
		ResultSet rst = null;
		PreparedStatement pstt = null;
		try {
			pstt = conn.prepareStatement(sql);
			rst = pstt.executeQuery();
			while(rst.next()) {
				ar1.add(rst.getString(1));
				ar2.add(rst.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rst, pstt, conn);
		}
		res.put("total", ar1);
		res.put("day", ar2);
		return res;
	}

	@Override
	public int countUser() {
		String sql = "select count(stuId) as totaluser from user";
		return DBUtil.getTotalCount(sql, null);
	}

	@Override
	public int countEvent() {
		String sql = "select count(eId) as eventTotal from event";
		return DBUtil.getTotalCount(sql, null);
	}

}

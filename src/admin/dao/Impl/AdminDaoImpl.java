package admin.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import admin.dao.IAdminDao;
import admin.entity.Admin;
import admin.entity.User;
import utils.DBUtil;

public class AdminDaoImpl implements IAdminDao {
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	@Override
	public int login(Admin admin) {
		String sql = null;
		Admin u = null;
		try {
			sql = "select * from admin where admid = ?";
			u = qr.query(sql, new BeanHandler<Admin>(Admin.class), admin.getAdmid());
			if(u == null) {
				return -1;
			}
			else {
				sql = "select * from admin where admid = ? and admpwd = ?";
				u = qr.query(sql, new BeanHandler<Admin>(Admin.class),admin.getAdmid(),admin.getAdmpwd());
//				System.out.println(u);
				if(u == null){
					return 0;
				}
				else {
					return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<User> queryAllUserinfo(int limit, int page) {
		List<User> allUser = new ArrayList<User>();
		String sql = "select * from userinfo limit ?,?";
		int pages = (page - 1) * limit;
		try {
			allUser = qr.query(sql, new BeanListHandler<User>(User.class),pages,limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUser;
	}
	@Override
	public int countTotal() {
		String sql = "select count(stuId) from userinfo";
		return DBUtil.getTotalCount(sql, null);
	}

	@Override
	public int primaryLogin(String stuId,String pwd) {
		String sql = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstt = null;
		ResultSet rst = null;
		try {
			sql = "select * from user where stuId = ? and uPwd = ? and isAdmin = 1";
			pstt = conn.prepareStatement(sql);
			pstt.setString(1, stuId);
			pstt.setString(2, pwd);
			rst = pstt.executeQuery();
			if (rst.next()) {
				return 1;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rst, pstt, conn);
		}
		return -1;
	}

	
}

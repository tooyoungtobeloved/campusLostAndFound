package admin.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import admin.dao.IRegimeUserDao;
import admin.entity.User;
import utils.DBUtil;

public class RegimeUserDaoImpl implements IRegimeUserDao{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public int deleteUser(String stuId) {
		int status = 0;
		String sql = "delete from user where stuId = ?";
		try {
			status = qr.update(sql,stuId);
			System.out.println(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<User> searchUser(String id) {
		List<User> user = null;
		String sql = "select * from userinfo WHERE  stuId LIKE concat('%',?,'%') ";
		try {
			user = qr.query( sql, new BeanListHandler<User>(User.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int countUser(String id) {
		String sql = "select COUNT(stuId) from userinfo WHERE stuId LIKE concat('%',?,'%')";
		Object[] obj = {id};
		return DBUtil.getTotalCount(sql, obj);
	}

}

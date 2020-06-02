package admin.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import admin.dao.ISetADMINDao;
import admin.entity.ADMINS;
import utils.DBUtil;

public class SetAdminDaoImpl implements ISetADMINDao{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public List<ADMINS> queryA(int limit, int page) {
		List<ADMINS> admins = null;
		String sql = "SELECT user.* ,userinfo.uName,userinfo.uDesc FROM user,userinfo WHERE user.stuId = userinfo.stuId ORDER BY isAdmin desc limit ?,?";
		int pages = (page - 1) * limit;
		try {
			admins = qr.query(sql, new BeanListHandler<ADMINS>(ADMINS.class),pages,limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins;
	}

	@Override
	public int countADMIN() {
		String sql = "select count(stuId) from user";
		return DBUtil.getTotalCount(sql, null);
	}

	@Override
	public int updateStatus(String stuId, int status) {
		int s = 0;
		String sql = "UPDATE user set isAdmin = ? WHERE stuId = ?";
		try {
			s = qr.update(sql, status,stuId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

}

package admin.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import admin.dao.ISuggestDAO;
import admin.entity.Suggest;
import utils.DBUtil;

public class SuggestDaoImpl implements ISuggestDAO{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public List<Suggest> querySuggest(int limit, int page) {
		String sql = "select * from suggest limit ?,?";
		int pages = (page - 1) * limit;
		List<Suggest> sug = null;
		try {
			sug = qr.query(sql, new BeanListHandler<Suggest>(Suggest.class),pages, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sug;
	}

	@Override
	public int countTotal() {
		String sql = "select count(sugID) from suggest";
		return DBUtil.getTotalCount(sql, null);
	}

	@Override
	public int delSug(int sugID) {
		int status = 0;
		String sql = "delete from suggest where sugID = ?";
		try {
			status = qr.update(sql, sugID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

}

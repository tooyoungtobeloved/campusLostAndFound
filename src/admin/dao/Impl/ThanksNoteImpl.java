package admin.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import admin.dao.IThanksNote;
import admin.entity.ThanksNote;
import utils.DBUtil;

public class ThanksNoteImpl implements IThanksNote{

	@Override
	public List<ThanksNote> queryThanks(int limit, int page) {
		Connection conn = DBUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		List<ThanksNote> tksList = null;
		int pages = (page - 1) * limit;
		String sql = "select * from thanknote limit ?,?";
		try {
			tksList = qr.query(conn, sql, new BeanListHandler<ThanksNote>(ThanksNote.class), pages, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(null, null, conn);
		}
		return tksList;
	}

	@Override
	public int deleteWithID(int tId) {
		Connection conn = DBUtil.getConnection();
		String sql = "delete from thanknote where tId = ?";
		int status = 0;
		QueryRunner qr = new QueryRunner();
		try {
			status = qr.update(conn, sql, tId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(null, null, conn);
		}
		System.out.println("status" + status);
		return status;
	}

	@Override
	public int countTotal() {
		String sql = "select count(tId) from thanknote";
		return DBUtil.getTotalCount(sql, null);
	}

}

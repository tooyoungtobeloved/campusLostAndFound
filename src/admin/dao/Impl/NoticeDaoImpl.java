package admin.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import admin.dao.INoticeDao;
import admin.entity.Notice;
import utils.DBUtil;

public class NoticeDaoImpl implements INoticeDao{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public int insertNotice(Notice N) {
		String sql = "insert into note values(?,?,?,?,?)";
		Object[] para = {N.getNoteId(),N.getNoteTitle(),N.getNoteContent(),N.getNotePubDate(),N.getIsTop()};
		int status = 0;
		System.out.println(N.getIsTop());
		if(N.getIsTop() == 0) {
			try {
				status = qr.update(sql,para);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			updateTop();
			try {
				status = qr.update(sql, para);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	
	private boolean updateTop() {
		String sql = "update note set isTop = 0";
		int status = 0;
		try {
			status = qr.update(sql);
			if(status != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Notice> showNotice(int limit, int page) {
		List<Notice> notices = null;
		String sql = "select * from note order by isTop desc limit ?,?";
		int pages = (page - 1) * limit;
		try {
			notices = qr.query(sql, new BeanListHandler<Notice>(Notice.class),pages, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notices;
	}

	@Override
	public int countTotal() {
		String sql = "select count(noteId) from note";
		return DBUtil.getTotalCount(sql, null);
	}

	@Override
	public int delete(int noteId) {
		String sql = "delete from note where noteId = ?";
		int status = 0;
		try {
			status = qr.update(sql, noteId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int setTop(int noteId) {
		updateTop();
		int status = 0;
		String sql = "update note set isTop = 1 where noteId = ?";
		try {
			status = qr.update(sql, noteId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
}

package user.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import user.dao.IAdminInfoDao;
import user.entity.ContactInfo;
import user.entity.Note;
import utils.DBUtil;

public class AdminedDaoImpl implements IAdminInfoDao{
	
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	@Override
	public List<Note> showNote() {
		List<Note> notes = new ArrayList<Note>();
		String sql = "select * from note order by isTop desc,notePubDate desc;";
		try {
			notes = qr.query(sql, new BeanListHandler<Note>(Note.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notes;
	}
	@Override
	public Note noteDetail(int noteId) {
		Note note = null;
		String sql = "select * from note where noteId=?";
		try {
			note = qr.query(sql,new BeanHandler<Note>(Note.class), noteId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return note;
	}
	
	@Override
	public List<ContactInfo> contactUs() {
		List<ContactInfo> services = new ArrayList<ContactInfo>();
		String sql = "select service,sQQ from services limit 0,5";
		try {
			services = qr.query(sql, new BeanListHandler<ContactInfo>(ContactInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return services;
	}
}

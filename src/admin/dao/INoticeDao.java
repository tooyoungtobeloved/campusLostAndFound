package admin.dao;

import java.util.List;

import admin.entity.Notice;

public interface INoticeDao {
	public int insertNotice(Notice N);
	public List<Notice> showNotice(int limit, int page);
	public int countTotal();
	
	public int delete(int noteId);
	
	public int setTop(int noteId);
	
}

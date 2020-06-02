package admin.Iservice;

import java.util.List;

import admin.entity.Notice;
/*
 * Notice service
 */
public interface INoticeService{
	public int insertNotice(Notice N);
	public List<Notice> showNotice(int page, int limit);
	
	public int countTotal();
	
	public int delete(int noteId);
	
	public int setTop(int noteId);
}

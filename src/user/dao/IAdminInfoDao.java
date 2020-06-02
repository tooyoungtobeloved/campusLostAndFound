package user.dao;

import java.util.List;

import user.entity.ContactInfo;
import user.entity.Note;

public interface IAdminInfoDao {
	//主页的公告
	List<Note> showNote();
	
	//主页的公告详细信息
	Note noteDetail(int noteId);
	
	//主页的联系我们
	List<ContactInfo> contactUs();
	
}

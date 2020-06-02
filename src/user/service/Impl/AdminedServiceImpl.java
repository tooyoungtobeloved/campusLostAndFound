package user.service.Impl;

import java.util.List;

import user.dao.IAdminInfoDao;
import user.dao.Impl.AdminedDaoImpl;
import user.entity.ContactInfo;
import user.entity.Note;
import user.service.IAdminedService;

public class AdminedServiceImpl implements IAdminedService{
	IAdminInfoDao dao = new AdminedDaoImpl();
	
	@Override
	public List<Note> showNote() {
		return dao.showNote();
	}
	
	@Override
	public Note noteDetail(int noteId) {
		return dao.noteDetail(noteId);
	}
	
	@Override
	public List<ContactInfo> contactUs() {
		return dao.contactUs();
	}
	
}

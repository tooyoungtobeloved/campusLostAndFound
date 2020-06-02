package user.service;

import java.util.List;

import user.entity.ContactInfo;
import user.entity.Note;

public interface IAdminedService {
	
	List<Note> showNote();
	
	Note noteDetail(int noteId);
	
	List<ContactInfo> contactUs();
}

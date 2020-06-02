package admin.Iservice;

import java.util.List;

import admin.entity.ThanksNote;

public interface IThanksNoteService {
	public List<ThanksNote> queryThanksNote(int limit, int page);
	public int deleteWithtID(int tId);
	public int countTotal();
}

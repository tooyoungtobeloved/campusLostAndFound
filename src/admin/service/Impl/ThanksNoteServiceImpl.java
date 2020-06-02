package admin.service.Impl;

import java.util.List;

import admin.Iservice.IThanksNoteService;
import admin.dao.Impl.ThanksNoteImpl;
import admin.entity.ThanksNote;

public class ThanksNoteServiceImpl implements IThanksNoteService{
	
	ThanksNoteImpl tni = new ThanksNoteImpl();
	
	@Override
	public List<ThanksNote> queryThanksNote(int limit, int page) {
		return tni.queryThanks(limit, page);
	}

	@Override
	public int deleteWithtID(int tId) {
		return tni.deleteWithID(tId);
	}

	@Override
	public int countTotal() {
		return tni.countTotal();
	}

}

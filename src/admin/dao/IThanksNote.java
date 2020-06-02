package admin.dao;

import java.util.List;

import admin.entity.ThanksNote;

public interface IThanksNote {
	public List<ThanksNote> queryThanks(int limit,int page);
	public int deleteWithID(int tId);
	public int countTotal();
}

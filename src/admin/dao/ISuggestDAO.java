package admin.dao;

import java.util.List;

import admin.entity.Suggest;

public interface ISuggestDAO {
	public List<Suggest> querySuggest(int limit, int page);
	public int countTotal();
	
	public int delSug(int sugID);

}

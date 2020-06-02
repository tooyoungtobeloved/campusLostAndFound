package admin.service.Impl;

import java.util.List;

import admin.Iservice.ISuggestService;
import admin.dao.Impl.SuggestDaoImpl;
import admin.entity.Suggest;

public class SuggestServiceImpl implements ISuggestService{
	private SuggestDaoImpl ISDI = new SuggestDaoImpl();
	@Override
	public List<Suggest> querySuggest(int limit, int page) {
		return ISDI.querySuggest(limit, page);
	}
	@Override
	public int countTotal() {
		return ISDI.countTotal();
	}
	@Override
	public int delSug(int sugID) {
		return ISDI.delSug(sugID);
	}
}

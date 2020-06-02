package user.service.Impl;

import user.dao.ISuggestDao;
import user.dao.Impl.SuggestDaoImpl;
import user.service.ISuggestService;

public class SuggestServiceImpl implements ISuggestService{
	private ISuggestDao ISDI = new SuggestDaoImpl();
	@Override
	public int insertSuggest(String type, String name, String contact, String info) {
		return ISDI.insertSuggest(type, name, contact, info);
	}

}

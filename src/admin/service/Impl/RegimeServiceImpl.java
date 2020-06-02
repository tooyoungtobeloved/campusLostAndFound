package admin.service.Impl;

import java.util.List;

import admin.Iservice.IRegimeUserService;
import admin.dao.Impl.RegimeUserDaoImpl;
import admin.entity.User;

public class RegimeServiceImpl implements IRegimeUserService{

	private RegimeUserDaoImpl rsi = new RegimeUserDaoImpl();
	
	@Override
	public int deleleUser(String stuId) {
		return rsi.deleteUser(stuId);
	}
	@Override
	public List<User> searchUser(String id) {
		return rsi.searchUser(id);
	}
	@Override
	public int countsearch(String id) {
		return rsi.countUser(id);
	}


}

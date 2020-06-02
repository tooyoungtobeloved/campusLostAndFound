package admin.service.Impl;

import java.util.List;

import admin.Iservice.IAdminService;
import admin.dao.Impl.AdminDaoImpl;
import admin.entity.Admin;
import admin.entity.User;

public class AdminServiceImpl implements IAdminService{
	private AdminDaoImpl ai = new AdminDaoImpl();
	@Override
	public int adminlogin(Admin a) {
		return ai.login(a);
	}
	@Override
	public List<User> queryAllUser(int limit, int page) {
		return ai.queryAllUserinfo(limit, page);
	}
	@Override
	public int countTotal() {
		return ai.countTotal();
	}
	@Override
	public int prmariyAdminLogin(String stuId, String pwd) {
		return ai.primaryLogin(stuId, pwd);
	}
	
}

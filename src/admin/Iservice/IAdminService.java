package admin.Iservice;

import java.util.List;

import admin.entity.Admin;
import admin.entity.User;

public interface IAdminService {
	public int adminlogin(Admin a);
	public List<User> queryAllUser(int limit, int page);
	public int countTotal();
	
	public int prmariyAdminLogin(String stuId, String pwd);
}

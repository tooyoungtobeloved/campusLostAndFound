package admin.Iservice;

import java.util.List;

import admin.entity.User;

public interface IRegimeUserService {
	public int deleleUser(String stuId);
	public List<User> searchUser(String id);
	public int countsearch(String id);
}

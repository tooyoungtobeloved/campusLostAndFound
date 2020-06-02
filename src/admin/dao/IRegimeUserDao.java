package admin.dao;

import java.util.List;

import admin.entity.User;

public interface IRegimeUserDao {
	/*
	 * delete user
	 */
	public int deleteUser(String stuId);
	
	/*
	 * 搜索用户
	 */
	public List<User> searchUser(String id);
	
	public int countUser(String id);
	

}

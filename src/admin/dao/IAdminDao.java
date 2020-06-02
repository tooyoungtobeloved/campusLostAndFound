package admin.dao;

import java.util.List;

import admin.entity.Admin;
import admin.entity.User;

public interface IAdminDao {
	//超级管理员登录
	public int login(Admin admin);
	public List<User> queryAllUserinfo(int limit, int page);
	public int countTotal();
	/*
	 * 普通管理员登录
	 */
	public int primaryLogin(String stuId, String uPwd);
	
	
}


package admin.dao;

import java.util.List;



import admin.entity.ADMINS;

public interface ISetADMINDao {
	public List<ADMINS> queryA(int limit, int page);
	public int countADMIN();
	/*
	 * 改状态
	 */
	public int updateStatus(String stuId, int status);
	
}

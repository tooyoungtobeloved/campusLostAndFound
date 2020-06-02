package admin.Iservice;

import java.util.List;

import admin.entity.ADMINS;

public interface ISetADMINService {
	public List<ADMINS> queryAD(int limit, int page);
	public int countADMIN();
	
	/*
	 * 设置是否管理员
	 */
	public int updateStatus(String stuId, int status);
}

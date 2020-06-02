package admin.service.Impl;

import java.util.List;

import admin.Iservice.ISetADMINService;
import admin.dao.Impl.SetAdminDaoImpl;
import admin.entity.ADMINS;

public class SetADMINServiceImpl implements ISetADMINService{

	private SetAdminDaoImpl SAI = new SetAdminDaoImpl();
	
	@Override
	public List<ADMINS> queryAD(int limit, int page) {
		return SAI.queryA(limit, page);
	}

	@Override
	public int countADMIN() {
		return SAI.countADMIN();
	}

	@Override
	public int updateStatus(String stuId, int status) {
		return SAI.updateStatus(stuId, status);
	}

	
}

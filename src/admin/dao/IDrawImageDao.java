package admin.dao;

import java.util.HashMap;

public interface IDrawImageDao {
	public HashMap<String,Object> drawcolumn();
	
	public HashMap<String,Object> drawLine();
	
	/**
	 * 这是后加的统计方法
	 */
	public int countUser();
	
	public int countEvent();
}

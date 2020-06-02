package admin.Iservice;

import java.util.HashMap;

public interface IDrawImageService {
	public HashMap<String, Object> drawImage();
	public HashMap<String, Object> drawLine();
	
	/**
	 * 主页统计
	 */
	public HashMap<String, Integer> countInfo();

}

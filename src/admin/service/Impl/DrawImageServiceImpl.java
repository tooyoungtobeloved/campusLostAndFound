package admin.service.Impl;

import java.util.HashMap;

import admin.Iservice.IDrawImageService;
import admin.dao.Impl.DrawImageImpl;

public class DrawImageServiceImpl implements IDrawImageService{
	private DrawImageImpl DII = new DrawImageImpl();
	@Override
	public HashMap<String, Object> drawImage() {
		return DII.drawcolumn();
	}
	@Override
	public HashMap<String, Object> drawLine() {
		return DII.drawLine();
	}
	@Override
	public HashMap<String, Integer> countInfo() {
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		res.put("userTotal", DII.countUser());
		res.put("eventTotal", DII.countEvent());
		return res;
	}

}

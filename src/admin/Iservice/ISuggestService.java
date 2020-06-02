package admin.Iservice;

import java.util.List;

import admin.entity.Suggest;

public interface ISuggestService {
	public List<Suggest> querySuggest(int limit, int page);
	public int countTotal();
//	删除建议
	public int delSug(int sugID);

}

package admin.service.Impl;

import java.util.List;

import admin.Iservice.INoticeService;
import admin.dao.Impl.NoticeDaoImpl;
import admin.entity.Notice;

public class NoticeServiceImpl implements INoticeService{

	private NoticeDaoImpl ndi = new NoticeDaoImpl();
	@Override
	public int insertNotice(Notice N) {
		return ndi.insertNotice(N);
	}
	@Override
	public List<Notice> showNotice(int limit, int page) {
		return ndi.showNotice(limit, page);
	}
	@Override
	public int countTotal() {
		return ndi.countTotal();
	}
	@Override
	public int delete(int noteId) {
		return ndi.delete(noteId);
	}
	@Override
	public int setTop(int noteId) {
		return ndi.setTop(noteId);
	}

}

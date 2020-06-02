package user.service.Impl;

import java.util.List;

import user.dao.Impl.UserDaoImplMy;
import user.entity.Event;
import user.entity.MarkDetail;
import user.entity.ThanksMail;
import user.entity.UserInfo;
import user.service.IUserService;

public class UserServeImpl implements IUserService{
	
	UserDaoImplMy userDao = new UserDaoImplMy();
	
	@Override
	public boolean register(UserInfo u) {
		return userDao.register(u);
	}

	@Override
	public boolean login(UserInfo u) {
		return userDao.login(u);
	}

	@Override
	public UserInfo selfInfo(String stuId) {
		return userDao.selfInfo(stuId);
	}

	@Override
	public boolean pubInfo(Event e,String stuId) {
		return userDao.pubInfo(e,stuId);
		
	}


	@Override
	public List<Event> tableShow(String index,String eKinds,String eType,int currentPage,int pageSize){
		return userDao.tableShow(index,eKinds,eType, currentPage, pageSize);
	}

	@Override
	public int getTotalCount(String index,String eKinds,String eType) {
		return userDao.getTotalCount(index,eKinds,eType);
	}

	@Override
	public List<Event> showCarousel() {
		return userDao.showCarousel();
	}

	@Override
	public List<Event> tipsShow(String type) {
		return userDao.tipsShow(type);
	}

	@Override
	public List<ThanksMail> thanks() {
		return userDao.thanks();
	}

	@Override
	public ThanksMail thanksDetail(int tId) {
		return userDao.thanksDetail(tId);
	}

	@Override
	public Event eventDetail(int eId) {
		return userDao.eventDetail(eId);
	}

	@Override
	public List<MarkDetail> getMarks(int eId,int pNo,int size) {
		return userDao.getMarks(eId,pNo,size);
	}

	@Override
	public List<Event> getCards(String eKinds) {
		return userDao.getCards(eKinds);
	}

	@Override
	public boolean sendComment(String stuId, int eId, String markDetail) {
		return userDao.sendComment(stuId, eId, markDetail);
	}

	@Override
	public int getTotalCount(int eId) {
		return userDao.getTotalCount(eId);
	}

	@Override
	public UserInfo getMe(String stuId) {
		if(userDao.stuIsExit(stuId)==true) {
			return userDao.getMe(stuId);
		}return null;
	}

	@Override
	public int modifypwd(UserInfo user, String newPwd) {
		if(userDao.login(user)==true) {
			return userDao.modifypwd(user, newPwd);
		}
		return -1;
	}

	@Override
	public int modifyuInfo(UserInfo user) {
		return userDao.modifyuInfo(user);
	}

	@Override
	public List<Event> showMyEvents(String stuId,int currentPage,int pageSize) {
		return userDao.showMyEvents(stuId, currentPage, pageSize);
	}

	@Override
	public int getTotalCount(String stuId) {
		return userDao.getTotalCount(stuId);
	}

	@Override
	public boolean modifyState(int eId, int operate) {
		return userDao.modifyState(eId, operate);
	}

	@Override
	public boolean uploadHead(UserInfo u) {
		return userDao.uploadHead(u);
	}

	@Override
	public boolean stuIsExit(String stuId) {
		return userDao.stuIsExit(stuId);
	}

	@Override
	public boolean checkuName(String uName) {
		return userDao.checkuName(uName);
	}

	@Override
	public boolean checkOldPwd(UserInfo user) {
		return userDao.checkOldPwd(user);
	}

	@Override
	public boolean pubMails(ThanksMail mail) {
		return userDao.pubMails(mail);
	}


}

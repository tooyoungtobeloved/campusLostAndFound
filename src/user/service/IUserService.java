package user.service;

import java.util.List;

import user.entity.Event;
import user.entity.MarkDetail;
import user.entity.ThanksMail;
import user.entity.UserInfo;

public interface IUserService {
	
	//注册
	boolean register(UserInfo u);
	
	//登录
	boolean login(UserInfo u);
	//从session得到我的stuId，当进别人的个人信息页面时，会损失对他人事件的操作权限，与修改他人资料权限
	UserInfo getMe(String stuId);
	
	//发布事件
	boolean pubInfo(Event e,String stuId);
	
	//分失物类别展示事件表格信息
	List<Event> tableShow(String index,String eKinds,String eType,int currentPage,int pageSize);
	int getTotalCount(String index,String eKinds,String eType);
	
	//事件详情页的评论总数
	int getTotalCount(int eId);
	//轮播图
	List<Event> showCarousel();
	//首页便签
	List<Event> tipsShow(String type);
	
	//主页的报喜内容
	List<ThanksMail> thanks();
	//感谢信详情
	ThanksMail thanksDetail(int tId);
	
	//事件详情页
	Event eventDetail(int eId);
	List<MarkDetail> getMarks(int eId,int pNo,int size);
	//发送评论
	boolean sendComment(String stuId, int eId,String markDetail);
	
	//主页的校园卡
	List<Event> getCards(String eKinds);
	
	//个人信息页面
	UserInfo selfInfo(String stuId);
	List<Event> showMyEvents(String stuId,int currentPage,int pageSize);
	
	//修改密码,判断旧密码是否正确
	int modifypwd(UserInfo user, String newPwd);
	boolean checkOldPwd(UserInfo user);
	
	//修改资料
	int modifyuInfo(UserInfo user);
	//我的发布总数量
	int getTotalCount(String stuId);
	
	//修改事件状态
	boolean modifyState(int eId,int operate);
	
	//修改头像
	boolean uploadHead(UserInfo u);
	
	//判断学号是否存在
	boolean stuIsExit(String stuId);
	//判断用户名是否存在
	boolean checkuName(String uName);
	
	//报喜
	boolean pubMails(ThanksMail mail);
}

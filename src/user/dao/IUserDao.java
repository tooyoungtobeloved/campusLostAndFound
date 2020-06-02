package user.dao;

import java.util.List;

import user.entity.Event;
import user.entity.MarkDetail;
import user.entity.ThanksMail;
import user.entity.UserInfo;

public interface IUserDao {
	
	//检测学号,用户名是否存在
	boolean stuIsExit(String stuId);
	boolean checkuName(String uName);
	
	//用户注册
	boolean register(UserInfo uInfo);
	
	//用户登录
	boolean login(UserInfo u);
	UserInfo getMe(String stuId);
	
	//发布事件
	boolean pubInfo(Event e,String stuId);
	
	////分失物类别展示事件表格信息
	List<Event> tableShow(String index,String eKinds,String eType,int currentPage,int pageSize);
	//事件汇总页的分页组成
	int getTotalCount(String index,String eKinds,String eType);
	
	//事件详情里的评论分页总数
	int getTotalCount(int eId);
	
	//首页轮播图
	List<Event> showCarousel();
	//首页的便签展示
	List<Event> tipsShow(String type);
	
	String getunName(String stuId);
	
	//主页的报喜内容
	List<ThanksMail> thanks();
	ThanksMail thanksDetail(int tId);
	
	//事件详情
	Event eventDetail(int eId);
	//事件下的评论
	List<MarkDetail> getMarks(int eId,int pNo,int size);
	
	//发送评论
	boolean sendComment(String stuId, int eId,String markDetail);
	
	//主页的校园卡轮播图
	List<Event> getCards(String eKinds);
	
	//显示个人资料
	UserInfo selfInfo(String stuId);
	//修改密码
	int modifypwd(UserInfo user, String newPwd);
	//检测旧密码正确性
	boolean checkOldPwd(UserInfo user);
	
	//修改资料
	int modifyuInfo(UserInfo user);
	//显示我的事件
	List<Event> showMyEvents(String stuId,int currentPage,int pageSize);
	//得到我的事件总数
	int getTotalCount(String stuId);
	
	//修改头像
	boolean uploadHead(UserInfo u);
	
	//修改事件状态
	boolean modifyState(int eId,int operate);
	
	//发布感谢信
	boolean pubMails(ThanksMail mail);
}

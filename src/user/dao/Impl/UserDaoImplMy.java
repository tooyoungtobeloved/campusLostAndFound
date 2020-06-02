package user.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import user.dao.IUserDao;
import user.entity.Event;
import user.entity.MarkDetail;
import user.entity.ThanksMail;
import user.entity.UserInfo;
import utils.DBUtil;

public class UserDaoImplMy implements IUserDao{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public boolean stuIsExit(String stuId) {
		int result = -1;
		ResultSet rst = null;
		String sql = "select count(1) from user where stuId=?";
		Object[] obj = {stuId};
		try {
			rst = DBUtil.executeQuery(sql, obj);
			
			if(rst.next()) {
				result = rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rst, null, null);
		}return result>0;
	}
	
	
	
	@Override
	public boolean register(UserInfo uInfo) {
		int count1 = 0;
		int count2 = 0;
		String sql = "insert into user(stuId,uPwd) values(?,?)";
		Object[] obj = {uInfo.getStuId(),uInfo.getuPwd()};
		try {
			count1 = qr.update(sql,obj);
			sql = "insert into userinfo(stuId,uName,uImage,registerTime) values(?,?,?,?)";
			Object[] obj2 = {uInfo.getStuId(),uInfo.getuName(),"img/initialPic.jpg",new Date()};
			count2 = qr.update(sql,obj2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count1>0&&count2>0;
	}

	@Override
	public boolean login(UserInfo u) {
		int result = 0;
		String sql = "select count(*) from user where stuId=? and uPwd=?";
		Object[] obj = {u.getStuId(),u.getuPwd()};
		ResultSet rst = null;
		try {
			rst = DBUtil.executeQuery(sql, obj);
			if(rst.next()) {
				result = rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(DBUtil.getConnection(), null, rst);
		}
		return result>0;
		
	}

	@Override
	public UserInfo selfInfo(String stuId) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where stuId=?";
		try {
			user = qr.query(sql, new BeanHandler<UserInfo>(UserInfo.class),stuId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean pubInfo(Event e,String stuId) {
		int count1 = 0;
		int count2 = 0;
		int eStatus = 0;
		String sql = "insert into myevent values(?,null,?)";
		Object[] obj = {stuId,eStatus};
		try {
			count1 = qr.update(sql,obj);
			if(count1>0) {
				System.out.println("插入myevent成功");
			}else {
				System.out.println("插入myevent失败");
			}
			//eType,eKinds,eTitle,eDetail,ePlace,eContact,eDate,ePubDate,eImage
			sql = "insert into event values(null,?,?,?,?,?,?,?,?,?)";
			Object[] obj2 = {e.geteType(),
							e.geteKinds(),
							e.geteTitle(),
							e.geteDetail(),
							e.getePlace(),
							e.geteContact(),
							e.geteDate(),
							e.getePubDate(),
							e.geteImage()};
			count2 = qr.update(sql,obj2);
			if(count2>0) {
				System.out.println("插入event成功");
			}else {
				System.out.println("插入event失败");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return count1>0&&count2>0;
	}

	@Override
	public List<Event> tableShow(String index,String eType,String eKinds,int currentPage,int pageSize) {
		String sql = null;
		List<Event> events = new ArrayList<Event>();
		int num = (currentPage-1)*pageSize;
		if(index=="" || index.isEmpty() || index==null) {
			if(eKinds.equals("所有")) {
				sql = "select * from tableshow where eType=? limit ?,?";
				Object[] obj = {eType,num,pageSize};
				try {
					events = qr.query(sql, new BeanListHandler<Event>(Event.class),obj);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				sql = "select * from tableshow where eType=? and eKinds=? limit ?,?";
				Object[] obj = {eType,eKinds,num,pageSize};
				try {
					events = qr.query(sql, new BeanListHandler<Event>(Event.class),obj);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			sql = "select * from tableshow where eType=? and edetail like ? or etitle like ? limit ?,?";
			Object[] obj = {eType,"%"+index+"%","%"+index+"%",num,pageSize};
			try {
				events = qr.query(sql, new BeanListHandler<Event>(Event.class),obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}

	@Override
	public int getTotalCount(String index,String eType,String eKinds) {
		String sql = null;
		if(index=="" || index.isEmpty() || index==null) {
			if(eKinds.equals("所有")) {
				sql = "select count(*) from (select distinct event.eid from event,myevent where eType=? and eStatus=1) as info;";
				Object[] obj = {eType};
				return DBUtil.getTotalCount(sql, obj);
			}else {
				sql = "select count(*) from (select distinct event.eid from event,myevent where eType=? and eKinds=? and eStatus=1) as info;";
				Object[] obj = {eType,eKinds};
				return DBUtil.getTotalCount(sql,obj);
			}
		}else {
			sql = "select count(*) from (select distinct event.eid from event,myevent where eType=? and edetail like ? and eStatus=1) as info;";
			Object[] obj = {eType,"%"+index+"%"};
			return DBUtil.getTotalCount(sql, obj);
		}
		
	}
	
	@Override
	public List<Event> showCarousel() {
		String sql = "select myevent.eId,eImage from event,myevent where (estatus=1 and event.eId=myevent.eId)  order by epubdate desc limit 0,8";
		List<Event> events = new ArrayList<Event>();
		try {
			events = qr.query(sql, new BeanListHandler<Event>(Event.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}

	@Override
	public List<Event> tipsShow(String type) {
		//tips是一个视图
		String sql = "select * from tips where eType=? limit 0,10";
		List<Event> events = new ArrayList<>();
		try {
			events = qr.query(sql, new BeanListHandler<Event>(Event.class),type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	//是否需要存在
	@Override
	public String getunName(String stuId) {
		String uname = null;
		String sql = "select uName from userinfo where stuId=?";
		try {
			uname = qr.query(sql, new ScalarHandler<String>(),stuId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uname;
	}


	//感谢信列表
	@Override
	public List<ThanksMail> thanks() {
		List<ThanksMail> notes = new ArrayList<>();
		String sql = "select tId,tTitle,tDetail from thanknote order by tDate desc limit 0,9";
		try {
			notes = qr.query(sql, new BeanListHandler<ThanksMail>(ThanksMail.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notes;
	}



	//感谢信
	@Override
	public ThanksMail thanksDetail(int tId) {
		ThanksMail thank = null;
		String sql = "select * from thanknote where tId=?";
		try {
			thank = qr.query(sql,new BeanHandler<ThanksMail>(ThanksMail.class), tId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thank;
	}

	@Override
	public Event eventDetail(int eId) {
		Event event = new Event();
		String sql = "select userInfo.stuId,uName,uImage,event.* from userinfo,myevent,event where userinfo.stuId = myevent.stuId and myevent.eId = event.eId and event.eId=?";
		try {
			event = qr.query(sql, new BeanHandler<Event>(Event.class),eId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}



	@Override
	public List<MarkDetail> getMarks(int eId,int pNo,int size) {
		int num = (pNo-1)*size;
		List<MarkDetail> marks= new ArrayList<MarkDetail>();
		String sql = "select * from marks where eId =? limit ?,?";
		Object[] obj = {eId,num,size};
		try {
			marks = qr.query(sql, new BeanListHandler<MarkDetail>(MarkDetail.class),obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(null, null, DBUtil.getConnection());
		}
		return marks;
	}


	//主页的校园卡
	@Override
	public List<Event> getCards(String eKinds) {
		List<Event> events = new ArrayList<>();
		String sql = "select event.eId,eImage from event,myevent where eKinds=? and estatus=1 and eImage is not null limit 0,8";
		try {
			events = qr.query(sql, new BeanListHandler<Event>(Event.class),eKinds);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return events;
	}


	//发送评论返回布尔
	@Override
	public boolean sendComment(String stuId, int eId,String markDetail) {
		int count = 0;
		try {
			String sql = "insert into markdetail values(null,?,?,null,?)";
			Object[] obj = {eId,stuId,markDetail};
			count = qr.update(sql ,obj);
			if(count>0) {
				System.out.println("markdetail插入成功");
			}else {
				System.out.println("markdetail插入失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count>0;
	}


	//得到此事件的评论总数
	@Override
	public int getTotalCount(int eId) {
		String sql = "select count(1) from markdetail where eId=?";
		Object[] obj = {eId};
		return DBUtil.getTotalCount(sql, obj);
	}

	@Override
	public UserInfo getMe(String stuId) {
		UserInfo user = new UserInfo();
		String sql = "select uName,uImage from userInfo where stuId=?";
		try {
			user =  qr.query(sql, new BeanHandler<UserInfo>(UserInfo.class),stuId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	//因为进行了表单验证，所以可以直接修改
	@Override
	public int modifypwd(UserInfo user, String newPwd) {
		//-1是旧密码错误，1是修改成功，0是修改失败
		int result = -1;
			String sql = "update user set uPwd=? where stuId=?";
			try {
				result = qr.update(sql,newPwd,user.getStuId());
				if(result>0){
					return 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
			return -1;
	}


	//修改个人资料
	@Override
	public int modifyuInfo(UserInfo user) {
		String sql = "update userinfo set uName=?,uMail=?,uSex=?,uQQ=?,uDesc=? where stuId=?";
		Object[] obj = {user.getuName(),user.getuMail(),user.getuSex(),user.getuQQ(),user.getuDesc(),user.getStuId()};
		int result = -1;
		try {
			result = qr.update(sql,obj);
			if(result>0) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		return -1;
	}


	//显示我的事件列表
	@Override
	public List<Event> showMyEvents(String stuId,int currentPage,int pageSize) {
		List<Event> myEvents = new ArrayList<Event>();
		int index = (currentPage-1)*pageSize;//每页显示的第一个数据
		String sql = "select event.*,myevent.eStatus from myevent,event where myevent.eId=event.eId and stuId=? order by epubdate desc limit ?,?";
		Object[] obj = {stuId,index,pageSize};
		try {
			myEvents = qr.query(sql, new BeanListHandler<Event>(Event.class),obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myEvents;
	}

	//得到我发布的事件总数
	@Override
	public int getTotalCount(String stuId) {
		String sql = "select count(eId) from myevent where stuId=?";
		Object[] obj = {stuId};
		DBUtil.closeAll(null, null, DBUtil.getConnection());
		return DBUtil.getTotalCount(sql, obj);
	}

	//修改我的事件的状态，默认0审核状态，1正在进行，2已结束
	@Override
	public boolean modifyState(int eId, int operate) {
		int count = -1;
		String sql = null;
		if(operate == 2) {//结束事件
			Object[] obj = {operate,eId};
			sql = "update myevent set eStatus=? where eId=?";
			try {
				count = qr.update(sql,obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			Object[] obj = {eId};
			sql = "delete from myevent where eId=?";//操作为2就删除事件
			try {
				count = qr.update(sql,obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count>0?true:false;
	}

	@Override
	public boolean uploadHead(UserInfo u) {
		int count = -1;
		String sql = "update userInfo set uImage=? where stuId=?";
		Object[] obj = {u.getuImage(),u.getStuId()};
		try {
			count = qr.update(sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count>0;
	}

	@Override
	public boolean checkuName(String uName) {
		int result = 0;
		String sql = "select count(1) from userInfo where uName=?";
		try {
			Long temp = (Long)qr.query(sql, new ScalarHandler<Long>(),uName);
			result = temp.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result>0;
	}



	@Override
	public boolean checkOldPwd(UserInfo user) {
		int result = -1;
		String sql = "select count(1) from user where stuId=? and uPwd=?";
		Object[] obj = {user.getStuId(),user.getuPwd()};
		try {
			Long temp = qr.query(sql, new ScalarHandler<Long>(),obj);
			result = temp.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result>0;
	}



	@Override
	public boolean pubMails(ThanksMail mail) {
		int count = -1;
		String sql = "insert into thanknote values(null,?,?,?)";
		Object[] obj = {mail.gettTitle(),mail.gettDetail(),new Date()};
		try {
			count = qr.update(sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count>0;
	}
	
}

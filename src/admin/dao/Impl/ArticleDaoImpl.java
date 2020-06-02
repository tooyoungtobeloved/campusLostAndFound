package admin.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import admin.dao.IArticleDao;
import admin.entity.Article;
import utils.DBUtil;

public class ArticleDaoImpl implements IArticleDao{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public List<Article> queryArticle(int limit, int page) {
		List<Article> result = null;
		String sql = "SELECT userinfo.uName,userinfo.stuId,event.eId ,eType,myevent.eStatus, eKinds, eTitle, eDetail, ePlace, eContact, DATE_FORMAT(eDate,'%Y-%m-%d %H:%i') as eDate, DATE_FORMAT(ePubDate,'%Y-%m-%d %H:%i') as ePubDate FROM myevent,`event`,userinfo where myevent.stuId = userinfo.stuId AND myevent.eId = `event`.eId order by eStatus limit ?,?";
		int pages = (page - 1) * limit;
		try {
			result = qr.query( sql, new BeanListHandler<Article>(Article.class),pages,limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/*计算文章总量
	 * @see admin.dao.Iarticle#CountArticle()
	 */
	@Override
	public int CountArticle() {
		String sql = "select count(eId) from event";
		return DBUtil.getTotalCount(sql, null);
	}
	@Override
	public int deleteEventWithId(String eId) {
		int status = 0;
		String sql = "delete from myevent where eId = ?";
		try {
			status = qr.update(sql,eId);
			System.out.println(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	/*
	 * 根据状态查询, 0是待审，1是正在进行，2是已经结束
	 * @see admin.dao.Iarticle#queryArticle(int, int, int)
	 */
	public List<Article> queryArticle(int status, int limit, int page) {
		List<Article> result = null;
		String sql = "SELECT userinfo.uName,userinfo.stuId,event.eId ,eType,myevent.eStatus, eKinds, eTitle, eDetail, ePlace, eContact, DATE_FORMAT(eDate,'%Y-%m-%d %H:%i') as eDate, "
				+ "DATE_FORMAT(ePubDate,'%Y-%m-%d %H:%i') as ePubDate FROM myevent,`event`,userinfo where myevent.stuId = userinfo.stuId AND myevent.eId = `event`.eId and myevent.eStatus = ?  limit ?,?";
		int pages = (page - 1) * limit;
		try {
			result = qr.query(sql, new BeanListHandler<Article>(Article.class),status, pages,limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int countTotal(int status) {
		String sql = "select count(eId) from myevent where eStatus = ?";
		Object[] obj = {status};
		return DBUtil.getTotalCount(sql, obj);
	}
	@Override
	public int passEvent(int status, int eId) {
		String sql = "UPDATE myevent set eStatus = ?  WHERE eId = ?";
		int result = 0;
		try {
			result = qr.update(sql, status, eId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<Article> searchWithContent(int status,int limit, int page,String content) {
		int pages = (page - 1) * limit;
		List<Article> result = null;
		String sql = "SELECT userinfo.uName,userinfo.stuId,event.eId ,eType,myevent.eStatus, eKinds, eTitle, eDetail, ePlace, eContact, DATE_FORMAT(eDate,'%Y-%m-%d %H:%i') as eDate, "
				+ "DATE_FORMAT(ePubDate,'%Y-%m-%d %H:%i') as ePubDate FROM myevent,`event`,userinfo where myevent.stuId = userinfo.stuId AND myevent.eId = `event`.eId and myevent.eStatus = ? and (eTitle like concat('%" + content + "%')"+" or eDetail like concat('%" + content + "%')"+") limit ?,?";
		try {
			result = qr.query(sql, new BeanListHandler<Article>(Article.class),status,pages,limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int countTotal(int status, String content) {
		String sql = "SELECT COUNT(*) FROM `event`,myevent,userinfo where myevent.stuId = userinfo.stuId AND myevent.eId = `event`.eId and eStatus = ? and (eTitle like concat('%',?,'%') or eDetail like concat('%',?,'%'))";
		Object[] obj = {status,content,content};
		return DBUtil.getTotalCount(sql, obj);
	}
	@Override
	public List<Article> searchWithoutStatus(int limit, int page, String content) {
		int pages = (page - 1) * limit;
		List<Article> result = null;
		String sql = "SELECT userinfo.uName,userinfo.stuId,event.eId ,eType,myevent.eStatus, eKinds, eTitle, eDetail, ePlace, eContact, DATE_FORMAT(eDate,'%Y-%m-%d %H:%i') as eDate, "
				+ "DATE_FORMAT(ePubDate,'%Y-%m-%d %H:%i') as ePubDate FROM myevent,`event`,userinfo where myevent.stuId = userinfo.stuId AND myevent.eId = `event`.eId and (eTitle like concat('%" + content + "%')"+" or eDetail like concat('%" + content + "%')"+") limit ?,?";
		try {
			result = qr.query(sql, new BeanListHandler<Article>(Article.class),pages,limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
		

}

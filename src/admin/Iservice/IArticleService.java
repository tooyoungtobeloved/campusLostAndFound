package admin.Iservice;

import java.util.List;

import admin.entity.Article;

public interface IArticleService {
	public List<Article> queryArticle(int limit, int page);
	/*
	 * 文章总数
	 */
	public int countTotal();
	
	public int deleteEventWithID(String eId);
	
	/*
	 * 根据状态查
	 */
	public List<Article> queryArticle(int status,int limit, int page);
	
	/*
	 * 状态查总数
	 */
	public int countotal(int status);
	
	/*
	 * 设置通过
	 */
	public int passEvent(int status, int eId);
	
	
	public List<Article> queryWith(int status,int limit, int page,String content);
	
	public int countTotal(int status,String content);
	
	/*
	 * 无状态查询
	 */
	public List<Article> queryWithout(int limit, int page, String content);
}

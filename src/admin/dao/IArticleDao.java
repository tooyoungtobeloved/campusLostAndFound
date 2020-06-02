package admin.dao;

import java.util.List;

import admin.entity.Article;

public interface IArticleDao {
	public List<Article> queryArticle(int limit, int page);
	public int CountArticle();
	/*
	 * 删除事件
	 */
	public int deleteEventWithId(String eId);
	
	/*
	 * 根据状态查询
	 */
	public List<Article> queryArticle(int status, int limit, int page);
	
	public int countTotal(int status);
	/*
	 * 通过事件
	 */
	public int passEvent(int status, int eId);
	
	/*
	 * 根据内容和标题搜索
	 */
	public List<Article> searchWithContent(int status, int limit, int page, String content);
	
	/*
	 * countTotal
	 */
	public int countTotal(int status, String content);
	
	/*不限状态搜索
	 * 
	 */
	public List<Article> searchWithoutStatus(int limit, int page,String content);
}

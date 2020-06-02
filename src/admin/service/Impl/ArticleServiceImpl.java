package admin.service.Impl;

import java.util.List;

import admin.Iservice.IArticleService;
import admin.dao.Impl.ArticleDaoImpl;
import admin.entity.Article;

public class ArticleServiceImpl implements IArticleService{

	private ArticleDaoImpl AI = new ArticleDaoImpl();
	
	@Override
	public List<Article> queryArticle(int limit, int page) {
		return AI.queryArticle(limit, page);
	}

	@Override
	public int countTotal() {
		return AI.CountArticle();
	}

	@Override
	public int deleteEventWithID(String eId) {
		return AI.deleteEventWithId(eId);
	}

	@Override
	public List<Article> queryArticle(int status, int limit, int page) {
		return AI.queryArticle(status, limit, page);
	}

	@Override
	public int countotal(int status) {
		return AI.countTotal(status);
	}

	@Override
	public int passEvent(int status, int eId) {
		return AI.passEvent(status, eId);
	}

	@Override
	public List<Article> queryWith(int status, int limit, int page, String content) {
		return AI.searchWithContent(status, limit, page, content);
	}

	@Override
	public int countTotal(int status, String content) {
		return AI.countTotal(status, content);
	}

	@Override
	public List<Article> queryWithout(int limit, int page, String content) {
		return AI.searchWithoutStatus(limit, page, content);
	}

}

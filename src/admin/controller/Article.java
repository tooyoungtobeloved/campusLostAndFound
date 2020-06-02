package admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import admin.Iservice.IArticleService;
import admin.service.Impl.ArticleServiceImpl;

/**
 * Servlet implementation class Article
 */
@WebServlet("/Article")
public class Article extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IArticleService AS = new ArticleServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		String content = request.getParameter("content");
		if(content != null) {
			doSearch(request, response);
		}else {
			IArticleService AS = new ArticleServiceImpl();
		HashMap<String, Object> result = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit =Integer.parseInt(request.getParameter("limit"));
		result.put("result", AS.queryArticle(limit,page));
		result.put("total", AS.countTotal());
		result.put("code", 0);
		String re = JSON.toJSONString(result);
		response.getWriter().append(re);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String Method = request.getParameter("method");
		
		if(Method.equals("delete")) {
			dodelete(request, response);
		}else if(Method.equals("pass")) {
			dopass(request,response);
		}
		else {
			doGet(request, response);
		}
		
		
	}
	
	/*
	 *RestFul 风格 dodelete 
	 */
	private void dodelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		String eId = req.getParameter("eId");
//		System.out.println(eId);

		HashMap<String, Object> res = new HashMap<String, Object>();
		
		if(AS.deleteEventWithID(eId) == 1) {
			res.put("code", 0);
		}
		else {
			res.put("code", 500);
		}
		String result = JSON.toJSONString(res);
		resp.getWriter().append(result);
		
		
	}
	private void dopass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		String eId = req.getParameter("eId");
//		System.out.println(eId);

		HashMap<String, Object> res = new HashMap<String, Object>();
		
		if(AS.deleteEventWithID(eId) == 1) {
			res.put("code", 0);
		}
		else {
			res.put("code", 500);
		}
		String result = JSON.toJSONString(res);
		resp.getWriter().append(result);
		
	}
	
	private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("application/json;charset=utf-8");
		String content = request.getParameter("content");
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		IArticleService AS = new ArticleServiceImpl();
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", AS.queryWithout(limit, page, content));
		result.put("code", 0);
		String re = JSON.toJSONString(result);
		response.getWriter().append(re);
		
	}
}

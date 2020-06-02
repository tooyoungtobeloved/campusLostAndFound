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
 * Servlet implementation class WaitpassArticle
 */
@WebServlet("/WaitpassArticle")
public class WaitpassArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		String method = request.getParameter("method");
		String content = request.getParameter("content");
		if (content != null) {
			doSearch(request, response);
		} else {
			if ("pass".equals(method) ) {
				doSet(request, response);
			} else if ("end".equals(method)) {
				doEnd(request, response);
			} else {
				int limit = Integer.parseInt(request.getParameter("limit"));
				int page = Integer.parseInt(request.getParameter("page"));
				int status = Integer.parseInt(request.getParameter("status"));
				IArticleService asi = new ArticleServiceImpl();
				HashMap<String, Object> result = new HashMap<String, Object>();

				result.put("result", asi.queryArticle(status, limit, page));
				result.put("total", asi.countotal(status));
				result.put("code", 0);
				String re = JSON.toJSONString(result);
				response.getWriter().append(re);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void doSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		int eId = Integer.parseInt(request.getParameter("eId"));
		IArticleService asi = new ArticleServiceImpl();
		
		if(asi.passEvent(1,eId) != 0) {
			response.getWriter().append("success");
		}
		
		
	}

	private void doEnd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int eId = Integer.parseInt(request.getParameter("eId"));
		IArticleService asi = new ArticleServiceImpl();

		if (asi.passEvent(2,eId) != 0) {
			response.getWriter().append("success");
		}

	}
	private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		String content = request.getParameter("content");
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		int status = Integer.parseInt(request.getParameter("status"));
		IArticleService AS = new ArticleServiceImpl();
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", AS.queryWith(status, limit, page, content));
		result.put("total", AS.countTotal(status, content));
		result.put("code", 0);
		String re = JSON.toJSONString(result);
		response.getWriter().append(re);
		
	}
	
}

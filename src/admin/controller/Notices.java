package admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import admin.entity.Notice;
import admin.service.Impl.NoticeServiceImpl;

/**
 * Servlet implementation class Notices
 */
@WebServlet("/Notices")
public class Notices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method.equals("put")) {
			DoPut(request, response);
		}else if(method.equals("get")) {
			ShowNotices(request, response);
		}else if(method.equals("delete")) {
			doDel(request, response);
		}else if(method.equals("change")) {
			doChange(request, response);
		}
		/**
		 * change 改变置顶状态
		 */
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void DoPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;utf-8");
		NoticeServiceImpl INI = new NoticeServiceImpl();
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String isTop = request.getParameter("isTop");
		
		Notice N = null;
		if(isTop == null) {
			N = new Notice(null, title, content, new Date(), 0);
		}else {
			N = new Notice(null, title, content, new Date(), 1);
		}
		
		if(INI.insertNotice(N) != 0) {
			HashMap<String, Object> re = new HashMap<String, Object>();
			re.put("code", 0);
			String result = JSON.toJSONString(re);
			response.getWriter().append(result);
		}else {
			response.getWriter().append("error");
		}
	}
	
	private void ShowNotices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		NoticeServiceImpl INI = new NoticeServiceImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit =Integer.parseInt(request.getParameter("limit"));

		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", INI.showNotice(limit, page));
		result.put("total", INI.countTotal());
		result.put("code", 0);
		
		String res = JSON.toJSONString(result);
		
		response.getWriter().append(res);
		
	}

	/*
	 * 删除
	 */
	
	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		NoticeServiceImpl INI = new NoticeServiceImpl();
		HashMap<String, Object> res = new HashMap<String, Object>();
		if(INI.delete(noteId) != 0) {
			res.put("code",0);
		}else {
			res.put("code",500);
		}
		String r = JSON.toJSONString(res);
		response.getWriter().append(r);
	}
	
	private void doChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("applicaion/json;charset=utf-8");
		NoticeServiceImpl INI = new NoticeServiceImpl();
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		if(INI.setTop(noteId) != 0) {
			response.getWriter().append("success");
//			res.put("code", 0);
		}else {
			response.getWriter().append("error");
		}
		
	}
}

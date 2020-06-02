package admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import admin.service.Impl.ThanksNoteServiceImpl;

/**
 * Servlet implementation class ThanksNoteServlet
 */
@WebServlet("/ThanksNote")
public class ThanksNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		String method = request.getParameter("method");
		if("get".equals(method)) {
			ThanksNoteServiceImpl tnsi = new ThanksNoteServiceImpl();
			HashMap<String, Object> result = new HashMap<String, Object>();
			int page = Integer.parseInt(request.getParameter("page"));
			int limit =Integer.parseInt(request.getParameter("limit"));
			result.put("result", tnsi.queryThanksNote(limit, page));
			result.put("total", tnsi.countTotal());
			result.put("code", 0);
			String re = JSON.toJSONString(result);
			response.getWriter().append(re);
		}else {
			int tId = Integer.parseInt(request.getParameter("tId"));
			HashMap<String, Object> res = new HashMap<String, Object>();
			ThanksNoteServiceImpl tnsi = new ThanksNoteServiceImpl();

			if(tnsi.deleteWithtID(tId) != 0) {
				res.put("code", 0);
			}
			else {
				res.put("code", 500);
			}
			String result = JSON.toJSONString(res);
			response.getWriter().append(result);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

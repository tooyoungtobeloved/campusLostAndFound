package admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import admin.Iservice.ISuggestService;
import admin.service.Impl.SuggestServiceImpl;

@WebServlet("/ShowSuggest")
public class ShowSuggest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit =Integer.parseInt(request.getParameter("limit"));
		ISuggestService ISSI = new SuggestServiceImpl();
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("result", ISSI.querySuggest(limit, page));
		res.put("total", ISSI.countTotal());
		res.put("code", 0);
		String re = JSON.toJSONString(res);
		response.getWriter().append(re);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method != null) {
			doDel(request, response);
		}else {
			doGet(request, response);
		}
	}
	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=utf-8");
		int sugID = Integer.parseInt(request.getParameter("sugID"));
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		ISuggestService ISSI = new SuggestServiceImpl();
		if(ISSI.delSug(sugID) != 0) {
			res.put("code",0);
		}
		String r = JSON.toJSONString(res);
		response.getWriter().append(r);
	}
}

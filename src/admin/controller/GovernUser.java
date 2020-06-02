package admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import admin.service.Impl.AdminServiceImpl;
import admin.service.Impl.RegimeServiceImpl;

/**
 * Servlet implementation class GovernUser
 */
@WebServlet("/GovernUser")
public class GovernUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		String stuId = request.getParameter("stuId");
		if(stuId != null) {
			search(stuId,request, response);
		}
		
		else{
			AdminServiceImpl asi = new AdminServiceImpl();
			HashMap<String, Object> res = new HashMap<String, Object>();
			int page = Integer.parseInt(request.getParameter("page"));
			int limit =Integer.parseInt(request.getParameter("limit"));
			res.put("result", asi.queryAllUser(limit, page));
			res.put("code", 0);
			res.put("total", asi.countTotal());
			String result = JSON.toJSONString(res);
			response.getWriter().append(result);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method.equals("delete")) {
			delete(request, response);
		}
		else{
			doGet(request, response);
		}
	}
	
		private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("application/json;utf-8");
			String stuId = req.getParameter("stuId");
			
			RegimeServiceImpl rsi = new RegimeServiceImpl();
			
			
			System.out.println(stuId);
//			resp.getWriter().append(url);
			HashMap<String, Object> re = new HashMap<String, Object>();
			if(rsi.deleleUser(stuId) == 1) {
				re.put("code", 0);
			}
			else {
				re.put("code", 500);
			}
			String result = JSON.toJSONString(re);
			resp.getWriter().append(result);
		}
		
		private void search(String stuId,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("application/json;utf-8");
			RegimeServiceImpl rsi = new RegimeServiceImpl();
			
			HashMap<String, Object> re = new HashMap<String, Object>();
			
			re.put("result", rsi.searchUser(stuId));
			re.put("code", 0);
			re.put("total", rsi.countsearch(stuId));
			
			String result = JSON.toJSONString(re);
			resp.getWriter().append(result);
		}
		
}

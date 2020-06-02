package admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class ForAdmin
 */
@WebServlet("/ForAdmin")
public class ForAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method.equals("get")) {
			doG(request, response);
		}else if(method.equals("out")) {
			Out(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void doG(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		HashMap<String, Object> m = new HashMap<String, Object>();
		HttpSession hs = request.getSession();
		if(hs.getAttribute("admin") != null) {
			m.put("status", 0);
		}else if(hs.getAttribute("superadmin") != null) {
			m.put("status", 1);
		}else {
			m.put("status", 404);
		}
		String r = JSON.toJSONString(m);
		response.getWriter().append(r);
		
	}
	
	private void Out(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession hs = req.getSession();
		hs.invalidate();
		resp.getWriter().append("success");
		
	}
	
}

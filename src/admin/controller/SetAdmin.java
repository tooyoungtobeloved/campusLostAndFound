package admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import admin.service.Impl.SetADMINServiceImpl;

/**
 * Servlet implementation class ADMIN
 */
@WebServlet("/ADMIN")
public class SetAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method.equals("get")) {
			doG(request, response);
		}else if(method.equals("set")) {
			doS(request, response);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void doG(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		HashMap<String, Object> result = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit =Integer.parseInt(request.getParameter("limit"));
		SetADMINServiceImpl ASI = new SetADMINServiceImpl();
		result.put("result", ASI.queryAD(limit, page));
		result.put("total", ASI.countADMIN());
		result.put("code", 0);
		String r = JSON.toJSONString(result);
		response.getWriter().append(r);
	}

	private void doS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String stuId = request.getParameter("stuId");
		int type = Integer.parseInt(request.getParameter("type"));
		SetADMINServiceImpl AS = new SetADMINServiceImpl();
		if(AS.updateStatus(stuId, type) != 0) {
			response.getWriter().append("success");
		}else {
			response.getWriter().append("failure");
		}
		
	}
}

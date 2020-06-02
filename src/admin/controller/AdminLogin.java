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

import admin.Iservice.IAdminService;
import admin.entity.Admin;
import admin.service.Impl.AdminServiceImpl;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		IAdminService asi = new AdminServiceImpl();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String author =  request.getParameter("author");
		Admin ad = new Admin(id,pwd);
		HashMap<String, Object> result = new HashMap<String, Object>();
		//超级管理员登录
		if(author.equals("superadmin")) {
			if(asi.adminlogin(ad) == -1) {
				result.put("status", 500);
			}else if(asi.adminlogin(ad) == 0) {
				result.put("status", 404);
			}else if(asi.adminlogin(ad) == 1) {
				HttpSession hs = request.getSession();
				hs.setMaxInactiveInterval(60 * 60);
				hs.setAttribute("superadmin", ad.getAdmid());
				result.put("status", 200);
			}
			//普通管理员登录
		}else if(author.equals("admin")) {
			if(asi.prmariyAdminLogin(id, pwd) == 1) {
				HttpSession hs = request.getSession();
				hs.setMaxInactiveInterval(60 * 60);
				hs.setAttribute("admin", id);
				result.put("status", 200);
			}
			else {
				result.put("status", 404);
			}
		}
		
		String re = JSON.toJSONString(result);
		response.getWriter().append(re);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

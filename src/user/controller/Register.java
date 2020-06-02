package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.entity.UserInfo;
import user.service.Impl.UserServeImpl;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String stuId = request.getParameter("stuId");
		String uPwd = request.getParameter("uPwd");
		String uName = request.getParameter("uName");
		UserInfo u = new UserInfo(stuId,uPwd,uName);
		System.out.println(stuId);
		System.out.println(uPwd);
		System.out.println(uName);
		UserServeImpl service = new UserServeImpl();
		boolean flag = service.register(u);
		
		String result = JSON.toJSONString(flag);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

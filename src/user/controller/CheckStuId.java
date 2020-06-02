package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.service.Impl.UserServeImpl;

@WebServlet("/CheckStuId")
public class CheckStuId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String stuId = request.getParameter("stuId");
		
		UserServeImpl service = new UserServeImpl();
		boolean flag = service.stuIsExit(stuId);//返回true就是存在
		System.out.println(flag);
		String result = JSON.toJSONString(flag);
		out.print(result);//返回结果,true既是存在id不能注册，false是可以注册
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

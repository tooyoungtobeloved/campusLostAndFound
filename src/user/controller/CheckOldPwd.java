package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.entity.UserInfo;
import user.service.Impl.UserServeImpl;

@WebServlet("/CheckOldPwd")
public class CheckOldPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		String oldpwd = request.getParameter("oldpwd");
		String stuId = request.getParameter("stuId");
		UserInfo user = new UserInfo(stuId, oldpwd);
		
		UserServeImpl service = new UserServeImpl();
		boolean flag = service.checkOldPwd(user);
		
		String result = JSON.toJSONString(flag);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import user.entity.UserInfo;
import user.listener.ActivationListener;
import user.service.IUserService;
import user.service.Impl.UserServeImpl;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String stuId = request.getParameter("stuId");
		String pwd = request.getParameter("uPwd");
		System.out.println(stuId);
		System.out.println(pwd);
		
		
		IUserService service = new UserServeImpl();
		UserInfo u2 = service.getMe(stuId);
		UserInfo u = new UserInfo(stuId,pwd);
		boolean flag = service.login(u);
		if(flag) {
			HttpSession session = request.getSession();
			ActivationListener uInfo = new ActivationListener(stuId,u2.getuName(),u2.getuImage());
			session.setAttribute("stuId", uInfo);
		}
			
		String result = JSON.toJSONString(flag);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

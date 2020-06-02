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

import user.listener.ActivationListener;
import user.service.Impl.UserServeImpl;

/**
 * Servlet implementation class CheckUname
 */
@WebServlet("/CheckuName")
public class CheckuName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String uName = request.getParameter("uName");
		
		UserServeImpl service = new UserServeImpl();
		boolean flag = service.checkuName(uName);//返回true就是存在
		
		//修改我的用户名时，若判断为是我本来的用户名，则返回false，不予理会,false表示不存在，true表示存在
		HttpSession session = request.getSession();
		if(session.getAttribute("stuId")!=null) {
			ActivationListener uInfo = (ActivationListener)session.getAttribute("stuId");
			String myName = uInfo.getuName();
			if(uName.equals(myName)) {
				flag = false;
			}
		}
		
		String result = JSON.toJSONString(flag);
		
		
		
		
		out.print(result);//返回登录结果
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

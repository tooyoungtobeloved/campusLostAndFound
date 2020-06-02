package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import user.entity.UserInfo;
import user.listener.ActivationListener;

@WebServlet("/GetSession2")
public class GetSession2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		String result = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("stuId")!=null) {
			ActivationListener uInfo = (ActivationListener)session.getAttribute("stuId");
			UserInfo user = new UserInfo();
			user.setStuId(uInfo.getStuId());
			user.setuName(uInfo.getuName());
			user.setuImage(uInfo.getuImage());
			System.out.println(user.getuName());
			result = JSON.toJSONString(user);//返回我的信息
		}
		
		response.getWriter().print(result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

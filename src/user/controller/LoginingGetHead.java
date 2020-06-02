package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.entity.UserInfo;
import user.service.IUserService;
import user.service.Impl.UserServeImpl;

/**
 * Servlet implementation class LoginingGetHead
 */
@WebServlet("/LoginingGetHead")
public class LoginingGetHead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		String stuId = request.getParameter("stuId");
		IUserService service = new UserServeImpl();
		UserInfo user = service.getMe(stuId);
		String myHead = null;
		if(user != null) {
			myHead = user.getuImage();//拿到我的头像
		}
		
		String result = JSON.toJSONString(myHead);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

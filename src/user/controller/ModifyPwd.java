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
import user.service.IUserService;
import user.service.Impl.UserServeImpl;

@WebServlet("/ModifyPwd")
public class ModifyPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		HttpSession session = request.getSession();
		ActivationListener uInfo = (ActivationListener)session.getAttribute("stuId");
		String stuId = uInfo.getStuId();
		
		String oldpwd = request.getParameter("oldpwd");
		String newPwd = request.getParameter("newpwd");
		
		UserInfo user = new UserInfo(stuId,oldpwd);
		
		IUserService service = new UserServeImpl();
		int modify = service.modifypwd(user, newPwd);
		
		String result = JSON.toJSONString(modify);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

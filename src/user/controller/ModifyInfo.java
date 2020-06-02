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

@WebServlet("/ModifyInfo")
public class ModifyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		String modstuId = request.getParameter("modstuId");
		String moduName = request.getParameter("moduName");
		String moduMail = request.getParameter("moduMail");
		String moduSex = request.getParameter("moduSex");
		String moduQQ = request.getParameter("moduQQ");
		String moduDesc = request.getParameter("moduDesc");
		
		UserInfo user = new UserInfo(modstuId,moduName,moduMail,moduSex,moduQQ,moduDesc);
		
		//修改完资料把session里的uName同时修改
		HttpSession session = request.getSession();
		ActivationListener uInfo = (ActivationListener)session.getAttribute("stuId");
		uInfo.setuName(user.getuName());
		
		
		IUserService service = new UserServeImpl();
		int modResult = service.modifyuInfo(user);
		
		String result = JSON.toJSONString(modResult);
		response.getWriter().print(result);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

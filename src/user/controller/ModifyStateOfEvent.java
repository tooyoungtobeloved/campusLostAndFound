package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.service.IUserService;
import user.service.Impl.UserServeImpl;

@WebServlet("/ModifyStateOfEvent")
public class ModifyStateOfEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		int eId = Integer.parseInt(request.getParameter("eId"));
		int eStatus = Integer.parseInt(request.getParameter("operate"));
		
		IUserService service = new UserServeImpl();
		boolean modresult = service.modifyState(eId, eStatus);
		
		String result = JSON.toJSONString(modresult);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

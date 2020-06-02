package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.entity.ThanksMail;
import user.service.IUserService;
import user.service.Impl.UserServeImpl;

@WebServlet("/PubThanksMail")
public class PubThanksMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		
		ThanksMail mails = new ThanksMail();
		mails.settTitle(title);
		mails.settDetail(detail);
		IUserService service = new UserServeImpl();
		boolean pubMail = service.pubMails(mails);
		
		String result = JSON.toJSONString(pubMail);
		response.getWriter().print(result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

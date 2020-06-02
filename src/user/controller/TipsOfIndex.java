package user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.entity.ContactInfo;
import user.entity.Event;
import user.entity.Note;
import user.entity.ThanksMail;
import user.service.IAdminedService;
import user.service.IUserService;
import user.service.Impl.AdminedServiceImpl;
import user.service.Impl.UserServeImpl;

@WebServlet("/TipsOfIndex")
public class TipsOfIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		IUserService userService = new UserServeImpl();
		IAdminedService adminService = new AdminedServiceImpl();
		
		List<Note> notes = adminService.showNote();
		List<ContactInfo> contacts = adminService.contactUs();
		List<Event> gotTips = userService.tipsShow("寻物启事");//主页的寻物启事
		List<Event> lostTips = userService.tipsShow("失物招领");//主页的失物招领
		List<Event> carousel = userService.showCarousel();//主页的轮播图
		List<ThanksMail> thanks = userService.thanks();
		List<Event> cards = userService.getCards("校园卡");//获得主页的校园卡
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("thanks", thanks);
		maps.put("notes", notes);
		maps.put("contacts", contacts);
		maps.put("gotTips", gotTips);
		maps.put("lostTips", lostTips);
		maps.put("carousel", carousel);
		maps.put("cards", cards);
		String result = JSON.toJSONString(maps);
		response.getWriter().print(result);//返回登录结果
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

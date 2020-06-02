package user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import user.entity.Event;
import user.entity.Page;
import user.entity.UserInfo;
import user.listener.ActivationListener;
import user.service.Impl.UserServeImpl;

@WebServlet("/SelfInfo")
public class SelfInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		String stuId = request.getParameter("stuId");
		
		UserServeImpl service = new UserServeImpl();
		UserInfo uInfo = service.selfInfo(stuId);
		
		//获取本人的stuId，判断是否为本人
		HttpSession session = request.getSession();
		ActivationListener myInfo = (ActivationListener)session.getAttribute("stuId");
		String mystuId = null;
		if(myInfo!=null) {
			mystuId = myInfo.getStuId();
		}
		
		//分页
		String cPage = request.getParameter("pNo");//当前页码
		if(cPage==null || cPage.isEmpty()) {
			cPage = "1";
		}
		int pNo = Integer.parseInt(cPage);
		int size = 6;//每页显示的数据总数
		Page page = new Page();
		page.setTotalCount(service.getTotalCount(stuId));
		page.setPageSize(size);
		if(pNo<1) {
			pNo = 1;
		}else if(pNo>page.getTotalPage()) {
			pNo = page.getTotalPage();
		}
		page.setPageNo(pNo);
		
		//我的发布
		List<Event> myEvents = service.showMyEvents(stuId,pNo,size);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("uInfo", uInfo);
		maps.put("myEvents", myEvents);
		maps.put("mystuId", mystuId);
		maps.put("page", page);
		
		String result = JSON.toJSONString(maps);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

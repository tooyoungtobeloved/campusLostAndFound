package user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import user.entity.Event;
import user.entity.MarkDetail;
import user.entity.Page;
import user.entity.ThanksMail;
import user.entity.UserInfo;
import user.listener.ActivationListener;
import user.service.IUserService;
import user.service.Impl.UserServeImpl;

@WebServlet("/EventDetail")
public class EventDetail extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		HttpSession session = request.getSession();
		ActivationListener uInfo = (ActivationListener)session.getAttribute("stuId");
		
		String uImage = null;
		String stuId = null;
		String uName = null;
		if(uInfo==null) {
			uImage = "img/UserSecret.png";
		}else {
			uImage = uInfo.getuImage();
			stuId = uInfo.getStuId();
			uName = uInfo.getuName();
		}
		
		int eId = Integer.parseInt(request.getParameter("eId"));
		
		IUserService service = new UserServeImpl();
		Event event = service.eventDetail(eId);
		
		String cPage = request.getParameter("pNo");//当前页码
		if(cPage==null || cPage.isEmpty() || cPage=="0") {
			cPage = "1";
		}
		int pNo = Integer.parseInt(cPage);
		int size = 6;//每页显示的数据总数
		Page page = new Page();
		page.setTotalCount(service.getTotalCount(eId));
		page.setPageSize(size);
		System.out.println(page.getTotalPage());
		if(pNo<1) {
			pNo = 1;
		}else if(pNo>page.getTotalPage()) {
			pNo = page.getTotalPage();
		}
		page.setPageNo(pNo);
		List<MarkDetail> marks = service.getMarks(eId,pNo,size);
		
		//感谢信
		List<ThanksMail> thanks = service.thanks();
		
		UserInfo u = new UserInfo();
		u.setStuId(stuId);
		u.setuImage(uImage);
		u.setuName(uName);
		
		HashMap<String, Object> maps = new HashMap<String,Object>();
		maps.put("events", event);
		maps.put("marks", marks);
		maps.put("page", page);
		maps.put("u", u);
		maps.put("thanks", thanks);
		
		String result = JSON.toJSONString(maps);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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

import user.entity.Event;
import user.entity.Page;
import user.service.IUserService;
import user.service.Impl.UserServeImpl;

@WebServlet("/TableShow")
public class TableShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		String eType = request.getParameter("eType");
		String eKinds = request.getParameter("eKinds");
		String index = request.getParameter("index");
		if(index.equals("undefined")) {
			index = "";
		}
		
		String cPage = request.getParameter("pNo");//当前页码
		if(cPage==null || cPage.isEmpty()) {
			cPage = "1";
		}
		int pNo = Integer.parseInt(cPage);
		int size = 5;//每页显示的数据总数
		
		IUserService service = new UserServeImpl();
		
		Page page = new Page();
		page.setTotalCount(service.getTotalCount(index,eType,eKinds));
		page.setPageSize(size);
		
		if(pNo<1) {
			pNo = 1;
		}else if(pNo>page.getTotalPage()) {
			pNo = page.getTotalPage();
		}
		System.out.println(pNo);
		page.setPageNo(pNo);
		List<Event> events = service.tableShow(index,eType,eKinds,pNo,size);
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("event", events);
		maps.put("page", page);
		
		String result = JSON.toJSONString(maps);
		response.getWriter().print(result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

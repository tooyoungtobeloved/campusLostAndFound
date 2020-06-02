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
import user.entity.Note;
import user.service.IAdminedService;
import user.service.Impl.AdminedServiceImpl;

@WebServlet("/AdminShowInfo")
public class AdminShowInfoOfIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		IAdminedService service = new AdminedServiceImpl();
		
		List<Note> notes = service.showNote();
		List<ContactInfo> contacts = service.contactUs();
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		
		maps.put("notes", notes);
		maps.put("contacts", contacts);
		
		String result = JSON.toJSONString(maps);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

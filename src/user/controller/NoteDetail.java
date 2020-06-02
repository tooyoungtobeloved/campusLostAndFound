package user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import user.entity.Note;
import user.entity.ThanksMail;
import user.service.IAdminedService;
import user.service.IUserService;
import user.service.Impl.AdminedServiceImpl;
import user.service.Impl.UserServeImpl;

@WebServlet("/NoteDetail")
public class NoteDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		
		IAdminedService noteService = new AdminedServiceImpl();
		IUserService mailService = new UserServeImpl();
		
		ThanksMail thanks = mailService.thanksDetail(noteId);
		Note notes = noteService.noteDetail(noteId);
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("thanks", thanks);
		maps.put("notes", notes);
		
		String result = JSON.toJSONString(maps);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

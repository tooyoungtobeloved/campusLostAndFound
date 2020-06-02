package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import admin.Iservice.IDrawImageService;
import admin.service.Impl.DrawImageServiceImpl;



@WebServlet("/DrawImageS")
public class DrawImageS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method.equals("column")) {
			drawColumn(request, response);
		}else if(method.equals("line")) {
			drawLine(request, response);
		}else if(method.equals("info")) {
			getInfo(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
	private void drawLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		IDrawImageService III = new DrawImageServiceImpl();
		String res = JSON.toJSONString(III.drawLine());
		response.getWriter().append(res);
	}
	private void drawColumn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		IDrawImageService III = new DrawImageServiceImpl();
		String res = JSON.toJSONString(III.drawImage());
		response.getWriter().append(res);
	}
	private void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		IDrawImageService III = new DrawImageServiceImpl();
		String res = JSON.toJSONString(III.countInfo());
		response.getWriter().append(res);
	}
}

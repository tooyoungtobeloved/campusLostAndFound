package user.controller;

import java.io.IOException;
import java.util.Map;

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
import utils.ReceiveFormdata;

@WebServlet("/UpLoadHead")
public class UpLoadHead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings({ "unchecked", "static-access" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		
		HttpSession session = request.getSession();
		ActivationListener listener = (ActivationListener)session.getAttribute("stuId");
		String stuId = listener.getStuId();
		Long imgName = System.currentTimeMillis();
		String directory = "head";
		ReceiveFormdata receive = new ReceiveFormdata();
		//调用这个方法获取每个文件流或是数据的value
		Map<String,Object> impfileMap = receive.getFilePath(request,imgName.toString(),directory,stuId);//获取解析后的参数列表
		
		String filename = impfileMap.get("fileName").toString();
		String uImage = null;
		//判断是否上传了图片
		if(filename!=null && !filename.isEmpty()) {
			uImage = "http://localhost:8080/upload/"+stuId+"/head/"+filename;
		}
		//这是存储在数据库中的图片地址
		System.out.println("数据库上的图片地址"+uImage);
		
		UserInfo u = new UserInfo();
		u.setStuId(stuId);
		u.setuImage(uImage);
		
		IUserService service = new UserServeImpl();
		boolean uploadResult = service.uploadHead(u);
		if(uploadResult) {
			listener.setuImage(uImage);
		}
		String result = JSON.toJSONString(uploadResult);
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

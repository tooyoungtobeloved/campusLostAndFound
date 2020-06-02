package user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import user.entity.ContactInfo;
import user.entity.Event;
import user.listener.ActivationListener;
import user.service.IAdminedService;
import user.service.IUserService;
import user.service.Impl.AdminedServiceImpl;
import user.service.Impl.UserServeImpl;
import utils.ReceiveFormdata;

@WebServlet("/PubInfo")
public class PubInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/JSON; charset=UTF-8");
		/*Enumeration<String> headerNames = request.getHeaderNames();
	      while (headerNames.hasMoreElements()) {
             String name = headerNames.nextElement();
              //根据名称获取请求头的值
              String value = request.getHeader(name);
            System.out.println(name+"---"+value);
	      }*/
		      
		HttpSession session = request.getSession();
		ActivationListener listener = (ActivationListener)session.getAttribute("stuId");
		String stuId = listener.getStuId();
		Long imgName = System.currentTimeMillis();
		
		ReceiveFormdata receive = new ReceiveFormdata();
		String directory = "myevent";
		@SuppressWarnings("static-access")
		Map<String,Object> impfileMap = receive.getFilePath(request,imgName.toString(),directory,stuId);//获取解析后的参数列表
	
		String eType = impfileMap.get("types").toString();
		String eKinds = "";
		if(!"寻人启事".equals(eType)) {
			eKinds = impfileMap.get("kinds").toString();
		}
		String eTitle = impfileMap.get("title").toString();
		String eDetail = impfileMap.get("edetail").toString();
		String ePlace = impfileMap.get("place").toString()+"  "+impfileMap.get("place2").toString();
		
		String contactPerson = impfileMap.get("contactPerson").toString();
		System.out.println(contactPerson);
		String eContact = null;
		if("管理员".equals(contactPerson)) {
			IAdminedService admin = new AdminedServiceImpl();
			List<ContactInfo> contactUs = admin.contactUs();
			ContactInfo con = contactUs.get(1);
			eContact = con.getService()+"  QQ:"+con.getsQQ();
		}else {
			eContact = impfileMap.get("contactName").toString()+"   手机："+impfileMap.get("tel").toString();
		}
		
		String dateTemp = impfileMap.get("edate").toString();
		String temp2 = dateTemp.replaceAll("T", " ");
		Date eDate = null;
		Date ePubDate = new Date();
		try {
			SimpleDateFormat sfFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			eDate = sfFormat.parse(temp2);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		
		String filename = impfileMap.get("fileName").toString();
		String eImage = null;
		
		//判断是否上传了图片,如果没上传，给一个我们自己的图片
		if(filename!=null && !filename.isEmpty()) {
			eImage = "http://localhost:8080/upload/"+stuId+"/myevent/"+filename;
		}else{
			if(!"寻人启事".equals(eType)) {
				if(eKinds.equals("校园卡")) {
					eImage = "img/schoolcard.png";
				}else if(eKinds.equals("身份证")) {
					eImage = "img/IDCard.jpg";
				}else if(eKinds.equals("书籍")) {
					eImage = "img/book.jpg";
				}else if(eKinds.equals("电子产品")) {
					eImage = "img/electric.jpeg";
				}else if(eKinds.equals("其它")) {
					eImage = "img/others.jpg";
				}
			}else {
				eImage = "img/寻人启事.jpeg";
			}
		}
		//这是存储在数据库中的
		System.out.println("数据库上的图片地址"+eImage);
		
		Event e = new Event(eType,eKinds,eTitle,eDetail,ePlace,eContact,eDate,ePubDate,eImage);
		IUserService service = new UserServeImpl();
		boolean pubResult = service.pubInfo(e,stuId);
		String result = JSON.toJSONString(pubResult);
		response.getWriter().print(result);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

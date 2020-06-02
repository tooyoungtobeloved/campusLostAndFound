package user.listener;

import java.io.Serializable;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

@WebListener
public class ActivationListener implements HttpSessionActivationListener,Serializable{

	//准备被钝化、活化的数据
	private String stuId;
	private String uImage;
	private String uName;
	
	public ActivationListener() {}
	

	public ActivationListener(String stuId, String uName, String uImage) {
		super();
		this.stuId = stuId;
		this.uImage = uImage;
		this.uName = uName;
	}


	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getuImage() {
		return uImage;
	}
	public void setuImage(String uImage) {
		this.uImage = uImage;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}

	//钝化，监听即将钝化之前
    public void sessionWillPassivate(HttpSessionEvent se)  { 
    	System.out.println("即将钝化session");//这个对象将会随着session的钝化而钝化
    }
	
	//活化，监听刚刚进行活化之后
    public void sessionDidActivate(HttpSessionEvent se)  { 
    	System.out.println("活化session之后");//这个对象将会随着session的活化而活化
    }

  
	
}

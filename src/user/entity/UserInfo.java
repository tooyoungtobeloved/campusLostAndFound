package user.entity;

public class UserInfo {
	private String stuId;//学号
	private String uName;//用户名
	private String uMail;//邮箱
	private String uSex;//性别
	private String uQQ;//QQ号
	private String uDesc;//个性签名
	private String registerTime;//注册时间
	private String uImage;//头像在服务器的存储位置
	private String uPwd;
	
	
	public UserInfo() {}
	
	public UserInfo(String stuId, String uPwd) {
		super();
		this.stuId = stuId;
		this.uPwd = uPwd;
	}
	
	public UserInfo(String stuId, String uPwd,String uName) {
		super();
		this.stuId = stuId;
		this.uPwd = uPwd;
		this.uName = uName;
	}
	
	public UserInfo(String stuId, String uName, String uMail, String uSex, String uQQ, String uDesc,
			String registerTime, String uImage) {
		this.stuId = stuId;
		this.uName = uName;
		this.uMail = uMail;
		this.uSex = uSex;
		this.uQQ = uQQ;
		this.uDesc = uDesc;
		this.registerTime = registerTime;
		this.uImage = uImage;
	}
	
	public UserInfo(String stuId, String uName, String uMail, String uSex, String uQQ, String uDesc) {
		this.stuId = stuId;
		this.uName = uName;
		this.uMail = uMail;
		this.uSex = uSex;
		this.uQQ = uQQ;
		this.uDesc = uDesc;
	}
	
	public UserInfo(String stuId, String uName, String uMail, String uSex, String uQQ, String uDesc,
			String uImage) {
		this.stuId = stuId;
		this.uName = uName;
		this.uMail = uMail;
		this.uSex = uSex;
		this.uQQ = uQQ;
		this.uDesc = uDesc;
		this.uImage = uImage;
	}

	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuMail() {
		return uMail;
	}
	public void setuMail(String uMail) {
		this.uMail = uMail;
	}
	public String getuSex() {
		return uSex;
	}
	public void setuSex(String uSex) {
		this.uSex = uSex;
	}
	public String getuQQ() {
		return uQQ;
	}
	public void setuQQ(String uQQ) {
		this.uQQ = uQQ;
	}
	public String getuDesc() {
		return uDesc;
	}
	public void setuDesc(String uDesc) {
		this.uDesc = uDesc;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getuImage() {
		return uImage;
	}
	public void setuImage(String uImage) {
		this.uImage = uImage;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
}

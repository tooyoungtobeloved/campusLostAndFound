package admin.entity;

public class User {
	private String stuId;
	private String uName;
	private String uImage;
	private String uMail;
	private String uSex;
	private String uQQ;
	private String uDesc;
	private String registerTime;
	
	public User() {
	}
	
	public User(String stuId, String uName, String uImage, String uMail, String uSex, String uQQ,
			String uDesc, String registerTime) {
		this.stuId = stuId;
		this.uName = uName;
		this.uImage = uImage;
		this.uMail = uMail;
		this.uSex = uSex;
		this.uQQ = uQQ;
		this.uDesc = uDesc;
		this.registerTime = registerTime;
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

	public String getuImage() {
		return uImage;
	}

	public void setuImage(String uImage) {
		this.uImage = uImage;
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

	@Override
	public String toString() {
		return "User [stuId=" + stuId + ", uName=" + uName + ", uImage=" + uImage + ", uMail=" + uMail + ", uSex="
				+ uSex + ", uQQ=" + uQQ + ", uDesc=" + uDesc + ", registerTime=" + registerTime + "]";
	}
	
}

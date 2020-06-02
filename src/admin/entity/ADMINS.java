package admin.entity;

public class ADMINS {
	private String stuId;
	private String uPwd;
	private int isAdmin;
	private String uName;
	private String uDesc;
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuDesc() {
		return uDesc;
	}
	public void setuDesc(String uDesc) {
		this.uDesc = uDesc;
	}
	
}

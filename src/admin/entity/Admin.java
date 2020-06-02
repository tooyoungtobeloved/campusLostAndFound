package admin.entity;

public class Admin {
	private String admid;
	private String admpwd;
	
	public Admin() {
	}
	public Admin(String admid, String admpwd) {
		this.admid = admid;
		this.admpwd = admpwd;
	}
	public String getAdmid() {
		return admid;
	}
	public void setAdmid(String admid) {
		this.admid = admid;
	}
	public String getAdmpwd() {
		return admpwd;
	}
	public void setAdmpwd(String admpwd) {
		this.admpwd = admpwd;
	}
	@Override
	public String toString() {
		return "Admin [admid=" + admid + ", admpwd=" + admpwd + "]";
	}
	
}

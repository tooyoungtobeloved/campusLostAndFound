package user.entity;

public class ContactInfo {
	private String service;
	private String sQQ;
	
	public ContactInfo() {}
	
	public ContactInfo(String service, String sQQ) {
		super();
		this.service = service;
		this.sQQ = sQQ;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getsQQ() {
		return sQQ;
	}
	public void setsQQ(String sQQ) {
		this.sQQ = sQQ;
	}
	
}

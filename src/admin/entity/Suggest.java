package admin.entity;

public class Suggest {
	private int sugID;
	private String sugType;
	private String sugName;
	private String sugContact;
	private String sugContent;
	
	public Suggest() {
	}
	public Suggest(int sugID, String sugType, String sugName, String sugContact, String sugContent) {
		this.sugID = sugID;
		this.sugType = sugType;
		this.sugName = sugName;
		this.sugContact = sugContact;
		this.sugContent = sugContent;
	}
	public int getSugID() {
		return sugID;
	}
	public void setSugID(int sugID) {
		this.sugID = sugID;
	}
	public String getSugType() {
		return sugType;
	}
	public void setSugType(String sugType) {
		this.sugType = sugType;
	}
	public String getSugName() {
		return sugName;
	}
	public void setSugName(String sugName) {
		this.sugName = sugName;
	}
	public String getSugContact() {
		return sugContact;
	}
	public void setSugContact(String sugContact) {
		this.sugContact = sugContact;
	}
	public String getSugContent() {
		return sugContent;
	}
	public void setSugContent(String sugContent) {
		this.sugContent = sugContent;
	}
	@Override
	public String toString() {
		return "Suggest [sugID=" + sugID + ", sugType=" + sugType + ", sugName=" + sugName + ", sugContact="
				+ sugContact + ", sugContent=" + sugContent + "]";
	}
}

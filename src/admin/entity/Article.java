package admin.entity;

public class Article {
	private String eStatus;
	private String stuId;
	private String eId;
	private String uName;
	private String eTitle;
	private String eDetail;
	private String ePlace;
	private String eContact;
	private String eDate;
	private String ePubDate;
//	private String eImage;
	
	public String geteId() {
		return eId;
	}
	public String geteStatus() {
		return eStatus;
	}
	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
//	public String getuImage() {
//		return uImage;
//	}
//	public void setuImage(String uImage) {
//		this.uImage = uImage;
//	}
	public String geteTitle() {
		return eTitle;
	}
	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}
	public String geteDetail() {
		return eDetail;
	}
	public void seteDetail(String eDetail) {
		this.eDetail = eDetail;
	}
	public String getePlace() {
		return ePlace;
	}
	public void setePlace(String ePlace) {
		this.ePlace = ePlace;
	}
	public String geteContact() {
		return eContact;
	}
	public void seteContact(String eContact) {
		this.eContact = eContact;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String getePubDate() {
		return ePubDate;
	}
	public void setePubDate(String ePubDate) {
		this.ePubDate = ePubDate;
	}
//	public String geteImage() {
//		return eImage;
//	}
//	public void seteImage(String eImage) {
//		this.eImage = eImage;
//	}
	public Article(String stuId, String eId, String uName, String eTitle, String eDetail, String ePlace,
			String eContact, String eDate, String ePubDate) {
		this.stuId = stuId;
		this.eId = eId;
		this.uName = uName;
		this.eTitle = eTitle;
		this.eDetail = eDetail;
		this.ePlace = ePlace;
		this.eContact = eContact;
		this.eDate = eDate;
		this.ePubDate = ePubDate;
	}

	public Article() {
	}
	@Override
	public String toString() {
		return "Article [stuId=" + stuId + ", eId=" + eId + ", uName=" + uName + ", eTitle=" + eTitle + ", eDetail="
				+ eDetail + ", ePlace=" + ePlace + ", eContact=" + eContact + ", eDate=" + eDate + ", ePubDate="
				+ ePubDate + "]";
	}
	
}

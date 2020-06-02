package user.entity;

import java.util.Date;

public class Event {
	private int eId;//事件ID
	private String eType;//失物招领寻物启事寻人启事
	private String eKinds;//校园卡，电子产品
	private String eTitle;//事件标题
	private String eDetail;//详情描述
	private String ePlace;//丢失地点
	private String eContact;//联系人
	private Date eDate;//丢失捡到时间
	private Date ePubDate;//发布时间
	private String eImage;//物品详情图片在服务器的路径
	private String uImage;
	private String uName;
	private String stuId;
	private int eStatus; 
	
	public Event() {
		super();
	}

	public Event(int eId, String eType, String eKinds, String eTitle, String eDetail, String ePlace, String eContact,
			Date eDate, Date ePubDate, String eImage) {
		super();
		this.eId = eId;
		this.eType = eType;
		this.eKinds = eKinds;
		this.eTitle = eTitle;
		this.eDetail = eDetail;
		this.ePlace = ePlace;
		this.eContact = eContact;
		this.eDate = eDate;
		this.ePubDate = ePubDate;
		this.eImage = eImage;
	}
	
	
	
	public Event(int eId, String eType, String eKinds, String eTitle, String eDetail, String ePlace, String eContact,
			Date eDate, Date ePubDate, String eImage, String uImage, String uName, String stuId, int eStatus) {
		super();
		this.eId = eId;
		this.eType = eType;
		this.eKinds = eKinds;
		this.eTitle = eTitle;
		this.eDetail = eDetail;
		this.ePlace = ePlace;
		this.eContact = eContact;
		this.eDate = eDate;
		this.ePubDate = ePubDate;
		this.eImage = eImage;
		this.uImage = uImage;
		this.uName = uName;
		this.stuId = stuId;
		this.eStatus = eStatus;
	}

	public Event(String eType, String eKinds, String eTitle, String eDetail, String ePlace, String eContact,
			Date eDate, Date ePubDate, String eImage) {
		super();
		this.eType = eType;
		this.eKinds = eKinds;
		this.eTitle = eTitle;
		this.eDetail = eDetail;
		this.ePlace = ePlace;
		this.eContact = eContact;
		this.eDate = eDate;
		this.ePubDate = ePubDate;
		this.eImage = eImage;
	}


	public Event(int eId, String eType, String eKinds, String eTitle, String eDetail, String ePlace, String eContact,
			Date eDate, Date ePubDate, String eImage, String uImage,String uName) {
		super();
		this.eId = eId;
		this.eType = eType;
		this.eKinds = eKinds;
		this.eTitle = eTitle;
		this.eDetail = eDetail;
		this.ePlace = ePlace;
		this.eContact = eContact;
		this.eDate = eDate;
		this.ePubDate = ePubDate;
		this.eImage = eImage;
		this.uImage = uImage;
		this.uName = uName;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

	public String geteKinds() {
		return eKinds;
	}

	public void seteKinds(String eKinds) {
		this.eKinds = eKinds;
	}

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

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Date getePubDate() {
		return ePubDate;
	}

	public void setePubDate(Date ePubDate) {
		this.ePubDate = ePubDate;
	}

	public String geteImage() {
		return eImage;
	}

	public void seteImage(String eImage) {
		this.eImage = eImage;
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

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public int geteStatus() {
		return eStatus;
	}

	public void seteStatus(int eStatus) {
		this.eStatus = eStatus;
	}
	
	
}
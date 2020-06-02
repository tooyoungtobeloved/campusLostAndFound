package user.entity;

import java.util.Date;

public class MarkDetail {

	public MarkDetail() {
	}
	
	private int remarkId;
	private String uName;
	private String uImage;
	private Date markTime;
	private String markDetail;
	private String stuId;
	
	
	public MarkDetail(Date markTime, String markDetail) {
		super();
		this.markTime = markTime;
		this.markDetail = markDetail;
	}
	
	public MarkDetail(String uName, String uImage, Date markTime, String markDetail) {
		super();
		this.uName = uName;
		this.uImage = uImage;
		this.markTime = markTime;
		this.markDetail = markDetail;
	}
	
	
	public MarkDetail(int remarkId, String uName, String uImage, Date markTime, String markDetail, String stuId) {
		super();
		this.remarkId = remarkId;
		this.uName = uName;
		this.uImage = uImage;
		this.markTime = markTime;
		this.markDetail = markDetail;
		this.stuId = stuId;
	}

	public MarkDetail(int remarkId, String uName, String uImage, Date markTime, String markDetail) {
		super();
		this.remarkId = remarkId;
		this.uName = uName;
		this.uImage = uImage;
		this.markTime = markTime;
		this.markDetail = markDetail;
	}
	public int getRemarkId() {
		return remarkId;
	}
	public void setRemarkId(int remarkId) {
		this.remarkId = remarkId;
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
	public Date getMarkTime() {
		return markTime;
	}
	public void setMarkTime(Date markTime) {
		this.markTime = markTime;
	}
	public String getMarkDetail() {
		return markDetail;
	}
	public void setMarkDetail(String markDetail) {
		this.markDetail = markDetail;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	

}

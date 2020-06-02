package user.entity;

import java.util.Date;

public class ThanksMail {
	private int tId;
	private String tTitle;
	private String tDetail;
	private Date tDate;
	
	
	public ThanksMail() {}
	
	public ThanksMail(String tTitle, String tDetail, Date tDate) {
		super();
		this.tTitle = tTitle;
		this.tDetail = tDetail;
		this.tDate = tDate;
	}
	
	public ThanksMail(int tId, String tTitle, String tDetail, Date tDate) {
		super();
		this.tId = tId;
		this.tTitle = tTitle;
		this.tDetail = tDetail;
		this.tDate = tDate;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public String gettTitle() {
		return tTitle;
	}
	public void settTitle(String tTitle) {
		this.tTitle = tTitle;
	}
	public String gettDetail() {
		return tDetail;
	}
	public void settDetail(String tDetail) {
		this.tDetail = tDetail;
	}
	public Date gettDate() {
		return tDate;
	}
	public void settDate(Date tDate) {
		this.tDate = tDate;
	}
	
}

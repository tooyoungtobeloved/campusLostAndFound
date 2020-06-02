package admin.entity;

public class ThanksNote {
	private int tId;
	private String tTitle;
	private String tDetail;
	private String tDate;
	
	
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
	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}
	@Override
	public String toString() {
		return "ThanksNote [tId=" + tId + ", tTitle=" + tTitle + ", tDetail=" + tDetail + ", tDate=" + tDate + "]";
	}
	
}

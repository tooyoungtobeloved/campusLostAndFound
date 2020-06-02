package user.entity;

public class Page {
	private int pageNo;
	private int totalCount;
	private int totalPage;
	private int pageSize;
	
	public Page() {}
	
	public Page(int pageNo, int totalCount, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
	}
	
	public Page(int pageNo, int totalCount, int totalPage, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return (this.totalCount%this.pageSize==0&&this.totalCount>=this.pageSize)?(this.totalCount/this.pageSize):((this.totalCount/this.pageSize)+1);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}

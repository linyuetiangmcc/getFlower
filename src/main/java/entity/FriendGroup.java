package entity;

public class FriendGroup {
	
	//{"lean":"0","success":true,"pageNo":"0","pageSum":34,"openId":"oggN1jiZF8AthajXkotabFE6lbmk","totals":200,"list":""}
	private int lean;
	private boolean success;
	private int pageNo;
	private int pageSum;
	private String openId = "oggN1jiZF8AthajXkotabFE6lbmk";
	private int totals;
	private String list;
	public int getLean() {
		return lean;
	}
	public void setLean(int lean) {
		this.lean = lean;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSum() {
		return pageSum;
	}
	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getTotals() {
		return totals;
	}
	public void setTotals(int totals) {
		this.totals = totals;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	
	

}

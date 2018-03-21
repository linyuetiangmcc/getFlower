package entity;

import java.util.Date;

public class FlowerPost {
	private String plantOpenId;
	private Date plantEndDate;
	private String nickName;
	private String param ;
	public String getPlantOpenId() {
		return plantOpenId;
	}
	public void setPlantOpenId(String plantOpenId) {
		this.plantOpenId = plantOpenId;
	}
	public Date getPlantEndDate() {
		return plantEndDate;
	}
	public void setPlantEndDate(Date plantEndDate) {
		this.plantEndDate = plantEndDate;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
}

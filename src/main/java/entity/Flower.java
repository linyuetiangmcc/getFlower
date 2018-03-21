package entity;
//{"message":"[{\"energy\":\"Y\",\"isSteal\":\"true\",\"myself\":true,\"plantAllTime\":2,
//\"plantCertNo\":\"8QUIei0HxG76EqTc6N3lkCSzyzN715Vk\",\"plantDate\":\"2018-01-11 00:23:54\",
//\"plantEndDate\":\"2018-01-12 00:23:54\",\"plantEnergyEndDate\":\"2018-01-12 00:28:54\",
//\"plantId\":\"4630036\",\"plantIndex\":\"3\",\"plantMatureTime\":7,
//\"plantOpenId\":\"oggN1jlETak4mYXaI6zmv99vyQEQ\",\"plantedId\":\"1Ep3I5AyVlA/SyCWgGKSixUiJ5StCSHM5Nfkgnv1dzY=\",
//\"plantmDate\":\"nxY6NS/pXnIxwHvHNQyrKpZ/YH0zfglb\",\"stateNo\":\"0\"},{\"energy\":\"Y\",\"isSteal\":\"true\",\"myself\":true,\"plantAllTime\":2,\"plantCertNo\":\"8QUIei0HxG76EqTc6N3lkCSzyzN715Vk\",\"plantDate\":\"2018-01-11 00:24:23\",\"plantEndDate\":\"2018-01-12 00:24:23\",\"plantEnergyEndDate\":\"2018-01-12 00:29:23\",\"plantId\":\"4630040\",\"plantIndex\":\"2\",\"plantMatureTime\":7,\"plantOpenId\":\"oggN1jlETak4mYXaI6zmv99vyQEQ\",\"plantedId\":\"lxsXKf5PsbX5xzgP4QbDQlM39vjGpD8/mBoBBT7Xzg4=\",\"plantmDate\":\"nxY6NS/pXnK+o+yn6bBjhcLoaY+d5ZYM\",\"stateNo\":\"0\"},{\"energy\":\"Y\",\"isSteal\":\"true\",\"myself\":true,\"plantAllTime\":2,\"plantCertNo\":\"8QUIei0HxG76EqTc6N3lkCSzyzN715Vk\",\"plantDate\":\"2018-01-11 00:22:39\",\"plantEndDate\":\"2018-01-12 00:22:39\",\"plantEnergyEndDate\":\"2018-01-12 00:27:39\",\"plantId\":\"4630027\",\"plantIndex\":\"4\",\"plantMatureTime\":7,\"plantOpenId\":\"oggN1jlETak4mYXaI6zmv99vyQEQ\",\"plantedId\":\"QLhSEl3QJhM9UfzdLl8MXLULyYpoF55jxo6nC0tWiS8=\",\"plantmDate\":\"nxY6NS/pXnIVvkxB4cjypt1r9EvDnYpa\",\"stateNo\":\"0\"}]","success":true,"flag":true}

import java.util.Date;

public class Flower {
	private String energy;
	private boolean isSteal;
	private boolean myself;
	private int plantAllTime;
	private String plantCertNo;
	
	private String plantDate;
	private String plantEndDate;
	private String plantEnergyEndDate;
	
	
	private Date plantDate_convert;
	private Date plantEndDate_convert;
	private Date plantEnergyEndDate_convert;
	
	private String plantId;
	private int plantIndex;
	private int plantMatureTime;
	private String plantOpenId;
	private String plantedId;
	private String plantmDate;
	private int stateNo;
	public String getEnergy() {
		return energy;
	}
	public void setEnergy(String energy) {
		this.energy = energy;
	}
	public boolean isSteal() {
		return isSteal;
	}
	public void setSteal(boolean isSteal) {
		this.isSteal = isSteal;
	}
	public boolean isMyself() {
		return myself;
	}
	public void setMyself(boolean myself) {
		this.myself = myself;
	}
	public int getPlantAllTime() {
		return plantAllTime;
	}
	public void setPlantAllTime(int plantAllTime) {
		this.plantAllTime = plantAllTime;
	}
	public String getPlantCertNo() {
		return plantCertNo;
	}
	public void setPlantCertNo(String plantCertNo) {
		this.plantCertNo = plantCertNo;
	}
	public String getPlantDate() {
		return plantDate;
	}
	public void setPlantDate(String plantDate) {
		this.plantDate = plantDate;
	}
	public String getPlantEndDate() {
		return plantEndDate;
	}
	public void setPlantEndDate(String plantEndDate) {
		this.plantEndDate = plantEndDate;
	}
	public String getPlantEnergyEndDate() {
		return plantEnergyEndDate;
	}
	public void setPlantEnergyEndDate(String plantEnergyEndDate) {
		this.plantEnergyEndDate = plantEnergyEndDate;
	}
	public Date getPlantDate_convert() {
		return plantDate_convert;
	}
	public void setPlantDate_convert(Date plantDate_convert) {
		this.plantDate_convert = plantDate_convert;
	}
	public Date getPlantEndDate_convert() {
		return plantEndDate_convert;
	}
	public void setPlantEndDate_convert(Date plantEndDate_convert) {
		this.plantEndDate_convert = plantEndDate_convert;
	}
	public Date getPlantEnergyEndDate_convert() {
		return plantEnergyEndDate_convert;
	}
	public void setPlantEnergyEndDate_convert(Date plantEnergyEndDate_convert) {
		this.plantEnergyEndDate_convert = plantEnergyEndDate_convert;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public int getPlantIndex() {
		return plantIndex;
	}
	public void setPlantIndex(int plantIndex) {
		this.plantIndex = plantIndex;
	}
	public int getPlantMatureTime() {
		return plantMatureTime;
	}
	public void setPlantMatureTime(int plantMatureTime) {
		this.plantMatureTime = plantMatureTime;
	}
	public String getPlantOpenId() {
		return plantOpenId;
	}
	public void setPlantOpenId(String plantOpenId) {
		this.plantOpenId = plantOpenId;
	}
	public String getPlantedId() {
		return plantedId;
	}
	public void setPlantedId(String plantedId) {
		this.plantedId = plantedId;
	}
	public String getPlantmDate() {
		return plantmDate;
	}
	public void setPlantmDate(String plantmDate) {
		this.plantmDate = plantmDate;
	}
	public int getStateNo() {
		return stateNo;
	}
	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}
	
	
	
}

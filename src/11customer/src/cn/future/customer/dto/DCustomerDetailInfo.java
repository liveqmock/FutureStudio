package cn.future.customer.dto;

import java.io.Serializable;

import cn.future.customer.pojo.PCustomerDetailInfo;

public class DCustomerDetailInfo implements Serializable{

	private static final long serialVersionUID = 7965319861339115227L;
	public DCustomerDetailInfo(PCustomerDetailInfo p){
		this.id = p.getId();
		this.sex = p.getSex();
		this.age = p.getAge();
		
		this.eduLevel = p.getEduLevel();
		this.eduSchool = p.getEduSchool();
		this.eduSchoolPicId = p.getEduSchoolPicId();
		this.eduInYear = p.getEduInYear();
		
		
		this.isMarried = p.getIsMarried();
		this.marriedPic = p.getMarriedPic();
		
		this.childInfo = p .getChildInfo();
		
		this.kinsfolkName = p.getKinsfolkName();
		this.kinsfolkRelation = p.getKinsfolkRelation();
		this.kinsfolkMobile = p.getKinsfolkMobile();
		
		this.houseHold = p.getHouseHold();
		this.otherCreditProject = p.getOtherCreditProject();
		
		this.creditCard = p.getCreditCard();
		this.creditCardUsed = p.getCreditCardUsed();
		this.creditInfoPic = p.getCreditInfoPic();
		
		this.liveSpace = p.getLiveSpace();
		this.liveSpacePost = p.getLiveSpacePost();
		this.liveSpacePic = p.getLiveSpacePic();
		
		this.monthIncome = p.getMonthIncome();
		this.monthIncomePicId = p.getMonthIncomePicId();
		
		this.workCity = p.getWorkCity();
		this.workStart = p.getWorkStart();
		this.workScope = p.getWorkScope();
		this.workIndustry = p.getWorkIndustry();
		this.workQuality = p.getWorkQuality();
		this.workPosition = p.getWorkPosition();
		
		this.assetsHouse = p.getAssetsHouse();
		this.assetsHousePic = p.getAssetsHousePic();
		this.assetsCar = p.getAssetsCar();
		this.assetsCarPic = p.getAssetsCarPic();
	}
	public DCustomerDetailInfo(){
		
	}
	private String id;//使用customer的id
	private String sex;
	private int age;
	//学历
	private String eduLevel;
	//毕业学校
	private String eduSchool;
	/**
	 * 在线验证学历信息
	 */
	private String eduSchoolPicId;
		//入学年份
	private String eduInYear;
	//是否结婚
	private String isMarried;
	//结婚证书
	private String marriedPic;
	private String childInfo;
	
	//直系亲属
	private String kinsfolkName;
	private String kinsfolkRelation;
	private String kinsfolkMobile="";
	
	private String houseHold;
	private String otherCreditProject;
	
	private String creditCard;
	private String creditCardUsed;
	//信用报告
	private String creditInfoPic;
		
	//居住地信息，邮政编码，证明图片
	private String liveSpace;
	private String liveSpacePost;
	private String liveSpacePic;
		
	private String monthIncome;
	private String monthIncomePicId;
	
	//工作信息
	private String workCity; //工作城市
	private String workStart;//现单位开始工作时间
	private String workScope;//公司规模
	private String workIndustry;//公司行业
	private String workQuality;//公司性质
	private String workPosition; //岗位
	
	//资产信息
	private String assetsHouse;
	private String assetsHousePic;
	private String assetsCar;
	private String assetsCarPic;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}
	public String getEduSchool() {
		return eduSchool;
	}
	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}
	public String getEduInYear() {
		return eduInYear;
	}
	public void setEduInYear(String eduInYear) {
		this.eduInYear = eduInYear;
	}
	public String getChildInfo() {
		return childInfo;
	}
	public void setChildInfo(String childInfo) {
		this.childInfo = childInfo;
	}
	public String getHouseHold() {
		return houseHold;
	}
	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}
	public String getOtherCreditProject() {
		return otherCreditProject;
	}
	public void setOtherCreditProject(String otherCreditProject) {
		this.otherCreditProject = otherCreditProject;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public String getCreditCardUsed() {
		return creditCardUsed;
	}
	public void setCreditCardUsed(String creditCardUsed) {
		this.creditCardUsed = creditCardUsed;
	}
	public String getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}
	public String getWorkCity() {
		return workCity;
	}
	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}
	public String getWorkStart() {
		return workStart;
	}
	public void setWorkStart(String workStart) {
		this.workStart = workStart;
	}
	public String getWorkScope() {
		return workScope;
	}
	public void setWorkScope(String workScope) {
		this.workScope = workScope;
	}
	public String getWorkIndustry() {
		return workIndustry;
	}
	public void setWorkIndustry(String workIndustry) {
		this.workIndustry = workIndustry;
	}
	public String getWorkQuality() {
		return workQuality;
	}
	public void setWorkQuality(String workQuality) {
		this.workQuality = workQuality;
	}
	public String getWorkPosition() {
		return workPosition;
	}
	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}
	public String getAssetsCar() {
		return assetsCar;
	}
	public void setAssetsCar(String assetsCar) {
		this.assetsCar = assetsCar;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEduSchoolPicId() {
		return eduSchoolPicId;
	}
	public void setEduSchoolPicId(String eduSchoolPicId) {
		this.eduSchoolPicId = eduSchoolPicId;
	}
	public String getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(String isMarried) {
		this.isMarried = isMarried;
	}
	public String getMarriedPic() {
		return marriedPic;
	}
	public void setMarriedPic(String marriedPic) {
		this.marriedPic = marriedPic;
	}
	public String getKinsfolkName() {
		return kinsfolkName;
	}
	public void setKinsfolkName(String kinsfolkName) {
		this.kinsfolkName = kinsfolkName;
	}
	public String getKinsfolkRelation() {
		return kinsfolkRelation;
	}
	public void setKinsfolkRelation(String kinsfolkRelation) {
		this.kinsfolkRelation = kinsfolkRelation;
	}
	public String getKinsfolkMobile() {
		return kinsfolkMobile;
	}
	public void setKinsfolkMobile(String kinsfolkMobile) {
		this.kinsfolkMobile = kinsfolkMobile;
	}
	public String getCreditInfoPic() {
		return creditInfoPic;
	}
	public void setCreditInfoPic(String creditInfoPic) {
		this.creditInfoPic = creditInfoPic;
	}
	public String getLiveSpace() {
		return liveSpace;
	}
	public void setLiveSpace(String liveSpace) {
		this.liveSpace = liveSpace;
	}
	public String getLiveSpacePost() {
		return liveSpacePost;
	}
	public void setLiveSpacePost(String liveSpacePost) {
		this.liveSpacePost = liveSpacePost;
	}
	public String getLiveSpacePic() {
		return liveSpacePic;
	}
	public void setLiveSpacePic(String liveSpacePic) {
		this.liveSpacePic = liveSpacePic;
	}
	public String getMonthIncomePicId() {
		return monthIncomePicId;
	}
	public void setMonthIncomePicId(String monthIncomePicId) {
		this.monthIncomePicId = monthIncomePicId;
	}
	public String getAssetsHouse() {
		return assetsHouse;
	}
	public void setAssetsHouse(String assetsHouse) {
		this.assetsHouse = assetsHouse;
	}
	public String getAssetsHousePic() {
		return assetsHousePic;
	}
	public void setAssetsHousePic(String assetsHousePic) {
		this.assetsHousePic = assetsHousePic;
	}
	public String getAssetsCarPic() {
		return assetsCarPic;
	}
	public void setAssetsCarPic(String assetsCarPic) {
		this.assetsCarPic = assetsCarPic;
	}
	
	
}

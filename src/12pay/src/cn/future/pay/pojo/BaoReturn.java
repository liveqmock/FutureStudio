package cn.future.pay.pojo;

public class BaoReturn {
	private String MerchantID;
	private String TransID;//商户流水
	private String Result;
	private String resultDesc;
	private String factMoney;
	private String additionalInfo;
	private String SuccTime;
	private String Md5Sign;
	
	public BaoReturn(String merchantID, String transID, String result,
			String resultDesc, String factMoney, String additionalInfo,
			String succTime, String md5Sign) {
		super();
		MerchantID = merchantID;
		TransID = transID;
		Result = result;
		this.resultDesc = resultDesc;
		this.factMoney = factMoney;
		this.additionalInfo = additionalInfo;
		SuccTime = succTime;
		Md5Sign = md5Sign;
	}
	public String getMerchantID() {
		return MerchantID;
	}
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	public String getTransID() {
		return TransID;
	}
	public void setTransID(String transID) {
		TransID = transID;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getFactMoney() {
		return factMoney;
	}
	public void setFactMoney(String factMoney) {
		this.factMoney = factMoney;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getSuccTime() {
		return SuccTime;
	}
	public void setSuccTime(String succTime) {
		SuccTime = succTime;
	}
	public String getMd5Sign() {
		return Md5Sign;
	}
	public void setMd5Sign(String md5Sign) {
		Md5Sign = md5Sign;
	}
	
	
}

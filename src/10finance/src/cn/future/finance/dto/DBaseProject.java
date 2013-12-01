package cn.future.finance.dto;

import java.io.Serializable;
import java.util.List;

import cn.future.finance.exception.FinanceProjectStatusCodeError;
import cn.future.finance.pojo.PBaseProject;
import cn.future.finance.util.FinanceCodeUtil;
import cn.future.finance.util.FinanceMoneyUtil;
import cn.future.oa.dto.DAccount;
import cn.future.util.TimeUtil;

public class DBaseProject implements Serializable {
	public DBaseProject(){
		
	}
	public DBaseProject(PBaseProject p){
		this.id = p.getId();
		this.flowId = p.getFlowId();
		this.userId = p.getUserId();
		this.timeType = p.getTimeType();
		this.title = p.getTitle();
		this.canBidNumber = FinanceMoneyUtil.formatThreeSplit((p
				.getFinanceNumber() - p.getAlreadyNumber()) / 100);
		this.financeNumber = FinanceMoneyUtil.formatThreeSplit(p
				.getFinanceNumber() / 100);// 单位是分
		this.alreadyNumber = FinanceMoneyUtil.formatThreeSplit(p
				.getAlreadyNumber() / 100);
		this.returnInfo = p.getReturnInfo();
		float percent = (p.getAlreadyNumber() * 100) / p.getFinanceNumber();
		this.alreadyPercent = (int) percent;
		this.bidTimes = p.getBidTimes();
		this.content = p.getContent();
		this.contentComments = p.getContentComments();
		this.comments = p.getComments();
		this.yearRate = p.getYearRate();
		this.projectMonth = p.getProjectMonth();
		this.returnTypeCode = p.getReturnTypeCode();
		this.returnTypeName = FinanceCodeUtil
				.getReturnName(this.returnTypeCode);
		this.projectTypeCode = p.getProjectTypeCode();
		this.projectTypeName = FinanceCodeUtil
				.getCreditName(this.projectTypeCode);

		this.startDate = TimeUtil.dateToYMDString(p.getStartDate());
		this.finishDate = TimeUtil.dateToYMDString(p.getFinishDate());
		this.deadDate = TimeUtil.dateToYMDString(p.getDeadDate());
		this.timeLeft = TimeUtil.timeDifference(p.getStartDate(),
				p.getDeadDate());
		this.closedBy = p.getClosedBy();
		this.closedId = p.getClosedId();
		this.statusCode = p.getStatusCode();
		try {
			this.statusName = FinanceCodeUtil.getStatusName(this.statusCode);
		} catch (FinanceProjectStatusCodeError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private DAccount account;
	private List<DFinanceProjectBidDetail> listBids;
	private String returnTypeName;
	private String projectTypeName;
	private String timeLeft;
	private String statusName;
	
	private static final long serialVersionUID = -7471569812688616804L;
	private String id; // real key
	private String flowId; // show key
	private String userId;// 哪个用户发布上来的
	private int version; // db transaction
	private String financeNumber; //
	private String alreadyNumber;
	private String canBidNumber;
	private int alreadyPercent;
	private int bidTimes;

	private String timeType; // 短期 长期
	private String yearRate;// 年化利率
	private int projectMonth;// 结款多少月？

	/**
	 * 项目标题
	 */
	private String title;
	private String content;
	private String contentComments;
	private String comments;

	// 还款类型
	private int returnTypeCode;// 还款类型
	private String returnInfo;// 还款信息 每月利息1%之类
	// 项目分类
	private int projectTypeCode; // 抵押 or

	private int minNumber; // 0 不控制
	private int stepNumber; // 100 一百一跳
	private int maxNumber; // 0 不控制

	private String startDate;
	private String finishDate;
	private String deadDate;
	/**
	 * 关闭状态， 对内显示 10 满标，系统关闭 5时间到系统关闭 1管理员关闭
	 */
	private String closedBy;//
	/**
	 * 谁关闭的项目 0 系统 或者userid
	 */
	private String closedId;
	/**
	 * 项目状态，对外显示 初始化为招标中
	 */
	private int statusCode;
	
	public String getReturnTypeName() {
		return returnTypeName;
	}
	public void setReturnTypeName(String returnTypeName) {
		this.returnTypeName = returnTypeName;
	}
	public String getProjectTypeName() {
		return projectTypeName;
	}
	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}
	public String getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getFinanceNumber() {
		return financeNumber;
	}
	public void setFinanceNumber(String financeNumber) {
		this.financeNumber = financeNumber;
	}
	public String getAlreadyNumber() {
		return alreadyNumber;
	}
	public void setAlreadyNumber(String alreadyNumber) {
		this.alreadyNumber = alreadyNumber;
	}
	public String getCanBidNumber() {
		return canBidNumber;
	}
	public void setCanBidNumber(String canBidNumber) {
		this.canBidNumber = canBidNumber;
	}
	public int getAlreadyPercent() {
		return alreadyPercent;
	}
	public void setAlreadyPercent(int alreadyPercent) {
		this.alreadyPercent = alreadyPercent;
	}
	public int getBidTimes() {
		return bidTimes;
	}
	public void setBidTimes(int bidTimes) {
		this.bidTimes = bidTimes;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getYearRate() {
		return yearRate;
	}
	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}
	public int getProjectMonth() {
		return projectMonth;
	}
	public void setProjectMonth(int projectMonth) {
		this.projectMonth = projectMonth;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentComments() {
		return contentComments;
	}
	public void setContentComments(String contentComments) {
		this.contentComments = contentComments;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getReturnTypeCode() {
		return returnTypeCode;
	}
	public void setReturnTypeCode(int returnTypeCode) {
		this.returnTypeCode = returnTypeCode;
	}
	public String getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}
	public int getProjectTypeCode() {
		return projectTypeCode;
	}
	public void setProjectTypeCode(int projectTypeCode) {
		this.projectTypeCode = projectTypeCode;
	}
	public int getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}
	public int getStepNumber() {
		return stepNumber;
	}
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(String deadDate) {
		this.deadDate = deadDate;
	}
	public String getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}
	public String getClosedId() {
		return closedId;
	}
	public void setClosedId(String closedId) {
		this.closedId = closedId;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DAccount getAccount() {
		return account;
	}
	public void setAccount(DAccount account) {
		this.account = account;
	}
	public List<DFinanceProjectBidDetail> getListBids() {
		return listBids;
	}
	public void setListBids(List<DFinanceProjectBidDetail> listBids) {
		this.listBids = listBids;
	}

	
}

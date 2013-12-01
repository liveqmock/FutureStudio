package cn.future.finance.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.finance.dto.DFinanceProject;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.service.FinanceProjectQueryService;

public class FinanceProjectQueryAction extends BaseAction{
	private static final long serialVersionUID = -706208265690766438L;
	private String id;
	private int page=1;
	private int pageSize=20;
	private String query=null;
	private FinanceProjectQueryService financeProjectQueryService;
	//set get
	private int count=0;
	private int countPage=0;
	private List<DFinanceProject> financeProjects;
	private DFinanceProject financeProject;
	private String message;
	/**
	 * 获取投资项目的全部信息，包括客户、负责管理员、投标信息
	 * @return
	 */
	public String projectDetail(){
		PFinanceProject pojo = null;
		try {
			response.setStatus(400);
			pojo = financeProjectQueryService.findFinanceById(id);
			int[] mode = {1,2,4,6};
			financeProject = financeProjectQueryService.transfer(pojo, mode);
			response.setStatus(200);
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			message=e.getMessage();
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 获取投资项目列表
	 * @return
	 */
	public String projectsList(){
		List<PFinanceProject> list = new ArrayList<PFinanceProject>();
		count=financeProjectQueryService.findFinanceProject(list, null, null, 0, page, pageSize, query);
		countPage=count/pageSize;
		financeProjects=financeProjectQueryService.transfer(list);
		return SUCCESS;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCount() {
		return count;
	}

	public List<DFinanceProject> getFinanceProjects() {
		return financeProjects;
	}

	public void setFinanceProjectQueryService(
			FinanceProjectQueryService financeProjectQueryService) {
		this.financeProjectQueryService = financeProjectQueryService;
	}

	public int getCountPage() {
		return countPage;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DFinanceProject getFinanceProject() {
		return financeProject;
	}
	public String getMessage() {
		return message;
	}
	
}

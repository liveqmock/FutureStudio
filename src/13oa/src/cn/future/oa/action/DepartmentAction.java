package cn.future.oa.action;

import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DDepartment;
import cn.future.oa.service.DepartmentQueryService;

public class DepartmentAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1415910221597686514L;
	private DepartmentQueryService departmentQueryService;
	//set -- get
	private List<DDepartment> departments;
	
	private int nocheck=-1;

	public String findDeptTree() throws NotFindException{
		departments = departmentQueryService.findDepartmentTree();
		if(departments!=null){
			for(DDepartment d : departments){
				d.checkRule(nocheck);
			}
		}
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DDepartment> getDepartments() {
		return departments;
	}


	public void setDepartmentQueryService(
			DepartmentQueryService departmentQueryService) {
		this.departmentQueryService = departmentQueryService;
	}

	public void setNocheck(int nocheck) {
		this.nocheck = nocheck;
	}
	
}

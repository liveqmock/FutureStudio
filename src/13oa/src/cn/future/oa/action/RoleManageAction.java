package cn.future.oa.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.oa.service.RoleManageService;

public class RoleManageAction extends BaseAction {

	private static final long serialVersionUID = -2472850923670105587L;
	//set 
	private RoleManageService roleManageService;
	private String userId;
	private String roleId;
	
	//get
	private String message;
	/**
	 * 为用户添加角色
	 * @return
	 */
	public String addUserRole(){
		response.setStatus(400);
		try {
			roleManageService.addAccountRole(userId, roleId);
			response.setStatus(200);
			message="添加成功";
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="添加角色出错，"+e.getMessage();
		}
		return SUCCESS;
	}
	/**
	 * 移除用户角色
	 * @return
	 */
	public String removeUserRole(){
		response.setStatus(400);
		try {
			roleManageService.removeAccountRole(userId, roleId);
			response.setStatus(200);
			message="移除成功";
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="移除失败，"+e.getMessage();
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getMessage() {
		return message;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public void setRoleManageService(RoleManageService roleManageService) {
		this.roleManageService = roleManageService;
	}

	
}

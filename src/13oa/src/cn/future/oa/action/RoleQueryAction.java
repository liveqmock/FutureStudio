package cn.future.oa.action;

import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DRole;
import cn.future.oa.service.RoleService;

public class RoleQueryAction extends BaseAction{

	private static final long serialVersionUID = 746774028451149085L;
	private RoleService roleService;
	//set
	private String userId=null;
	
	//get
	private List<DRole> roles;
	
	/**
	 * 查询角色信息，
	 * 如果有userId，那么查询用户的角色信息，如果没有列出所有的角色
	 * @return
	 */
	public String list(){
		if(userId !=null){
			try {
				roles = roleService.findAccountRoles(userId);
			} catch (NotFindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			roles = roleService.findRoles();
		}
		return SUCCESS;
	}

	public List<DRole> getRoles() {
		return roles;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
}

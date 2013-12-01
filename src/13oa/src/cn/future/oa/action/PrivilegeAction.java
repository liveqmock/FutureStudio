package cn.future.oa.action;

import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DSysFunction;
import cn.future.oa.pojo.SysOperation;
import cn.future.oa.service.PrivilegeService;

public class PrivilegeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7847831361080012202L;
	//set
	private PrivilegeService privilegeService;
	//get
	private List<DSysFunction> menus;
	private List<SysOperation> privileges;
	private List<DSysFunction> roleMenus;
	private List<SysOperation> roleOperations;
	private String message;
	private String id;
	/**
	 * 进入主界面后抓取菜单信息，权限信息，等
	 * @return
	 * @throws NotFindException 
	 */
	public String findSysFunction(){
		response.setStatus(500);
		id=this.findCookieId();
		if(id!=null){
			try{
				menus = privilegeService.findSysFunctionByUserId(id);
				privileges = privilegeService.findSysOperationByUserId(id);
				response.setStatus(200);
			}catch(NotFindException e){
				message=e.getMessage();
			}
		}
		message="id is null";
		return SUCCESS;
	}
	/**
	 * 主界面查找用户权限信息
	 * @return
	 * @throws NotFindException 
	 */
	public String findSysOperation() throws NotFindException{
		if(id!=null){
			privileges = privilegeService.findSysOperationByUserId(id);
		}
		return SUCCESS;
	}

	/**
	 * 查找所有的菜单和操作权限
	 * @return
	 */
	public String findAllPrivilege(){
		roleMenus = privilegeService.findSysfunctionMenuAll();
		roleOperations = privilegeService.findSysOperationAll();
		return SUCCESS;
	}
	/**
	 *  //TODO 这里应该首先判断是否有读取权限，
	 *  如果有权限，根据传回来的roleid查找用户的菜单权限和操作权限，
	 *  菜单权限，仅限于menu，
	 *  操作权限，全部
	 * @return
	 * @throws NotFindException 
	 */
	public String findPrivilegeByRoleId() throws NotFindException{
		if(id==null){
			return SUCCESS;
		}
		String roleid=id;
		roleMenus=privilegeService.findSysFunctionMenuByRoleId(roleid);
		roleOperations=privilegeService.findSysOperationByRoleId(roleid);
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<DSysFunction> getMenus() {
		return menus;
	}
	public List<SysOperation> getPrivileges() {
		return privileges;
	}
	public List<DSysFunction> getRoleMenus() {
		return roleMenus;
	}
	public List<SysOperation> getRoleOperations() {
		return roleOperations;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	public String getMessage() {
		return message;
	}
	
}
